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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Configurador del widget en tiempo de ejecución
 * @author doctor@serone.org
 */
public class SeroneWidgetColor extends Activity implements OnClickListener
{
	// Controles
	private Button botonOK;
	
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
			int colorActual=Color.BLACK;
			
			// ...
			
			// Retornamos el color
			this.setResult(colorActual);
        	this.finish();
		}
	}
}