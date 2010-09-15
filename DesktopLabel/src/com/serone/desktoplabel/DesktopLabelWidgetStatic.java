package com.serone.desktoplabel;

// Imports necesarios
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
    public static void onUpdate(Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds)
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
            int colorFondo=prefs.getInt("ColorFondo"+appWidgetIds[i], 0);
            int colorTexto=prefs.getInt("ColorTexto"+appWidgetIds[i], 1);
            boolean mostrarIcono=prefs.getBoolean("MostrarIcono"+appWidgetIds[i], true);

            // Actualizamos
    		DesktopLabelWidgetStatic.actualizar(
    			contexto, appWidgetManager, appWidgetIds[i], etiqueta, nImagen,
    			colorFondo, colorTexto, mostrarIcono);
		}
    	
    	Utils.logInfo("Procesados "+appWidgetIds.length+" widgets :)");
    }

	/**
	 * Actualiza de forma estática
	 */
	public static void actualizar(
		Context contexto, AppWidgetManager appWidgetManager, int idWidget, String etiqueta,
		int nImagen, int colorFondo, int colorTexto, boolean mostrarIcono)
	{		
		// Creamos la vista desde el layout, según el color de fondo y el modo de icono
	    RemoteViews vistaActualizada=null;

		if(mostrarIcono)
			vistaActualizada=new RemoteViews(contexto.getPackageName(), R.layout.layout_widget_con_icono);
		else vistaActualizada=new RemoteViews(contexto.getPackageName(), R.layout.layout_widget_sin_icono);
		
		switch(colorFondo)
		{
			case 1:
			{
				// Mostramos el layout con fondo blanco
				vistaActualizada.setBitmap(
					R.id.layoutWidget, "setBackgroundDrawable",
					((BitmapDrawable)contexto.getResources().getDrawable(R.drawable.fondo_blanco)).getBitmap());				
				break;
			}
			case 2:
			{
				// Mostramos el layout con fondo transparente 
				vistaActualizada.setBitmap(
					R.id.layoutWidget, "setBackgroundDrawable",
					((BitmapDrawable)contexto.getResources().getDrawable(R.drawable.fondo_rojo)).getBitmap());
				break;
			}
			default:
			{
				// Mostramos el layout con fondo negro 
				vistaActualizada.setBitmap(
					R.id.layoutWidget, "setBackgroundDrawable",
					((BitmapDrawable)contexto.getResources().getDrawable(R.drawable.fondo_negro)).getBitmap());
				break;
			}
		}

		// Ponemos el color del texto
		switch(colorTexto)
		{
			case 1:
			{
				// Blanco
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
				break;
			}
			default:
			{
				// Negro 
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLACK);
				break;
			}
		}
		
	    // Ponemos la etiqueta
	    vistaActualizada.setTextViewText(R.id.etiquetaWidget, etiqueta);

	    // Cambiamos la imagen
	    if(mostrarIcono)
	    {
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
	    
	    // Actualizamos
		Utils.logInfo("Actualizando Widget id="+idWidget+" ("+etiqueta+")");
		appWidgetManager.updateAppWidget(idWidget, vistaActualizada);
	}
}
