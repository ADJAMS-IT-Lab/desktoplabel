package com.serone.desktoplabel;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import com.serone.desktoplabel.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Gestor de los eventos del widget
 * @author doctor@serone.org
 */
public class DesktopLabelWidgetStatic
{
	/**
	 * Actualización del Widget
	 */
    public static void onUpdate(
    	Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
		Utils.logDebug("== onUpdate (static) ==");
		
    	// Si no se han dado IDs...
    	if(appWidgetIds.length==0)
    	{
    		Utils.logError("¡No se han dado IDs válidos!");
    		return;
    	}
    	
    	// Por cada ID que haya
    	for(int i=0; i<appWidgetIds.length; i++)
    	{
    		Utils.logInfo("Procesando widget #"+appWidgetIds[i]+"...");
    		
        	// Cargamos la etiqueta
            SharedPreferences prefs=contexto.getSharedPreferences("DesktopLabel", 0);
            
            String etiqueta=prefs.getString("Etiqueta"+appWidgetIds[i], "DesktopLabel");
            int nImagen=prefs.getInt("Icono"+appWidgetIds[i], 1);
            int estilo=prefs.getInt("Estilo"+appWidgetIds[i], 1);
            boolean mostrarIcono=prefs.getBoolean("MostrarIcono"+appWidgetIds[i], true);

            // Actualizamos
			DesktopLabelWidgetStatic.actualizar(
    			contexto, appWidgetManager, appWidgetIds[i], etiqueta, nImagen,
    			estilo, mostrarIcono);			
		}
    	
    	Utils.logInfo("Procesados "+appWidgetIds.length+" widgets :)");
    }

	/**
	 * Actualiza de forma estática
	 */
	public static void actualizar(
		Context contexto, AppWidgetManager appWidgetManager, int idWidget, String etiqueta,
		int nImagen, int estilo, boolean mostrarIcono)
	{
	    RemoteViews vistaActualizada=null;

		// Creamos la vista desde el layout, según los parámetros dados
		if(mostrarIcono)
		{
			// Con icono
			vistaActualizada=new RemoteViews(contexto.getPackageName(),R.layout.layout_widget_con_icono);			

		    // Cambiamos la imagen
			     if(nImagen==1)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono001);
			else if(nImagen==2)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono002);
			else if(nImagen==3)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono003);
			else if(nImagen==4)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono004);
			else if(nImagen==5)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono005);
			else if(nImagen==6)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono006);
			else if(nImagen==7)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono007);
			else if(nImagen==8)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono008);
			else if(nImagen==9)  vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono009);
			else if(nImagen==10) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono010);
			else if(nImagen==11) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono011);
			else if(nImagen==12) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono012);
			else if(nImagen==13) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono013);
			else if(nImagen==14) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono014);
			else if(nImagen==15) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono015);
			else if(nImagen==16) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono016);
			else if(nImagen==17) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono017);
			else if(nImagen==18) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono018);
			else if(nImagen==19) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono019);
			else if(nImagen==20) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono020);
			else if(nImagen==21) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono021);
			else if(nImagen==22) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono022);
			else if(nImagen==23) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono023);
			else if(nImagen==24) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono024);
			else if(nImagen==25) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono025);
			else if(nImagen==26) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono026);
			else if(nImagen==27) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono027);
			else if(nImagen==28) vistaActualizada.setImageViewResource(R.id.imagenWidget, R.drawable.icono028);
		}
		else
		{
			// Sin icono
			vistaActualizada=new RemoteViews(contexto.getPackageName(),	R.layout.layout_widget_sin_icono);	
		}

		// Ajustamos el tamaño de la fuente según la longitud del texto
		     if(etiqueta.length()>34) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", 10);
		else if(etiqueta.length()>31) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", (float) 12.5);
		else if(etiqueta.length()>28) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", 15);
		else if(etiqueta.length()>25) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", (float) 17.5);
		else if(etiqueta.length()>22) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", 20);
		else if(etiqueta.length()>19) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", (float) 22.5);
		else if(etiqueta.length()>16) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", 25);
		else if(etiqueta.length()>13) vistaActualizada.setFloat(R.id.etiquetaWidget, "setTextSize", (float) 27.5);
		
	    // Ponemos la etiqueta
	    vistaActualizada.setTextViewText(R.id.etiquetaWidget, etiqueta);

	    // Creamos un intent para lanzar la actividad de reconfiguración
	    // Le añadiremos el id del widget, para saber a quien nos referimos
		Utils.logInfo("Asociando Widget id="+idWidget+" a eventos...");
		
		try
		{
	        // NOTA: Como los extras daban problemas, hemos metido el id como una
	        // uri de datos Android. Ver la documentación para más detalles.
			Intent intentClick=new Intent(contexto, DesktopLabelWidgetClick.class);
			intentClick.setAction("com.serone.desktoplabel.click");
			intentClick.setData(Uri.parse("id://"+idWidget));
			
			PendingIntent pendingIntent=PendingIntent.getActivity(
				contexto, /*RequestCode*/0, intentClick, 0);
	
		    // Asociamos el evento a la vista remota
			vistaActualizada.setOnClickPendingIntent(R.id.layoutWidget, pendingIntent);
		}
		catch(Exception e)
		{
			Utils.logError(e.getMessage());
		}
		
	    // Actualizamos
		Utils.logInfo("Actualizando Widget id="+idWidget+" ("+etiqueta+")...");
		appWidgetManager.updateAppWidget(idWidget, vistaActualizada);
	}
}
