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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
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
    	Context contexto, AppWidgetManager appWidgetManager, int[] appWidgetIds, int anchura, int altura)
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
    		
    		// Configuración del widget
            String etiqueta="";
            int colorFondo=Color.BLACK;
            int colorTexto=Color.WHITE;
            int icono=1;
            int posicionIcono=1;          

            // Cargamos los parámetros, modificándolos si vienen de otras versiones
    		SharedPreferences prefs=contexto.getSharedPreferences("DesktopLabel", 0);
            etiqueta=prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]");
            
            if(etiqueta!="[[ERROR]]")
            {
            	// Viene de una versión anterior a la 1.4.0 (versionCode=7)
                etiqueta=DesktopLabelWidgetStatic.actualizarValor(
                	"Etiqueta", prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]"));
                
                colorFondo=DesktopLabelWidgetStatic.actualizarValor(
                    	"ColorFondo", prefs.getInt("Estilo"+appWidgetIds[i], 0));
                colorTexto=DesktopLabelWidgetStatic.actualizarValor(
                    	"ColorTexto",prefs.getInt("Estilo"+appWidgetIds[i], 0));
                icono=DesktopLabelWidgetStatic.actualizarValor(
                    	"Icono", prefs.getInt("Icono"+appWidgetIds[i], 1));

                // Quitamos el icono si antes estaba quitado
                boolean mostrarIcono=prefs.getBoolean("MostrarIcono"+appWidgetIds[i], true);                    
                if(!mostrarIcono) icono=0;
                
        	    // Registramos
        	    Utils.logAviso("¡Se ha de cambiar la configuración de widget!");

        	    Utils.logDebug("Configuración antigua del widget:");
        	    Utils.logDebug(" - Etiqueta: "+prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]"));
        	    Utils.logDebug(" - Estilo: "+prefs.getInt("Estilo"+appWidgetIds[i], 0));
        	    Utils.logDebug(" - Icono: "+prefs.getInt("Icono"+appWidgetIds[i], 1)+" ("+(mostrarIcono?"visible":"oculto")+")");

        	    Utils.logDebug("Configuración nueva del widget:");
        	    Utils.logDebug(" - Etiqueta: "+etiqueta);
        	    Utils.logDebug(" - Color Fondo: "+colorFondo);
        	    Utils.logDebug(" - Color Texto: "+colorTexto);
        	    Utils.logDebug(" - Icono: "+icono);
        	    
    			// Borramos los datos guardados
        		SharedPreferences.Editor prefsEditor=contexto.getSharedPreferences("DesktopLabel", 0).edit();
        		
        		prefsEditor.remove("Etiqueta"+appWidgetIds[i]);
        		prefsEditor.remove("Icono"+appWidgetIds[i]);
        		prefsEditor.remove("Estilo"+appWidgetIds[i]);
        		prefsEditor.remove("MostrarIcono"+appWidgetIds[i]);

        		prefsEditor.commit();
        		
    	        // Guardamos los nuevos datos
        		prefsEditor=contexto.getSharedPreferences("DesktopLabel", 0).edit();
        		
        		prefsEditor.putString( "Widget="+appWidgetIds[i]+" (Etiqueta)", 		 etiqueta);
        		prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (ColorFondo)", 		 colorFondo);
    	        prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (ColorTexto)", 		 colorTexto);
    	        prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (Icono)", 			 icono);
    	        prefsEditor.putInt(    "Widget="+appWidgetIds[i]+" (PosicionIcono)", 	 1);

    	        // Siempre guardamos la altura y anchura
    	        prefsEditor.putInt(    "Widget="+appWidgetIds[i]+" (Altura)", 	 		 altura);
    	        prefsEditor.putInt(    "Widget="+appWidgetIds[i]+" (Anchura)", 	 		 anchura);
    	        
        		prefsEditor.commit();
            }
            else
            {
    	        // Siempre guardamos la altura y anchura
        		SharedPreferences.Editor prefsEditor=contexto.getSharedPreferences("DesktopLabel", 0).edit();
        		
    	        prefsEditor.putInt(    "Widget="+appWidgetIds[i]+" (Altura)", 	 		 altura);
    	        prefsEditor.putInt(    "Widget="+appWidgetIds[i]+" (Anchura)", 	 		 anchura);
    	        
        		prefsEditor.commit();
        		
            	// Viene de una versión 1.4.0 (versionCode=7) o posterior
                etiqueta=        prefs.getString( "Widget="+appWidgetIds[i]+" (Etiqueta)", 			"DesktopLabel");
                colorFondo=      prefs.getInt(    "Widget="+appWidgetIds[i]+" (ColorFondo)", 		Color.BLACK);
                colorTexto=      prefs.getInt(    "Widget="+appWidgetIds[i]+" (ColorTexto)", 	    Color.WHITE);
                icono=           prefs.getInt(    "Widget="+appWidgetIds[i]+" (Icono)", 			1);
                posicionIcono=   prefs.getInt(    "Widget="+appWidgetIds[i]+" (PosicionIcono)", 	1);
            }
            
            // Actualizamos
			DesktopLabelWidgetStatic.actualizar(
    			contexto, appWidgetManager, appWidgetIds[i], etiqueta, icono,
    			colorFondo, colorTexto,	anchura, altura, posicionIcono);			
		}
    	
    	Utils.logInfo("Procesados "+appWidgetIds.length+" widgets :)");
    }

	/**
	 * Actualiza de forma estática
	 */
	public static void actualizar(
		Context contexto, AppWidgetManager appWidgetManager, int idWidget,
		String etiqueta, int icono, int colorFondo,	int colorTexto,	int anchura,
		int altura, int posicionIcono)
	{
	    RemoteViews vistaActualizada=null;

	    // Creamos la vista remota
		vistaActualizada=new RemoteViews(contexto.getPackageName(),R.layout.layout_widget);	

	    // Creamos el canvas sobre el que dibujaremos
	    Utils.logDebug("Creando un canvas de "+anchura+"x"+altura+"...");

		Bitmap bitmap=dibujarCanvas(
			contexto, idWidget, etiqueta, icono, colorFondo, colorTexto,
			anchura, altura, posicionIcono);
		
		// Establecemos el canvas
		vistaActualizada.setImageViewBitmap(R.id.imagenWidget, bitmap);

	    // Creamos un intent para lanzar la actividad de reconfiguración
	    // Le añadiremos el id del widget, para saber a quien nos referimos
		Utils.logDebug("Asociando Widget id="+idWidget+" a eventos...");
		
		try
		{
	        // NOTA: Como los extras daban problemas, hemos metido el id como una
	        // uri de datos Android. Ver la documentación para más detalles.
			Intent intentClick=new Intent(contexto, DesktopLabelWidgetClick.class);
			intentClick.setAction("com.serone.desktoplabel.click");
			intentClick.setData(Uri.parse("id://"+idWidget));
			
			PendingIntent pendingIntent=PendingIntent.getActivity(contexto, 0, intentClick, 0);
	
		    // Asociamos el evento a la vista remota
			vistaActualizada.setOnClickPendingIntent(R.id.imagenWidget, pendingIntent);
		}
		catch(Exception e)
		{
			Utils.logError(e.getMessage());
		}
	    
	    // Actualizamos
		Utils.logInfo("Actualizando Widget id="+idWidget+" ("+etiqueta+")...");
		appWidgetManager.updateAppWidget(idWidget, vistaActualizada);
	}

	/**
	 * Dibuja el canvas de forma estática
	 */
	public static Bitmap dibujarCanvas(
		Context contexto, int idWidget, String etiqueta, int icono, int colorFondo,	
		int colorTexto,	int anchura, int altura, int posicionIcono)
	{
		Paint p = new Paint();
		p.setAntiAlias(true);
		
		Bitmap bitmap = Bitmap.createBitmap(anchura, altura, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		// Dibujamos el fondo
		canvas.drawColor(Color.parseColor("#00FFFFFF"));
		p.setColor(colorFondo);
		canvas.drawRoundRect(new RectF(1, 1, anchura, altura), 10, 10, p);
		
		// Seleccionamos el recurso
		int resIcono=Utils.idRecursoImagen(contexto, icono);
		
		// Dibujamos la imagen
		canvas.drawBitmap(
			BitmapFactory.decodeResource(contexto.getResources(), resIcono),
			null, new Rect(10, 10, altura-10, altura-10), p);
			
		// Dibujamos el texto. Ajustaremos el tamaño de la fuente según la longitud 
		// del texto, para que se vea bien. Podremos incluso partirlo en dos lineas
		// si es necesario, pues ahora controlamos tan fuente como coordenadas de
		// renderizado del texto. Tenemos el control ;)
		p.setColor(colorTexto);
		
		int alturaTexto=(altura/2);
		int grosorTexto=3;

		// FIXME: Corregir según el layout del widget
		
		// Escalamos el texto
		if(etiqueta.length()>(int)26)
		{
			alturaTexto=(altura/5);
			grosorTexto=2;
		}
		else if(etiqueta.length()>(int)18)
		{
			alturaTexto=(altura/4);
			grosorTexto=2;
		}
		else if(etiqueta.length()>(int)10)
		{
			alturaTexto=(altura/3);
			grosorTexto=3;
		}

		p.setStrokeWidth(grosorTexto);
		p.setTextSize(alturaTexto);
		
		canvas.drawText(etiqueta, altura, (altura/2)+(alturaTexto/2), p);
		
		return bitmap;
	}
	
	/**
	 * Convierte configuraciones entre versiones
	 * FIXME: Ésto puede ir eliminándose o modificándose entre versiones,
	 *   cuando ya no haya instalaciones de las versiones a modificar
	 */
	public static String actualizarValor(String parametro, String valorActual)
	{
		// Las cadenas las devolvemos sin modificar
		//Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorActual);
		return valorActual;
	}
	
	/**
	 * Convierte configuraciones entre versiones
	 * FIXME: Ésto puede ir eliminándose o modificándose entre versiones,
	 *   cuando ya no haya instalaciones de las versiones a modificar
	 */
	public static int actualizarValor(String parametro, int valorActual)
	{
		int valorModificado=1;		
		
		if(parametro=="ColorFondo")
		{
			// Covertimos de estilo a color
			//   0: Negro, 1: Blanco, 2: Amarillo, 3: Azul, 4: Morado,
			//   5: Naranja, 6: Rojo, 7: Verde, 8: Transparente
			switch(valorActual)
			{
				case 1:  { valorModificado=Color.WHITE; break; }
				case 2:  { valorModificado=Color.YELLOW; break; }
				case 3:  { valorModificado=Color.BLUE; break; }
				case 4:  { valorModificado=Color.MAGENTA; break; }
				case 5:  { valorModificado=Color.parseColor("#FF00FF"); break; }
				case 6:  { valorModificado=Color.RED; break; }
				case 7:  { valorModificado=Color.GREEN; break; }
				case 8:  { valorModificado=Color.TRANSPARENT; break; }
				default: { valorModificado=Color.BLACK; break; }
			}
		}
		else if(parametro=="ColorTexto")
		{
			// Covertimos de estilo a color
			//   0: Negro, 1: Blanco, 2: Amarillo, 3: Azul, 4: Morado,
			//   5: Naranja, 6: Rojo, 7: Verde, 8: Transparente
			switch(valorActual)
			{
				case 1:  { valorModificado=Color.BLACK; break; }
				case 2:  { valorModificado=Color.BLACK; break; }
				case 3:  { valorModificado=Color.WHITE; break; }
				case 4:  { valorModificado=Color.BLACK; break; }
				case 5:  { valorModificado=Color.BLACK; break; }
				case 6:  { valorModificado=Color.WHITE; break; }
				case 7:  { valorModificado=Color.WHITE; break; }
				case 8:  { valorModificado=Color.BLACK; break; }
				default: { valorModificado=Color.WHITE; break; }
			}
		}
		else if(parametro=="Icono")
		{
			// El icono no cambia
			valorModificado=valorActual;
		}

		//Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorModificado);
		return valorModificado;
		
	}
	
	/**
	 * Convierte configuraciones entre versiones
	 * FIXME: Ésto puede ir eliminándose o modificándose entre versiones,
	 *   cuando ya no haya instalaciones de las versiones a modificar
	 */
	public static boolean actualizarValor(String parametro, int valorActual, boolean esBooleano)
	{
		boolean valorModificado=true;
		
		 if(parametro=="MostrarIcono")
		{
			// Valor directo
			valorModificado=(valorActual==1?true:false);
		}

		//Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorModificado);
		return valorModificado;
		
	}
}
