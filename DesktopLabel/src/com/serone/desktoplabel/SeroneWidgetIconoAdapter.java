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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Adaptador para que el grid muestre imágenes
 * @author doctor@serone.org
 */
public class SeroneWidgetIconoAdapter extends BaseAdapter
{
    private Context contexto=null;

	/**
	 * 
	 */
    public SeroneWidgetIconoAdapter(Context contexto)
    {
    	this.contexto = contexto;
    }

	/**
	 * 
	 */
	public int getCount()
	{
		// Número de elementos
		return (Utils.fin-Utils.ini);
	}

	/**
	 * 
	 */
	public Object getItem(int arg0)
	{
		return null;
	}

	/**
	 * 
	 */
	public long getItemId(int position)
	{
		return 0;
	}

	/**
	 * 
	 */
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// Original: http://developer.android.com/guide/tutorials/views/hello-gridview.html
		// Modificado por el Dr. SeROne
        ImageView imageView;
        
        // if it's not recycled, initialize some attributes
        if (convertView == null)
        {
        	// Creamos la imageView
            imageView = new ImageView(this.contexto);
            imageView.setLayoutParams(new GridView.LayoutParams(36, 36));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
        	// Reciclamos
            imageView = (ImageView) convertView;
        }

    	try
    	{
        	// Cogemos el ID del recurso 	
        	int idImagen=Utils.idRecursoImagen(this.contexto, position+Utils.ini);

            // Establecemos el recurso
            imageView.setImageResource(idImagen);
            
            return imageView;
    	}
    	catch(Exception e)
    	{
    		Utils.logError(e.getMessage());
    		return null;
    	}
	}
}