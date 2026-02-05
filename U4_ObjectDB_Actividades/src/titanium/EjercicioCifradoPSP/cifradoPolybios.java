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
        int fila, columna;
        palabra = palabra.toUpperCase();
        for (int i = 0; i < palabra.length(); i++) {
            fila = -1;
            columna = -1;
            letra = palabra.substring(i, i + 1);
            for (int j = 0; j < 5 && fila == -1; j++) {
                for (int k = 0; k < 5 && columna == -1; k++) {
                    if (MATRIX[j][k].contains(letra)) {
                        fila = j;
                        columna = k;
                    }
                }
            }
            if (fila != -1 && columna != -1) {
                fila++; columna++;
            }
        }
        return cifrado;
    }

    private static String descifrar(String palabra) {
        String letra;
        int fila, columna;
        for(int i = 0; i < palabra.length(); i = i + 2){
            letra = palabra;
        }
        return null;
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
                    String palCifrada = Teclado.leerCadena("Escribe palabra a descifrar: ");

                    break;
            }

        } while (opcion != 0);
    }
}
