package com.serone.desktoplabel;

// Imports necesarios
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Gestor de los eventos del widget
 * @author doctor@serone.org
 */
public class DesktopLabelWidget3xProvider extends AppWidgetProvider
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
	 * No necesario 
	 */
	//public void onDeleted(Context contexto, int[] appWidgetIds)
    //{
	//	Utils.logDebug("== onDeleted ==");
	//}
	
	/**
	 * NOTA: Si implementamos �ste m�todo no se llamar� autom�ticamente al
	 * m�todo onUpdate(), pues significa que se reciben y procesan de forma
	 * personalizada las peticiones. Los 4 intents importantes son:
	 *  - ACTION_APPWIDGET_UPDATE
	 *  - ACTION_APPWIDGET_DELETED
	 *  - ACTION_APPWIDGET_ENABLED
	 *  - ACTION_APPWIDGET_DISABLED
	 */
	//public void onReceive(Context contexto, Intent intent)
	//{
	//	Utils.logDebug("== onReceive ==");
	//}
	
	/**
	 * Actualizaci�n del Widget
	 */
    public void onUpdate(Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
		Utils.logDebug("== onUpdate ==");
		
		// Llamamos al m�todo est�tico com�n
		DesktopLabelWidgetStatic.onUpdate(contexto, appWidgetManager, appWidgetIds);
		
		// Se lo pasamos al super
		super.onUpdate(contexto, appWidgetManager, appWidgetIds);
    }
}
