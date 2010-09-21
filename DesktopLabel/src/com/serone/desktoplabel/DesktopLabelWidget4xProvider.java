package com.serone.desktoplabel;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Gestor de los eventos del widget
 * @author doctor@serone.org
 */
public class DesktopLabelWidget4xProvider extends AppWidgetProvider
{
	/**
	 * No necesario 
	 */
	//public void onEnabled(Context contexto)
    //{
	//	Utils.logDebug("== onEnabled ==");
    //}

	/**
	 * No necesario 
	 */
	//public void onDisabled(Context contexto) 
    //{
	//	Utils.logDebug("== onDisabled ==");
    //}
	
	/**
	 * Limpiamos recursos 
	 */
	public void onDeleted(Context contexto, int[] appWidgetIds)
    {
		Utils.logDebug("== onDeleted 4x ==");

    	// Por cada ID que haya
    	for(int i=0; i<appWidgetIds.length; i++)
    	{
    		Utils.logAviso("Borrando configuración del widget #"+appWidgetIds[i]);

			// Borramos los datos guardados
    		SharedPreferences.Editor prefs=contexto.getSharedPreferences("DesktopLabel", 0).edit();
	        prefs.remove("Etiqueta"+appWidgetIds[i]);
	        prefs.remove("Icono"+appWidgetIds[i]);
	        prefs.remove("Estilo"+appWidgetIds[i]);
	        prefs.remove("MostrarIcono"+appWidgetIds[i]);
	        prefs.commit();
    	}
	}
	
	/**
	 * NOTA: Si implementamos éste método no se llamará automáticamente al
	 * método onUpdate(), pues significa que se reciben y procesan de forma
	 * personalizada las peticiones. Los 4 intents importantes son:
	 *  - ACTION_APPWIDGET_UPDATE
	 *  - ACTION_APPWIDGET_DELETED
	 *  - ACTION_APPWIDGET_ENABLED
	 *  - ACTION_APPWIDGET_DISABLED
	 */
	public void onReceive(Context contexto, Intent intent)
	{
		Utils.logDebug("== onReceive 4x ("+intent.getAction().toString()+") ==");
		
		// Según la Intent...
		if(intent.getAction().equals("..."))
		{	
			// Hacemos lo que toque...
		}
		else
		{
			// Actualizamos, se lo pasamos al super
			super.onReceive(contexto, intent);
		}		
	}

	/**
	 * Actualización del Widget
	 */
    public void onUpdate(Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
		Utils.logDebug("== onUpdate 4x ==");
		
		// Llamamos al método estático común
		DesktopLabelWidgetStatic.onUpdate(contexto, appWidgetManager, appWidgetIds);

		// Se lo pasamos al super
		super.onUpdate(contexto, appWidgetManager, appWidgetIds);
    }
}
