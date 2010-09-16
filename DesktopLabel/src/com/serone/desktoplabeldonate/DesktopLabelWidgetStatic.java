package com.serone.desktoplabeldonate;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import com.serone.desktoplabel.R;

import android.appwidget.AppWidgetManager;
import android.content.Context;
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

		switch(colorFondo)
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
			case 2:
			{
				// Amarillo
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.YELLOW);
				break;
			}
			case 3:
			{
				// Azul
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.BLUE);
				break;
			}
			case 4:
			{
				// Morado
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.MAGENTA);
				break;
			}
			case 5:
			{
				// Naranja
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.argb(255, 255, 0, 255));
				break;
			}
			case 6:
			{
				// Rojo
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.RED);
				break;
			}
			case 7:
			{
				// Verde
			    vistaActualizada.setTextColor(R.id.etiquetaWidget, Color.GREEN);
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
