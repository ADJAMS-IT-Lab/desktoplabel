package com.serone.desktoplabel;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import java.text.DecimalFormat;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Configurador del widget en tiempo de ejecución
 * @author doctor@serone.org
 */
public class SeroneWidgetIcono extends Activity implements OnItemClickListener
{
	// Controles
	private GridView gridViewIconos=null;
	
    /**
     * Evento de creación
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context contexto=this.getApplicationContext();
        
        // Especificamos el layout
        setContentView(R.layout.layout_serone_icono);

        // Cogemos los controles
        this.gridViewIconos=(GridView)findViewById(R.id.gridViewIconos);
        
        // Decimos que la matriz contendrá imágenes
        SeroneWidgetIconoAdapter adaptador=new SeroneWidgetIconoAdapter(contexto);
        
        this.gridViewIconos.setAdapter(adaptador);        
        this.gridViewIconos.setColumnWidth(36);
        this.gridViewIconos.setHorizontalSpacing(5);
        this.gridViewIconos.setVerticalSpacing(5);
        
        // Asociamos a eventos
        this.gridViewIconos.setOnItemClickListener(this);
    }

    /**
     * Eventos del botón
     */
	public void onItemClick(AdapterView<?> adaptador, View view, int posicion, long id)
	{
		// Retornamos el icono
		this.setResult(posicion);
    	this.finish();
	}
}