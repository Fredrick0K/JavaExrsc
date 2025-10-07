package principal;

import acceso.AccesoProducto;
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

        Scanner teclado = new Scanner(System.in);
        System.out.println("Opcion: ");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 0:

                break;
            case 1:
			AccesoProducto.leerProducto(flujoEntrada)
                break;
            default:
                throw new AssertionError();
        }

    }

}
