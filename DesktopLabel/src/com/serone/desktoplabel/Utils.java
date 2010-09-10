package com.serone.desktoplabel;

// Imports necesarios
import android.content.Context;
import android.util.Log;
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
}
