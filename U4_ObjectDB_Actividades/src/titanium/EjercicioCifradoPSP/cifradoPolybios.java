package titanium.EjercicioCifradoPSP;

import entrada.Teclado;

public class cifradoPolybios {
    private static final String[][] MATRIX = {
            { "A", "B", "C", "D", "E" },
            { "F", "G", "H", "IJ", "k" },
            { "L", "M", "NÑ", "O", "P" },
            { "Q", "R", "S", "T", "U" },
            { "V", "W", "X", "Y", "Z" } };

    private static int cifrar(String palabra) {
        int cifrado = 0;
        String letra;
        for (int i = 0; palabra.length() > i; i++) {
            letra = String.valueOf(palabra.charAt(i));
        }
        //This for find the match for something
        for (int i = 0; i < MATRIX.length; i++) {
            for (int j = 0; j < MATRIX[i].length; j++) {
                if (MATRIX[i][j].equals(i)) {
                    System.out.println("Found at row " + i + ", column " + j);
                    // Optionally break if only first match is needed
                }
            }
        }
        return cifrado;
    }

    private static String descifrar(int cifrada) {
        String frase = "";
        return frase;
    }

    private static void escribirMenu() {
        System.out.println("0 - Salir.");
        System.out.println("1 - Cifrar palabra.");
        System.out.println("2 - Descifrar palabra.");
    }

    public static void main(String[] args) {

        int opcion, cifrada = 0;

        do {
            escribirMenu();
            opcion = Teclado.leerEntero("Opcion: ");
            switch (opcion) {
                case 0:
                    break;
                case 1:

                    String palabra = Teclado.leerCadena("Palabra: ");
                    for (int i = 0; palabra.length() > i; i++) {
                        System.out.println(palabra.charAt(i));
                    }

                    break;
                case 2:
                    String descifrada = descifrar(cifrada);
                    System.out.println("Palabra descifrada: " + descifrada);
                    break;
            }

        } while (opcion != 0);
    }
}
