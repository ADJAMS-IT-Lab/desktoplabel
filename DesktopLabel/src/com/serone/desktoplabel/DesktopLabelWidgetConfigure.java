package com.serone.desktoplabel;

// Imports necesarios
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Configurador del widget
 * @author doctor@serone.org
 */
public class DesktopLabelWidgetConfigure extends Activity implements OnClickListener
{
	// ID del widget
	private int idWidget=AppWidgetManager.INVALID_APPWIDGET_ID;
	
	// Controles
	private EditText etiqueta;
	private Button botonOK;
	private Button botonImagenMas;
	private Button botonImagenMenos;
	private ImageView imagen;
	private Spinner spinnerMostrarIcono;
	private Spinner spinnerFondo;
	private Spinner spinnerTexto;
	
	// Para el ciclo de la imagen
	private final int maxImagenes=28;
	private int nImagen=1;
	
    /**
     * Evento de creaci�n
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Especificamos el layout
        setContentView(R.layout.layout_actividad_configure);
        
        // Cogemos los controles
        this.etiqueta=(EditText)findViewById(R.id.etiqueta);
        this.imagen=(ImageView)findViewById(R.id.imagenWidgetConfig);
        this.botonOK=(Button)findViewById(R.id.botonAceptar);
        this.botonOK.setOnClickListener(this);
        this.botonImagenMas=(Button)findViewById(R.id.botonImagenMas);
        this.botonImagenMas.setOnClickListener(this);
        this.botonImagenMenos=(Button)findViewById(R.id.botonImagenMenos);
        this.spinnerMostrarIcono=(Spinner)findViewById(R.id.spinnerMostrarIcono);
        this.spinnerFondo=(Spinner)findViewById(R.id.spinnerColorFondo);
        this.spinnerTexto=(Spinner)findViewById(R.id.spinnerColorTexto);
        
        // Asociamos el listener de los botones
        this.botonImagenMenos.setOnClickListener(this);
        
        // Llenamos los spinnera (b�sicamente es un combobox). Cada elemento es
        // una vista en si misma. Un adapter es lo que le proporciona al
        // spinner �sos datos, por lo que crearemos uno para cada uno.
        String datosFondo[]=new String[3];
        String datosTexto[]=new String[2];
        String datosMostrarIcono[]=new String[2];
        
        datosFondo[0]=this.getString(R.string.negro);
        datosFondo[1]=this.getString(R.string.blanco);
        datosFondo[2]=this.getString(R.string.transparente);
        
        datosTexto[0]=this.getString(R.string.negro);
        datosTexto[1]=this.getString(R.string.blanco);
        
        datosMostrarIcono[0]=this.getString(R.string.si);
        datosMostrarIcono[1]=this.getString(R.string.no);
        
        ArrayAdapter<String> adaptadorFondo=new ArrayAdapter<String>(
        		this.getApplicationContext(), android.R.layout.simple_spinner_item, datosFondo);
        ArrayAdapter<String> adaptadorTexto=new ArrayAdapter<String>(
        		this.getApplicationContext(), android.R.layout.simple_spinner_item, datosTexto);
        ArrayAdapter<String> adaptadorMostrarIcono=new ArrayAdapter<String>(
        		this.getApplicationContext(), android.R.layout.simple_spinner_item, datosMostrarIcono);
        
        this.spinnerFondo.setAdapter(adaptadorFondo);
        this.spinnerFondo.setSelection(0);
        
        this.spinnerTexto.setAdapter(adaptadorTexto);
        this.spinnerTexto.setSelection(1);

        this.spinnerMostrarIcono.setAdapter(adaptadorMostrarIcono);
        this.spinnerMostrarIcono.setSelection(0);
        
        // Cogemos el id desde el intent
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        
        if (extras!=null)
        {
            this.idWidget=extras.getInt(
            	AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        
        // Si no ha ido bien...
        if(this.idWidget==AppWidgetManager.INVALID_APPWIDGET_ID)
        {
        	Utils.logError("No se ha podido coger el ID del widget, cancelando configuraci�n.");

        	// Retornamos CANCEL
        	Intent cancelResult=new Intent();
        	cancelResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.idWidget);
            this.setResult(RESULT_CANCELED, cancelResult);
            
        	// Salimos sin hacer nada m�s
        	this.finish();
        }
    }

    /**
     * Eventos del bot�n
     */
	public void onClick(View v)
	{
		// Seg�n el bot�n
		if(v==(View)this.botonOK)
		{
			// Se ha pulsado el bot�n, guardamos la cadena en las SharedPreferences
			// y finalizamos el configurador
			Context contexto=this.getApplicationContext();
	
			// Cogemos la etiqueta
			String etiquetaFinal=this.etiqueta.getText().toString();
			
			// Cogemos los colores
			int colorFondo=this.spinnerFondo.getSelectedItemPosition();
			int colorTexto=this.spinnerTexto.getSelectedItemPosition();
			
			// Cogemos el modo de icono
			boolean mostrarIcono=(this.spinnerFondo.getSelectedItemPosition()==0?true:false);
			
			// Guardamos
	        SharedPreferences.Editor prefs=contexto.getSharedPreferences("DesktopLabel", 0).edit();
	        prefs.putString("Etiqueta"+this.idWidget, etiquetaFinal);
	        prefs.putInt("Icono"+this.idWidget, this.nImagen);
	        prefs.putInt("ColorFondo"+this.idWidget, colorFondo);
	        prefs.putInt("ColorTexto"+this.idWidget, colorTexto);
	        prefs.putBoolean("MostrarIcono"+this.idWidget, mostrarIcono);
	        prefs.commit();
	        
	        // Ponemos el result a OK y retornamos el ID
	    	Intent okResult=new Intent();
	    	okResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.idWidget);
	        this.setResult(RESULT_OK, okResult);
	
	        // Forzamos la primera actualizaci�n
	        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(contexto);
	        DesktopLabelWidgetStatic.actualizar(
	        	contexto, appWidgetManager, this.idWidget, etiquetaFinal, this.nImagen,
	        	colorFondo, colorTexto, mostrarIcono);
	        
	        // Salimos
	    	Utils.logInfo("Configuraci�n del widget completada (etiqueta="+etiquetaFinal+", icono="+this.nImagen+")");
	    	this.finish();
		}
		else if(v==(View)this.botonImagenMas)
		{
			// Cambiamos el puntero y actualizamos
			if(this.nImagen==this.maxImagenes) this.nImagen=1;
			else this.nImagen++;
			this.actualizarImagen();
		}
		else if(v==(View)this.botonImagenMenos)
		{
			// Cambiamos el puntero y actualizamos
			if(this.nImagen==1) this.nImagen=this.maxImagenes;
			else this.nImagen--;
			this.actualizarImagen();
		}
	}

	/**
	 * 
	 */
	private void actualizarImagen()
	{
		     if(this.nImagen==1)  this.imagen.setImageResource(R.drawable.icono001);
		else if(this.nImagen==2)  this.imagen.setImageResource(R.drawable.icono002);
		else if(this.nImagen==3)  this.imagen.setImageResource(R.drawable.icono003);
		else if(this.nImagen==4)  this.imagen.setImageResource(R.drawable.icono004);
		else if(this.nImagen==5)  this.imagen.setImageResource(R.drawable.icono005);
		else if(this.nImagen==6)  this.imagen.setImageResource(R.drawable.icono006);
		else if(this.nImagen==7)  this.imagen.setImageResource(R.drawable.icono007);
		else if(this.nImagen==8)  this.imagen.setImageResource(R.drawable.icono008);
		else if(this.nImagen==9)  this.imagen.setImageResource(R.drawable.icono009);
		else if(this.nImagen==10) this.imagen.setImageResource(R.drawable.icono010);
		else if(this.nImagen==11) this.imagen.setImageResource(R.drawable.icono011);
		else if(this.nImagen==12) this.imagen.setImageResource(R.drawable.icono012);
		else if(this.nImagen==13) this.imagen.setImageResource(R.drawable.icono013);
		else if(this.nImagen==14) this.imagen.setImageResource(R.drawable.icono014);
		else if(this.nImagen==15) this.imagen.setImageResource(R.drawable.icono015);
		else if(this.nImagen==16) this.imagen.setImageResource(R.drawable.icono016);
		else if(this.nImagen==17) this.imagen.setImageResource(R.drawable.icono017);
		else if(this.nImagen==18) this.imagen.setImageResource(R.drawable.icono018);
		else if(this.nImagen==19) this.imagen.setImageResource(R.drawable.icono019);
		else if(this.nImagen==20) this.imagen.setImageResource(R.drawable.icono020);
		else if(this.nImagen==21) this.imagen.setImageResource(R.drawable.icono021);
		else if(this.nImagen==22) this.imagen.setImageResource(R.drawable.icono022);
		else if(this.nImagen==23) this.imagen.setImageResource(R.drawable.icono023);
		else if(this.nImagen==24) this.imagen.setImageResource(R.drawable.icono024);
		else if(this.nImagen==25) this.imagen.setImageResource(R.drawable.icono025);
		else if(this.nImagen==26) this.imagen.setImageResource(R.drawable.icono026);
		else if(this.nImagen==27) this.imagen.setImageResource(R.drawable.icono027);
		else if(this.nImagen==28) this.imagen.setImageResource(R.drawable.icono028);
	}
}