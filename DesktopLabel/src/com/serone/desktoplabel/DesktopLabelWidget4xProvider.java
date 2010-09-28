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

			// Borramos los datos guardados (previos a la 1.4.0)
    		SharedPreferences.Editor prefs=contexto.getSharedPreferences("DesktopLabel", 0).edit();
	        prefs.remove("Etiqueta"+appWidgetIds[i]);
	        prefs.remove("Icono"+appWidgetIds[i]);
	        prefs.remove("Estilo"+appWidgetIds[i]);
	        prefs.remove("MostrarIcono"+appWidgetIds[i]);

			// Borramos los datos guardados (1.4.0 y posterior)
    		prefs.remove("Widget="+appWidgetIds[i]+" (Etiqueta)");
    		prefs.remove("Widget="+appWidgetIds[i]+" (ColorFondo)");
	        prefs.remove("Widget="+appWidgetIds[i]+" (MostrarColorFondo)");
	        prefs.remove("Widget="+appWidgetIds[i]+" (ColorTexto)");
	        prefs.remove("Widget="+appWidgetIds[i]+" (MostrarColorTexto)");
	        prefs.remove("Widget="+appWidgetIds[i]+" (Icono)");
	        prefs.remove("Widget="+appWidgetIds[i]+" (MostrarIcono)"); 
	        
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

		// Cogemos las dimensiones concretas
		int altura=Utils.medidaWidgetAPixles(contexto, "altura", 1);
		int anchura=Utils.medidaWidgetAPixles(contexto, "anchura", 4);
		
		// Modificamos con el margen del layout (15x25)
		altura-=50;
		anchura-=30;
		
		// Llamamos al método estático común
		DesktopLabelWidgetStatic.onUpdate(contexto, appWidgetManager, appWidgetIds, anchura, altura);

		// Se lo pasamos al super
		super.onUpdate(contexto, appWidgetManager, appWidgetIds);
    }
}
