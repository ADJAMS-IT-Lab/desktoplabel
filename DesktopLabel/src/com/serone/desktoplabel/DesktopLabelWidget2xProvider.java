package com.serone.desktoplabel;

// Imports necesarios
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

/**
 * Gestor de los eventos del widget
 * @author doctor@serone.org
 */
public class DesktopLabelWidget2xProvider extends AppWidgetProvider
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
	 * NOTA: Si implementamos éste método no se llamará automáticamente al
	 * método onUpdate(), pues significa que se reciben y procesan de forma
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
	 * Actualización del Widget
	 */
    public void onUpdate(Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
		Utils.logDebug("== onUpdate ==");
		
		// Llamamos al método estático común
		DesktopLabelWidgetStatic.onUpdate(contexto, appWidgetManager, appWidgetIds);
		
		// Se lo pasamos al super
		super.onUpdate(contexto, appWidgetManager, appWidgetIds);
    }
}
