package acceso;

import java.util.ArrayList;
import java.util.List;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import modelo.Producto;
import modelo.Zona;

public class AccesoProducto {

    public static List<Producto> consoltarByPriceRange(double precioMin, double precioMax)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection coleccion = null;
        List<Producto> listaProd = new ArrayList<Producto>();
        try {
            coleccion = ExistDBUtil.abrirColeccion();
            XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
            String xql = "for $prod in /productos/producto "
                    + " where $prod/precio >= " + precioMin + " and $prod/precio <= " + precioMax + " " +
                    " order by $prod/precio descending"
                    + " return $prod";
            ResourceSet recursos = servicio.query(xql);
            ResourceIterator iterador = recursos.getIterator();
            while (iterador.hasMoreResources()) {
                Resource recurso = iterador.nextResource();
                String texto = (String) recurso.getContent();
                Producto producto = new Producto(texto);
                listaProd.add(producto);
            }
        } finally {
            ExistDBUtil.cerrarColeccion(coleccion);
        }
        return listaProd;
    }

    public static List<Zona> consoltarByWord(String word)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection coleccion = null;
        Zona zona = null;
        List<Zona> listaZona = new ArrayList<>();
        try {
            coleccion = ExistDBUtil.abrirColeccion();
            XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
            String xql = "for $prod in /zonas/zona "
                    + "where contains($prod/nombre, '" + word + "') "
                    + "return $prod";
            ResourceSet recursos = servicio.query(xql);
            ResourceIterator iterador = recursos.getIterator();
            while (iterador.hasMoreResources()) {
                Resource recurso = iterador.nextResource();
                String texto = (String) recurso.getContent();
                zona = new Zona(texto);
                listaZona.add(zona);
            }
        } finally {
            ExistDBUtil.cerrarColeccion(coleccion);
        }
        return listaZona;
    }

    public static List<Producto> consoltarProdByWord(String word)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection coleccion = null;
        Producto zona = null;
        List<Producto> listaZona = new ArrayList<>();
        try {
            coleccion = ExistDBUtil.abrirColeccion();
            XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
            String xql = "for $prod in //producto "
                    + "where contains($prod/denominacion, '" + word + "') "
                    + "return $prod";
            ResourceSet recursos = servicio.query(xql);
            ResourceIterator iterador = recursos.getIterator();
            while (iterador.hasMoreResources()) {
                Resource recurso = iterador.nextResource();
                String texto = (String) recurso.getContent();
                zona = new Producto(texto);
                listaZona.add(zona);
            }
        } finally {
            ExistDBUtil.cerrarColeccion(coleccion);
        }
        return listaZona;
    }

    public static Zona consoltarByCode(String code)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection coleccion = null;
        Zona zona = null;
        try {
            coleccion = ExistDBUtil.abrirColeccion();
            XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
            String xql = "for $zone in /zonas/zona "
                    + "where $zone/@codigo = '" + code + "' "
                    + "return $zone";
            ResourceSet recursos = servicio.query(xql);
            ResourceIterator iterador = recursos.getIterator();
            if (iterador.hasMoreResources()) {
                Resource recurso = iterador.nextResource();
                String texto = (String) recurso.getContent();
                zona = new Zona(texto);
            }
        } finally {
            ExistDBUtil.cerrarColeccion(coleccion);
        }
        return zona;
    }

    public static Producto consoltarProdByCode(String code)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection coleccion = null;
        Producto prod = null;
        try {
            coleccion = ExistDBUtil.abrirColeccion();
            XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
            String xql = "for $prod in //producto "
                    + "where $prod/@codigo = '" + code + "' "
                    + "return $prod";
            ResourceSet recursos = servicio.query(xql);
            ResourceIterator iterador = recursos.getIterator();
            if (iterador.hasMoreResources()) {
                Resource recurso = iterador.nextResource();
                String texto = (String) recurso.getContent();
                prod = new Producto(texto);
            }
        } finally {
            ExistDBUtil.cerrarColeccion(coleccion);
        }
        return prod;
    }

    public static void insertarZona(String code, String nombre, String director)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection collection = null;
        try {
            collection = ExistDBUtil.abrirColeccion();
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            String xql = "update insert <zona codigo= '" + code + "'> "
                    + "<nombre> " + nombre + " </nombre>"
                    + " <director>" + director + "</director>"
                    + "</zona> into /zonas";
            service.query(xql);
        } finally {
            ExistDBUtil.cerrarColeccion(collection);
        }
    }

    public static void actualizarProd(Producto prod)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection collection = null;
        try {
            collection = ExistDBUtil.abrirColeccion();
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            String xql = "update replace //producto[@codigo= '" + prod.getCodigo() + "']\n" +
                    "with \n" +
                    "<producto codigo='" + prod.getCodigo() + "' codigoZona='" + prod.getCodigoZona() + "'>\n" +
                    "    <denominacion>" + prod.getDenominacion() + "</denominacion>\n" +
                    "    <precio>" + prod.getPrecio() + "</precio>\n" +
                    "    <cantidadActual>" + prod.getCantidadActual() + "</cantidadActual>\n" +
                    "    <cantidadMinima>" + prod.getCantidadMinima() + "</cantidadMinima>\n" +
                    "</producto>";
            service.query(xql);
        } finally {
            ExistDBUtil.cerrarColeccion(collection);
        }
    }

    public static void eliminarProd(String name)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Collection collection = null;
        try {
            collection = ExistDBUtil.abrirColeccion();
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            String xql = "update delete //producto[starts-with(denominacion, '" + name + "')]";
            service.query(xql);
        } finally {
        }
    }
}
