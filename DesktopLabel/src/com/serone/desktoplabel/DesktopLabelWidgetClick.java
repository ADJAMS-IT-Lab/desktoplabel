package com.serone.desktoplabel;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import com.serone.desktoplabel.R;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Configurador del widget en tiempo de ejecución
 * @author doctor@serone.org
 */
public class DesktopLabelWidgetClick extends Activity implements OnClickListener, OnKeyListener
{
	private Context contexto=null;
	
	// ID del widget
	private int idWidget=AppWidgetManager.INVALID_APPWIDGET_ID;
	
	// Controles
	private EditText etiqueta;
	
	private Button botonOK;
	private Button botonColorFondo;
	private Button botonColorTexto;
	private Button botonIcono;
	
	private ImageView vistaPrevia=null;
	private LinearLayout layoutWidget=null;
	
	// Configuración final
	private int colorFondo=Color.BLACK;
	private int colorTexto=Color.WHITE;
	private int icono=1;
	private int posicionIcono=1; // 1=izquierda, 2=derecha

	private int alturaWidget=0;
	private int anchuraWidget=0;
	
	// Coloreando ahora (0=nada, 1=fondo, 2=texto)
	int coloreandoAhora=0;
	
    /**
     * Evento de creación
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.contexto=this.getApplicationContext();
        
        // Especificamos el layout
        setContentView(R.layout.layout_actividad_click);
    
        // Cogemos el id desde el intent (lo hemos metido al asociar el evento)
        // NOTA: Como los extras daban problemas, hemos metido el id como una
        // uri de datos Android. Ver la documentación para más detalles.
        Intent intent=this.getIntent(); 
        String uri=intent.getData().toString();
        
        this.idWidget=new Integer(uri.split("//")[1]);

        // Si no ha ido bien...
        if(this.idWidget==AppWidgetManager.INVALID_APPWIDGET_ID)
        {
        	Utils.logError("No se ha podido coger el ID del widget, cancelando configuración.");

        	// Retornamos CANCEL
        	Intent cancelResult=new Intent();
        	cancelResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.idWidget);
            this.setResult(RESULT_CANCELED, cancelResult);
            
            // Mostramos al usuario
            // FIXME: Ántes de activar hacer multiidioma
            //Utils.mostrarToast(contexto, "No se ha podido coger el ID del widget, cancelando configuración.");
            
        	// Salimos sin hacer nada más
        	this.finish();
        }
        else Utils.logAviso("Reconfigurando widget #"+this.idWidget);

        // Cogemos los controles
        this.etiqueta=(EditText)findViewById(R.id.etiqueta);
        this.etiqueta.setOnKeyListener(this);
        
        this.botonOK=(Button)findViewById(R.id.botonAceptar);
        this.botonOK.setOnClickListener(this);
        this.botonColorFondo=(Button)findViewById(R.id.botonColorFondo);
        this.botonColorFondo.setOnClickListener(this);
        this.botonColorTexto=(Button)findViewById(R.id.botonColorTexto);
        this.botonColorTexto.setOnClickListener(this);
        this.botonIcono=(Button)findViewById(R.id.botonIcono);
        this.botonIcono.setOnClickListener(this);

        this.vistaPrevia=(ImageView)findViewById(R.id.imagenWidget);
        this.layoutWidget=(LinearLayout)findViewById(R.id.layoutWidget);
        
        // Reconfiguramos la vista con la configuración actual
        SharedPreferences prefs=contexto.getSharedPreferences("DesktopLabel", 0);
        
        this.etiqueta.  setText(prefs.getString("Widget="+this.idWidget+" (Etiqueta)", "DesktopLabel"));
        this.colorFondo=prefs.getInt("Widget="+this.idWidget+" (ColorFondo)", Color.BLACK);
        this.colorTexto=prefs.getInt("Widget="+this.idWidget+" (ColorTexto)", Color.WHITE);
        this.icono=     prefs.getInt("Widget="+this.idWidget+" (Icono)", 1);

        // Cogemos las dimensiones
        this.alturaWidget=prefs.getInt("Widget="+this.idWidget+" (Altura)", 0);
        this.anchuraWidget=prefs.getInt("Widget="+this.idWidget+" (Anchura)", 0);

		// Cambiamos de tamaño al layout
		LayoutParams pars=this.layoutWidget.getLayoutParams();

		pars.height=this.alturaWidget+12;
		pars.width=this.anchuraWidget+16;
		
		this.layoutWidget.setLayoutParams(pars);
		
		// Cambiamos el tamaño a la imagen interna
		pars=this.vistaPrevia.getLayoutParams();
		
		pars.height=this.alturaWidget;
		pars.width=this.anchuraWidget;
		
		this.layoutWidget.setLayoutParams(pars);
		
        // Actualizamos la vista previa
		this.actualizarVistaPrevia();
    }

    /**
     * Eventos del botón
     */
	public void onClick(View v)
	{
		// Según el botón
		if(v==(View)this.botonOK)
		{
			// Se ha pulsado el botón, guardamos la cadena en las SharedPreferences
			// y finalizamos el configurador
			Context contexto=this.getApplicationContext();
	
			// Cogemos la etiqueta
			String etiquetaFinal=this.etiqueta.getText().toString();
			
			// Guardamos
	        SharedPreferences.Editor prefs=contexto.getSharedPreferences("DesktopLabel", 0).edit();
	        
	        prefs.putString("Widget="+this.idWidget+" (Etiqueta)", 		 etiquetaFinal);
	        prefs.putInt(	"Widget="+this.idWidget+" (ColorFondo)", 	 this.colorFondo);
	        prefs.putInt(	"Widget="+this.idWidget+" (ColorTexto)", 	 this.colorTexto);
	        prefs.putInt(	"Widget="+this.idWidget+" (Icono)", 		 this.icono);
	        prefs.putInt(   "Widget="+this.idWidget+" (PosicionIcono)",  this.posicionIcono);
	        
	        prefs.commit();
			
	        // Ponemos el result a OK y retornamos el ID
	    	Intent okResult=new Intent();
	    	okResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.idWidget);
	        this.setResult(RESULT_OK, okResult);

	        // Cogemos las dimensiones del widget
	        SharedPreferences pref=contexto.getSharedPreferences("DesktopLabel", 0);
	        
	        int anchura=pref.getInt( "Widget="+this.idWidget+" (Anchura)", 0);
	        int altura=pref.getInt("Widget="+this.idWidget+" (Altura)", 0);
	        
	        // Forzamos la primera actualización
	        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(contexto);
	        
	        DesktopLabelWidgetStatic.actualizar(
	        	contexto, appWidgetManager, this.idWidget, etiquetaFinal, this.icono, 
	        	this.colorFondo, this.colorTexto, anchura, altura, this.posicionIcono);
	        
            // Mostramos al usuario
	        Utils.mostrarToast(contexto, this.getString(R.string.widget_actualizado));
            
	        // Salimos
	    	Utils.logInfo("Configuración del widget completada (etiqueta="+etiquetaFinal+")");
	    	this.finish();
		}
		else if(v==(View)this.botonColorFondo)
		{
			this.coloreandoAhora=1;
			
			// Mostramos la actividad
            Intent nuevoIntent = new Intent(v.getContext(), SeroneWidgetColor.class);
            this.startActivityForResult(nuevoIntent, 0);
		}
		else if(v==(View)this.botonColorTexto)
		{
			this.coloreandoAhora=2;
			
			// Mostramos la actividad
            Intent nuevoIntent = new Intent(v.getContext(), SeroneWidgetColor.class);
            this.startActivityForResult(nuevoIntent, 1);
		}
		else if(v==(View)this.botonIcono)
		{
			// Mostramos la actividad
            Intent nuevoIntent = new Intent(v.getContext(), SeroneWidgetIcono.class);
            this.startActivityForResult(nuevoIntent, 2);
		}
	}
	
	/**
	 * Atiende a los cambios de color y de icono
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch(requestCode)
		{
			case 0:
			{
				// Color de fondo
				this.colorFondo=resultCode;
				this.actualizarVistaPrevia();
				
		        // Reseteamos
		        this.coloreandoAhora=0;
		        
				break;
			}
			
			case 1:
			{
				// Color de texto
				this.colorTexto=resultCode;
				this.actualizarVistaPrevia();
				
		        // Reseteamos
		        this.coloreandoAhora=0;
		        
				break;
			}
			
			case 2:
			{
				// Icono
				this.icono=resultCode+1;
				this.actualizarVistaPrevia();
				break;
			}
		}
	}

	/**
	 * 
	 */
	private void actualizarVistaPrevia()
	{
		// Cogemos la etiqueta
		String etiquetaFinal=this.etiqueta.getText().toString();

		// Renderizamos todo
		Bitmap bitmap=DesktopLabelWidgetStatic.dibujarCanvas(
				this.contexto, this.idWidget, etiquetaFinal, this.icono, this.colorFondo, 
				this.colorTexto, this.anchuraWidget, this.alturaWidget, this.posicionIcono);
		
		// Dibujamos la vista previa
		this.vistaPrevia.setImageBitmap(bitmap);
	}

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public boolean onKey(View vista, int arg1, KeyEvent arg2)
	{
		if(vista==(View)this.etiqueta)
		{
			// Cada vez que se cambie el texto actualizamos la vista previa
			this.actualizarVistaPrevia();
		}		
		return false;
	}
}