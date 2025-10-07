package principal;

import acceso.AccesoProducto;
import entrada.Teclado;
import modelo.Fecha;
import modelo.Producto;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;
import java.util.Scanner;

public class Principal {

    private static void escribirMenu() {

        System.out.println();
        System.out.println("0 - Salir.");
        System.out.println("1 - Consultar todos los productos.");
        System.out.println("2 - Consultar producto por ID.");
        System.out.println("3 - Insertar nuevo producto en el fichero.");
        System.out.println("4 - Actualizar precio, cantidad y fecha "
                + "de modificacion.");
        System.out.println("5 - Eliminar producto por codigo.");

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomAccessFile flujoEntrada = null;

        int opcion;
        do {
            escribirMenu();
            opcion = Teclado.leerEntero("Introduzca una opcion: ");
            switch (opcion) {
                case 0:

                    break;
                case 1:
                    try {
                        File fichero = new File("Actividad_U1_BinarioDirect\\data\\producto.dat");
                        flujoEntrada = new RandomAccessFile(fichero, "r");
                        flujoEntrada.seek(0);
                        int contador = 0;
                        while (flujoEntrada.getFilePointer() < flujoEntrada.length()) {
                            Producto producto = AccesoProducto.leerProducto(flujoEntrada);
                            if (producto.getCodigo() > 0) {
                                System.out.println(producto.toString());
                                contador++;
                            }
                        }
                    } catch (IOException ioe) {
                        System.out.println("Error al leer del fichero binario.");
                        ioe.printStackTrace();
                    } finally {
                        try {
                            if (flujoEntrada != null) {
                                flujoEntrada.close();
                            }
                        } catch (IOException ioe) {
                            System.out.println("Error al cerrar el fichero binario.");
                            ioe.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    int codigo = Teclado.leerEntero("Introduzca el codigo del producto: ");
                    String nombre = Teclado.leerCadena("Introduzca el nombre del producto: ");
                    String categoria = Teclado.leerCadena("Introduzca la categoria del producto: ");
                    String fechaStr = Teclado.leerCadena("Introduzca la fecha de modificacion (dd/mm/aaaa): ");
                    Fecha fechaModificacion = new Fecha(fechaStr);
                    int cantidad = Teclado.leerEntero("Introduzca la cantidad del producto: ");
                    double precio = Teclado.leerReal("Introduzca el precio del producto: ");
                    Producto producto = new Producto(codigo, nombre, categoria, fechaModificacion, cantidad, precio);
                    try {
                        File fichero = new File("Actividad_U1_BinarioDirect\\data\\producto.dat");
                        flujoEntrada = new RandomAccessFile(fichero, "rw");
                        AccesoProducto.escribirProducto(flujoEntrada, producto);
                        System.out.println("Se ha escrito un producto en el fichero binario.");
                    } catch (IOException ioe) {
                        System.out.println("Error al escribir en el fichero binario.");
                        ioe.printStackTrace();
                    } finally {
                        try {
                            if (flujoEntrada != null) {
                                flujoEntrada.close();
                            }
                        } catch (IOException ioe) {
                            System.out.println("Error al cerrar el fichero binario.");
                            ioe.printStackTrace();
                        }
                    }
                    break;
            }

        } while (opcion != 0);
    }

}
