package principal;

import java.util.List;
import org.xmldb.api.base.XMLDBException;
import acceso.AccesoProducto;
import acceso.Consola;
import entrada.Teclado;
import modelo.Producto;
import modelo.Zona;

public class Principal {

    private static void escribirMenu() {
        System.out.println("0 - Salir");
        System.out.println("1 - Consultar productor con precio dentro de un rango por orden DESC.");
        System.out.println("2 - Consultar las zonas de la base de datos que incluyen una palabra en el nombre.");
        System.out.println("3 - Consultar Producto por codigo.");
        System.out.println("4 - Consultar las zonas de la base de datos por codigo.");
        System.out.println("5 - Insertar una zona con codigo no repetido.");
        System.out.println(
                "6 - Actualizar denominacion, precio, cantidad actual, cantidad minima de un producto por codigo.");
        System.out.println("7 - Eliminar productos de son GPU.");
    }

    public static void main(String[] args) {
        int opcion;
        double precioMin, precioMax;
        List<Producto> listaProd;
        do {
            escribirMenu();
            opcion = Teclado.leerEntero("Opcion: ");
            switch (opcion) {
                case 0:
                    System.out.println("Goodbye twn!");
                    break;
                case 1:
                    precioMin = Teclado.leerReal("Precio Minimo: ");
                    precioMax = Teclado.leerReal("Precio Maximo: ");
                    try {
                        listaProd = AccesoProducto.consoltarByPriceRange(precioMin, precioMax);
                        if (listaProd.isEmpty()) {
                        } else {
                            Consola.escribirLista(listaProd);
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    String palabra = Teclado.leerCadena("Palabra: ");
                    try {
                        List<Zona> zona = AccesoProducto.consoltarByWord(palabra);
                        if (zona.isEmpty()) {
                            System.out.println("No se encontraron zonas.");
                        } else {
                            Consola.escribirLista(zona);
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    String code = Teclado.leerCadena("Codigo: ");
                    try {
                        Producto prod = AccesoProducto.consoltarProdByCode(code);
                        if (prod == null) {
                            System.out.println("No se ha encontrado ninguna prod");
                        } else {
                            System.out.println(prod.toString());
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    code = Teclado.leerCadena("Codigo: ");
                    try {
                        Zona zona = AccesoProducto.consoltarByCode(code);
                        if (zona == null) {
                            System.out.println("No se ha encontrado ninguna zona");
                        } else {
                            System.out.println(zona.toString());
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    code = Teclado.leerCadena("Codigo: ");
                    try {
                        Zona zona = AccesoProducto.consoltarByCode(code);
                        if (zona != null) {
                            System.out.println("Ya existe una zona con este codigo!!");
                        } else {
                            String name = Teclado.leerCadena("Nombre: ");
                            String director = Teclado.leerCadena("Director: ");
                            AccesoProducto.insertarZona(code, name, director);
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    code = Teclado.leerCadena("Codigo: ");
                    String denom = Teclado.leerCadena("Denominacion: ");
                    Double precio = Teclado.leerReal("Precio: ");
                    int cantReal = Teclado.leerEntero("Cantidad Real: ");
                    int cantMin = Teclado.leerEntero("Cantidad Minima: ");
                    try {
                        Producto productOld = AccesoProducto.consoltarProdByCode(code);
                        if (productOld == null) {
                            System.out.println("Producto no eocontrado!!!");
                        } else {
                            Producto prodNew = new Producto(code, productOld.getCodigoZona(), denom, precio, cantReal,
                                    cantMin);
                            AccesoProducto.actualizarProd(prodNew);
                            System.out.println("Actualizado!");
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    denom = Teclado.leerCadena("Denominacion: ");
                    try {
                        List<Producto> lista = AccesoProducto.consoltarProdByWord(denom);
                        if(lista.isEmpty()){
                            System.out.println("No se han encontrado productos con este nombre.");
                        }else{
                            AccesoProducto.eliminarProd(denom);
                            System.out.println("Eliminado!");
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                            | XMLDBException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.err.println("Opcion Out of Range");
                    break;
            }
        } while (opcion != 0);
    }
}
