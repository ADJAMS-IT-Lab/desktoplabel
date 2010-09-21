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

		// Creamos la vista desde el layout, según el estilo		
		switch(estilo)
		{
			case 1:
			{	
				if(mostrarIcono)
				{
					// Blanco con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_blanco);
				}
				else
				{
					// Blanco sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_blanco);	
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLACK);
			    
				break;
			}

			case 2:
			{	
				if(mostrarIcono)
				{
					// Amarillo con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_amarillo);
				}
				else
				{
					// Amarillo sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_amarillo);			
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLACK);
			    
				break;
			}

			case 3:
			{	
				if(mostrarIcono)
				{
					// Azul con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_azul);
				}
				else
				{
					// Azul sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_azul);				
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
			    
				break;
			}

			case 4:
			{	
				if(mostrarIcono)
				{
					// Morado con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_morado);
				}
				else
				{
					// Morado sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_morado);			
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
			    
				break;
			}

			case 5:
			{	
				if(mostrarIcono)
				{
					// Naranja con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_naranja);	
				}
				else
				{
					// Naranja sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_naranja);			
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLACK);
			    
				break;
			}

			case 6:
			{	
				if(mostrarIcono)
				{
					// Rojo con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_rojo);			
				}
				else
				{
					// Rojo sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_rojo);					
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
			    
				break;
			}

			case 7:
			{	
				if(mostrarIcono)
				{
					// Verde con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_verde);
				}
				else
				{
					// Verde sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_verde);			
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
			    
				break;
			}
			
			case 8:
			{
				if(mostrarIcono)
				{
					// Transparente con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_transparente);
				}
				else
				{
					// Transparente sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_transparente);		
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLACK);
			    
				break;
			}			
			default:
			{
				if(mostrarIcono)
				{
					// Negro con icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_con_icono_negro);
				}
				else
				{
					// Negro sin icono
					vistaActualizada=new RemoteViews(contexto.getPackageName(),
							R.layout.layout_widget_sin_icono_negro);
				}
				
				// Color de texto
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.WHITE);
			    
				break;
			}
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

	    // Creamos un intent para lanzar la actividad de reconfiguración
	    // Le añadiremos el id del widget, para saber a quien nos referimos
		Utils.logInfo("Asociando Widget id="+idWidget+" a eventos...");
		
		Intent intentClick=new Intent(contexto, DesktopLabelWidgetClick.class);
		intentClick.putExtra("idWidget", idWidget);
		
		PendingIntent pendingIntent=PendingIntent.getActivity(
			contexto, /*RequestCode*/0, intentClick, /*Flags*/0);

	    // Asociamos el evento a la vista remota
		vistaActualizada.setOnClickPendingIntent(R.id.layoutWidget, pendingIntent);
		
	    // Actualizamos
		Utils.logInfo("Actualizando Widget id="+idWidget+" ("+etiqueta+")...");
		appWidgetManager.updateAppWidget(idWidget, vistaActualizada);
	}
}
