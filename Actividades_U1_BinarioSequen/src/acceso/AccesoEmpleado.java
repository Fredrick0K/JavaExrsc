package acceso;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import modelo.Empleado;

public class AccesoEmpleado {

	private static String RUTA_FICHERO = "data/empleados.dat";
	private static String RUTA_FICHERO_VS = "Actividades_U1_BinarioSequen\\data\\empleados.dat";
	private static String RUTA_FICHERO_TEMP = "data/temporal.dat";
	private static String RUTA_FICHERO_TEMO_VS = "Actividades_U1_BinarioSequen\\data\\temporal.dat";

	public static void escribirEmpleado(Empleado empleado) throws IOException {
		ObjectOutputStream flujoSalida1 = null;
		MyObjectOutputStream flujoSalida2 = null;
		try {
			File fichero = new File(RUTA_FICHERO);

			if (fichero.exists()) {
				flujoSalida2 = new MyObjectOutputStream(new FileOutputStream(fichero, true));
				flujoSalida2.writeObject(empleado);
			}
			// Crear el fichero e insertar la pelicula al principio del fichero.
			else {
				flujoSalida1 = new ObjectOutputStream(new FileOutputStream(fichero));
				flujoSalida1.writeObject(empleado);
			}

		} finally {

			if (flujoSalida1 != null) {
				flujoSalida1.close();
			}

			if (flujoSalida2 != null) {
				flujoSalida2.close();
			}
		}
	}

	public static ArrayList<String> leerFichero()
			throws FileNotFoundException, StreamCorruptedException, IOException, EOFException, ClassNotFoundException {
		ObjectInputStream flujoEntrada = null;
		File fichero = new File(RUTA_FICHERO);
		ArrayList<String> listaEmpleados = new ArrayList<String>();
		try {
			flujoEntrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				while (true) {
					Empleado empleado = (Empleado) flujoEntrada.readObject();
					listaEmpleados.add(empleado.toString());
				}
			} catch (EOFException eof) {
				// Fin del archivo, salir del bucle
			}
		} finally {
			if (flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
		return listaEmpleados;
	}

	public static Empleado buscarPorCodigo(int codigoEmp) throws ClassNotFoundException, IOException {
		ObjectInputStream flujoEntrada = null;
		Empleado empleado = null;
		File fichero = new File(RUTA_FICHERO);
		if (!fichero.exists()) {
			return null;
		}
		try {
			flujoEntrada = new ObjectInputStream(new FileInputStream(fichero));
			while (true) {
				try {
					Empleado emp = (Empleado) flujoEntrada.readObject();
					if (emp.getCodigo() == codigoEmp) {
						empleado = emp;
						break;
					}
				} catch (EOFException eof) {
					break;
				}
			}
		} finally {
			if (flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
		return empleado;
	}

	public boolean actualizarEmpleado(int codigo, Empleado nuevoEmpleado)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream flujoLectura = null;
		ObjectOutputStream flujoEscritura = null;

		boolean actualizado = false;
		try {
			File ficheroEmpleados = new File(RUTA_FICHERO);
			flujoLectura = new ObjectInputStream(new FileInputStream(ficheroEmpleados));

			File ficheroTemp = new File(RUTA_FICHERO_TEMP);
			flujoEscritura = new ObjectOutputStream(new FileOutputStream(ficheroTemp));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Empleado empleado = (Empleado) flujoLectura.readObject();
					if (empleado.getCodigo() == codigo) {
						empleado.setNombre(nuevoEmpleado.getNombre());
						empleado.setApellido(nuevoEmpleado.getApellido());
						empleado.setFechaDeAlta(nuevoEmpleado.getFechaDeAlta());
						actualizado = true;
					}
					flujoEscritura.writeObject(empleado);
				} catch (EOFException eof) {
					finalFichero = true;
					// TODO: handle exception
				}
			}

			flujoEscritura.close();
			flujoLectura.close();
			ficheroEmpleados.delete();
			ficheroTemp.renameTo(ficheroEmpleados);
		} finally {
			if (flujoEscritura != null) {
				flujoEscritura.close();
			}
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return actualizado;
	}

	public static boolean eliminarPorCodigo(int codigo) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream flujoLectura = null;
		ObjectOutputStream flujoSalida = null;
		boolean eliminado = false;
		try {
			File ficheroEmpleados = new File(RUTA_FICHERO);
			flujoLectura = new ObjectInputStream(new FileInputStream(ficheroEmpleados));
			File ficheroTemp = new File(RUTA_FICHERO_TEMP);
			flujoSalida = new ObjectOutputStream(new FileOutputStream(ficheroTemp));

			boolean finalFichero = false;
			while (!finalFichero) {
				try {

					Empleado empleado = (Empleado) flujoLectura.readObject();
					if (empleado.getCodigo() == codigo) {
						eliminado = true;

					} else {
						flujoSalida.writeObject(empleado);
					}
				} catch (EOFException eof) {
					finalFichero = true;
				}
			}
			flujoLectura.close();
			flujoSalida.close();
			ficheroEmpleados.delete();
			ficheroTemp.renameTo(ficheroEmpleados);

		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
			if (flujoSalida != null) {
				flujoSalida.close();
			}

		}
		return eliminado;
	}

}
