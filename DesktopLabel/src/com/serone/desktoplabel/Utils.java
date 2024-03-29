package com.serone.desktoplabel;

//NOTA DE LICENCIA
//Bajo licencia GNU GPL 3 o posterior
//Ver gpl.html

// Imports necesarios
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Utilidades
 * @author doctor@serone.org
 */
public class Utils
{
	// Configuración del log
	public static String logFuente="Serone";
	public static int logNivel=4; // Desde 1 (error) hasta 4 (debug), 0=desactivado

    // Configuración global
	public static String nombreBase="icono";	// Base del recurso
	public static int ini=1;					// Número inicial de la secuencia
	public static int fin=28;					// Número final
	public static int numCeros=3; 				// Número de digitos (para rellenar con ceros)

	/**
	 * Devuelve el nombre de un recurso
	 */
	public static int idRecursoImagen(Context contexto, int numImagen)
	{
    	// Nombre del recurso
    	String num=Integer.toString(numImagen+Utils.ini);
    	String recurso="";
    	
    	if(num.length()<Utils.numCeros)
    	{
        	String ceros="";
        	for(int n=0; n<(Utils.numCeros-num.length()); n++) ceros+="0";
        	recurso=Utils.nombreBase+ceros+num;      		
    	}
    	else recurso=Utils.nombreBase+num;

    	// Cogemos el ID del recurso 	
    	return contexto.getResources().getIdentifier(recurso, "drawable", "com.serone.desktoplabel");		
	}
	
    /**
     * Metemos una traza de debug
     */
    public static void logDebug(String texto)
    {
    	if(Utils.logNivel>3) Log.d(Utils.logFuente, texto);
    }
    
    /**
     * Metemos una traza de info
     */
    public static void logInfo(String texto)
    {
    	if(Utils.logNivel>2) Log.i(Utils.logFuente, texto);
    }
    
    /**
     * Metemos una traza de aviso
     */
    public static void logAviso(String texto)
    {
    	if(Utils.logNivel>1) Log.w(Utils.logFuente, texto);
    }
    
    /**
     * Metemos una traza de error
     */
    public static void logError(String texto)
    {
    	if(Utils.logNivel>0) Log.d(Utils.logFuente, texto);
    }
    
    /**
     * Muestra una notificación Toast corta
     */
    public static void mostrarToast(Context contexto, String texto)
    {
    	// Configuramos el toast
    	int duracion = Toast.LENGTH_SHORT;

    	// Mostramos el toast
    	Toast toast = Toast.makeText(contexto, texto, duracion);
    	toast.show();
    }
    
    /**
     * Muestra una notificación Toast larga
     */
    public static void mostrarToastLargo(Context contexto, String texto)
    {
    	// Configuramos el toast
    	int duracion = Toast.LENGTH_LONG;

    	// Mostramos el toast
    	Toast toast = Toast.makeText(contexto, texto, duracion);
    	toast.show();
    }

    /**
     * Convierte densitypixels a pixels
     * @param dp los DPs a convertir
     * @return La medida en pixels
     */
	public static int medidaWidgetAPixles(Context contexto, String dimension, int casillas)
	{
		// Referencias
		//  - http://developer.android.com/guide/topics/appwidgets/index.html
		
		// Vemos que un cuadro de 1x1 es de 80x100 para una pantalla de 320x480,
		// por lo que sacaremos las medidas con reglas de 3
		
		// Pantalla
		int anchuraPantalla=contexto.getResources().getDisplayMetrics().widthPixels;
		int alturaPantalla=contexto.getResources().getDisplayMetrics().heightPixels;
		
		// Dimensiones por casilla
		int anchuraCasilla=(anchuraPantalla*80)/320;
		int alturaCasilla=(alturaPantalla*100)/480;
		
		// Convertimos
		int px=0;
		
		if(dimension=="altura") px=(casillas*alturaCasilla);
		else px=(casillas*anchuraCasilla);

		return px;
	}
}
