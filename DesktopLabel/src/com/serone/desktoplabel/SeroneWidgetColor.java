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
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Configurador del widget en tiempo de ejecución
 * @author doctor@serone.org
 */
public class SeroneWidgetColor extends Activity implements OnClickListener, OnSeekBarChangeListener
{
	// Controles
	private Button botonOK;
	private SeekBar seekBarRojo;
	private SeekBar seekBarVerde;
	private SeekBar seekBarAzul;
	private SeekBar seekBarTransparencia;
	private TextView textViewVistaPrevia;
	
    /**
     * Evento de creación
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context contexto=this.getApplicationContext();
        
        // Especificamos el layout
        setContentView(R.layout.layout_serone_color);

        // Cogemos los controles
        this.botonOK=(Button)findViewById(R.id.botonAceptar);
        this.botonOK.setOnClickListener(this);
        
        this.seekBarRojo=(SeekBar)findViewById(R.id.seekBarRojo);
        this.seekBarRojo.setOnSeekBarChangeListener(this);
        this.seekBarVerde=(SeekBar)findViewById(R.id.seekBarVerde);
        this.seekBarVerde.setOnSeekBarChangeListener(this);
        this.seekBarAzul=(SeekBar)findViewById(R.id.seekBarAzul);
        this.seekBarAzul.setOnSeekBarChangeListener(this);
        this.seekBarTransparencia=(SeekBar)findViewById(R.id.seekBarTransparencia);
        this.seekBarTransparencia.setOnSeekBarChangeListener(this);
        
        this.textViewVistaPrevia=(TextView)findViewById(R.id.textViewVistaPrevia);
        
        // Ajustamos las seekbars de 0 a 255
        this.seekBarRojo.setMax(255);
        this.seekBarVerde.setMax(255);
        this.seekBarAzul.setMax(255);
        this.seekBarTransparencia.setMax(255);
        
        // Cogemos el color inicial y ajustamos las seekbars
        // FIXME: Hacer ésto
        this.seekBarRojo.setProgress(0);
        this.seekBarVerde.setProgress(0);
        this.seekBarAzul.setProgress(0);
        this.seekBarTransparencia.setProgress(255);
        
        // Actualizamos
        this.actualizarColor();
    }

    /**
     * Actualiza la etiqueta de la vista previa con el color actual
     */
    private void actualizarColor()
    {
    	// Cogemos el color
		int colorActual=Color.argb
		(
			this.seekBarTransparencia.getProgress(),
			this.seekBarRojo.getProgress(),
			this.seekBarVerde.getProgress(),
			this.seekBarAzul.getProgress()
		);
		
		// Pintamos
    	this.textViewVistaPrevia.setBackgroundColor(colorActual);
    }
    
    /**
     * Eventos del botón
     */
	public void onClick(View v)
	{
		// Según el botón
		if(v==(View)this.botonOK)
		{
			// Cogemos el color
			int colorActual=Color.argb
			(
				this.seekBarTransparencia.getProgress(),
				this.seekBarRojo.getProgress(),
				this.seekBarVerde.getProgress(),
				this.seekBarAzul.getProgress()
			);
			
			// Retornamos el color
			this.setResult(colorActual);
        	this.finish();
		}
	}

	/**
	 * Evento de actualización de la seekbar
	 * @param seekBar
	 * @param arg1
	 * @param arg2
	 */
	public void onProgressChanged(SeekBar seekBar, int arg1, boolean arg2)
	{	
		// Actualizamos el color
		this.actualizarColor();
	}

	/**
	 * 
	 */
	public void onStartTrackingTouch(SeekBar seekBar)
	{
	}

	/**
	 * 
	 */
	public void onStopTrackingTouch(SeekBar seekBar)
	{	
	}
}