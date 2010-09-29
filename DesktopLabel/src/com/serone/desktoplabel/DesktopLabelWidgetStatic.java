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
    		
        	// Cargamos las shared preferences
            SharedPreferences prefs=contexto.getSharedPreferences("DesktopLabel", 0);
            
            String etiqueta="";
            int colorFondo=Color.BLACK; boolean colorFondoActivo=true;
            int colorTexto=Color.WHITE; boolean colorTextoActivo=true;
            int icono=1; boolean iconoActivo=true;            

            // Cargamos los parámetros, modificándolos si vienen de otras versiones
            etiqueta=prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]");
            
            if(etiqueta=="[[ERROR]]")
            {
            	// Viene de una versión anterior a la 1.4.0 (versionCode=7)
                etiqueta=DesktopLabelWidgetStatic.actualizarValor(
                	"Etiqueta", prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]"));
                
                colorFondo=DesktopLabelWidgetStatic.actualizarValor(
                    	"ColorFondo", prefs.getInt("Estilo"+appWidgetIds[i], 0));
                colorFondoActivo=DesktopLabelWidgetStatic.actualizarValor(
                    	"MostrarColorFondo", prefs.getInt("Estilo"+appWidgetIds[i], 0), true);
                
                colorTexto=DesktopLabelWidgetStatic.actualizarValor(
                    	"ColorTexto",prefs.getInt("Estilo"+appWidgetIds[i], 0));
                colorTextoActivo=DesktopLabelWidgetStatic.actualizarValor(
                    	"MostrarColorTexto", prefs.getInt("Estilo"+appWidgetIds[i], 0), true);
                
                icono=DesktopLabelWidgetStatic.actualizarValor(
                    	"Icono", prefs.getInt("Icono"+appWidgetIds[i], 1));
                iconoActivo=DesktopLabelWidgetStatic.actualizarValor(
                    	"MostrarIcono", (prefs.getBoolean("MostrarIcono"+appWidgetIds[i], true)?1:0), true);
                
        	    // Registramos
        	    Utils.logAviso("¡Se ha de cambiar la configuración de widget!");
        	    
        	    Utils.logDebug("Configuración antigua del widget:");
        	    Utils.logDebug(" - Etiqueta: "+prefs.getString("Etiqueta"+appWidgetIds[i], "[[ERROR]]"));
        	    Utils.logDebug(" - Estilo: "+prefs.getInt("Estilo"+appWidgetIds[i], 0));
        	    Utils.logDebug(" - Icono: "+prefs.getInt("Icono"+appWidgetIds[i], 1)+" ("+
        	    	(prefs.getBoolean("MostrarIcono"+appWidgetIds[i], true)?"visible":"oculto")+")");

        	    Utils.logDebug("Configuración nueva del widget:");
        	    Utils.logDebug(" - Etiqueta: "+etiqueta);
        	    Utils.logDebug(" - Color Fondo: "+colorFondo+" ("+(colorFondoActivo?"visible":"transparente")+")");
        	    Utils.logDebug(" - Color Texto: "+colorTexto+" ("+(colorTextoActivo?"visible":"transparente")+")");
        	    Utils.logDebug(" - Icono: "+icono+" ("+(iconoActivo?"visible":"oculto")+")");
        	    
    			// Borramos los datos guardados
        		SharedPreferences.Editor prefsEditor=contexto.getSharedPreferences("DesktopLabel", 0).edit();
        		
        		prefsEditor.remove("Etiqueta"+appWidgetIds[i]);
        		prefsEditor.remove("Icono"+appWidgetIds[i]);
        		prefsEditor.remove("Estilo"+appWidgetIds[i]);
        		prefsEditor.remove("MostrarIcono"+appWidgetIds[i]);

    	        // Guardamos los nuevos datos
        		prefsEditor.putString( "Widget="+appWidgetIds[i]+" (Etiqueta)", 		 etiqueta);

        		prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (ColorFondo)", 		 colorFondo);
    	        prefsEditor.putBoolean("Widget="+appWidgetIds[i]+" (MostrarColorFondo)", colorFondoActivo);

    	        prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (ColorTexto)", 		 colorTexto);
    	        prefsEditor.putBoolean("Widget="+appWidgetIds[i]+" (MostrarColorTexto)", colorTextoActivo);
    	        
    	        prefsEditor.putInt(	   "Widget="+appWidgetIds[i]+" (Icono)", 			 icono);
    	        prefsEditor.putBoolean("Widget="+appWidgetIds[i]+" (MostrarIcono)", 	 iconoActivo);    	        
        		
        		// Commit final
        		prefsEditor.commit();
            }
            else
            {
            	// Viene de una versión 1.4.0 (versionCode=7) o posterior
                etiqueta=        prefs.getString( "Widget="+appWidgetIds[i]+" (Etiqueta)", 			"DesktopLabel");
                colorFondo=      prefs.getInt(    "Widget="+appWidgetIds[i]+" (ColorFondo)", 		Color.BLACK);
                colorFondoActivo=prefs.getBoolean("Widget="+appWidgetIds[i]+" (MostrarColorFondo)", true);
                colorTexto=      prefs.getInt(    "Widget="+appWidgetIds[i]+" (ColorTexto)", 	    Color.WHITE);
                colorTextoActivo=prefs.getBoolean("Widget="+appWidgetIds[i]+" (MostrarColorTexto)", true);
                icono=           prefs.getInt(    "Widget="+appWidgetIds[i]+" (Icono)", 			1);
                iconoActivo=     prefs.getBoolean("Widget="+appWidgetIds[i]+" (MostrarIcono)", 		true);
            }
            
            // Actualizamos
			DesktopLabelWidgetStatic.actualizar(
    			contexto, appWidgetManager, appWidgetIds[i], etiqueta, icono, iconoActivo,
    			colorFondo, colorFondoActivo, colorTexto, colorTextoActivo,
    			anchura, altura);			
		}
    	
    	Utils.logInfo("Procesados "+appWidgetIds.length+" widgets :)");
    }

	/**
	 * Actualiza de forma estática
	 */
	public static void actualizar(
		Context contexto, AppWidgetManager appWidgetManager, int idWidget,
		String etiqueta, int icono, boolean iconoActivo, int colorFondo,
		boolean colorFondoActivo, int colorTexto, boolean colorTextoActivo,
		int anchura, int altura)
	{
	    RemoteViews vistaActualizada=null;

	    colorTexto=Color.WHITE;
	    colorFondo=Color.BLACK;
	    
	    Utils.logDebug("Creando un canvas de "+anchura+"x"+altura+"...");
	    
	    // Creamos la vista remota
		vistaActualizada=new RemoteViews(contexto.getPackageName(),R.layout.layout_widget);	

	    // Creamos el canvas sobre el que dibujaremos
		Paint p = new Paint();
		
		Bitmap bitmap = Bitmap.createBitmap(anchura, altura, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		
		// Dibujamos el fondo
		canvas.drawColor(colorFondo);
		
		// Seleccionamos el recurso
		int resIcono=1;
		
		     if(icono==1)  resIcono=R.drawable.icono001;
		else if(icono==2)  resIcono=R.drawable.icono002;
		else if(icono==3)  resIcono=R.drawable.icono003;
		else if(icono==4)  resIcono=R.drawable.icono004;
		else if(icono==5)  resIcono=R.drawable.icono005;
		else if(icono==6)  resIcono=R.drawable.icono006;
		else if(icono==7)  resIcono=R.drawable.icono007;
		else if(icono==8)  resIcono=R.drawable.icono008;
		else if(icono==9)  resIcono=R.drawable.icono009;
		else if(icono==10) resIcono=R.drawable.icono010;
		else if(icono==11) resIcono=R.drawable.icono011;
		else if(icono==12) resIcono=R.drawable.icono012;
		else if(icono==13) resIcono=R.drawable.icono013;
		else if(icono==14) resIcono=R.drawable.icono014;
		else if(icono==15) resIcono=R.drawable.icono015;
		else if(icono==16) resIcono=R.drawable.icono016;
		else if(icono==17) resIcono=R.drawable.icono017;
		else if(icono==18) resIcono=R.drawable.icono018;
		else if(icono==19) resIcono=R.drawable.icono019;
		else if(icono==20) resIcono=R.drawable.icono020;
		else if(icono==21) resIcono=R.drawable.icono021;
		else if(icono==22) resIcono=R.drawable.icono022;
		else if(icono==23) resIcono=R.drawable.icono023;
		else if(icono==24) resIcono=R.drawable.icono024;
		else if(icono==25) resIcono=R.drawable.icono025;
		else if(icono==26) resIcono=R.drawable.icono026;
		else if(icono==27) resIcono=R.drawable.icono027;
		else if(icono==28) resIcono=R.drawable.icono028;
		
		// Dibujamos la imagen
		canvas.drawBitmap(
			BitmapFactory.decodeResource(contexto.getResources(), resIcono),
			null, new Rect(10, 10, altura-10, altura-10), p);
			
		// Dibujamos el texto. Ajustaremos el tamaño de la fuente según la longitud 
		// del texto, para que se vea bien. Podremos incluso partirlo en dos lineas
		// si es necesario, pues ahora controlamos tan fuente como coordenadas de
		// renderizado del texto. Tenemos el control ;)
		p.setAntiAlias(true);
		p.setColor(colorTexto);
		
		// FIXME: Evaluar las posiciones y dimensiones según tamaño
		// FIXME: Meter más saltos para más tamaños, para que escale mejor
		
		if(etiqueta.length()>19)
		{
			p.setStrokeWidth(3);
			p.setTextSize(altura/2);
			canvas.drawText(etiqueta, altura, ((altura/4)*3), p);
		}
		else if(etiqueta.length()>16)
		{
			p.setStrokeWidth(3);
			p.setTextSize(altura/2);
			canvas.drawText(etiqueta, altura, ((altura/4)*3), p);
		}
		else if(etiqueta.length()>13)
		{
			p.setStrokeWidth(3);
			p.setTextSize(altura/2);
			canvas.drawText(etiqueta, altura, ((altura/4)*3), p);
		}
		
		// Establecemos el canvas
		vistaActualizada.setImageViewBitmap(R.id.imagenWidget, bitmap);

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
	 * Convierte configuraciones entre versiones
	 * FIXME: Ésto puede ir eliminándose o modificándose entre versiones,
	 *   cuando ya no haya instalaciones de las versiones a modificar
	 */
	public static String actualizarValor(String parametro, String valorActual)
	{
		// Las cadenas las devolvemos sin modificar
		Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorActual);
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
		else if(parametro=="Icono")
		{
			// El icono no cambia
			valorModificado=valorActual;
		}

		Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorModificado);
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
		

		if(parametro=="MostrarColorFondo")
		{
			// Antes no se podía ocultar, es siempre true
			valorModificado=true;
		}
		else if(parametro=="MostrarColorTexto")
		{
			// Antes no se podía ocultar, es siempre true
			valorModificado=true;
		}
		else if(parametro=="MostrarIcono")
		{
			// Valor directo
			valorModificado=(valorActual==1?true:false);
		}

		Utils.logDebug("actualizarValor('"+parametro+"', "+valorActual+") = "+valorModificado);
		return valorModificado;
		
	}
}
