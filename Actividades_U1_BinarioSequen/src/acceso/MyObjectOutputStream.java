package acceso;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Clase MyObjectOutputStream que hereda de ObjectOutputStream y
 * que sobrescribe el método writeStreamHeader.
 * Se utiliza con un fichero binario existente que contiene objetos,
 * para no tener problemas con las cabeceras de los objetos y
 * evitar la excepción StreamCorruptedException.
 */
public class MyObjectOutputStream extends ObjectOutputStream {
	
	/**
	 * Crea un flujo de salida de objetos por defecto.
	 */
    protected MyObjectOutputStream() 
    throws IOException, SecurityException {
        super();
    }
    
    /**
     * Crea un flujo de salida de objetos a partir de un parámetro.
     */
    public MyObjectOutputStream(OutputStream out) 
    throws IOException {
        super(out);
    }

    /**
     * No escribe ninguna cabecera de objeto al inicio del flujo de salida de objetos.
     */
    protected void writeStreamHeader() 
    throws IOException {} /* <---------- NO hay codigo, no hace nada */

}
