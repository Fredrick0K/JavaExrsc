package modelo;

public class Producto {

	private String codigo;
	private String codigoZona;
	private String denominacion;
	private double precio;
	private int cantidadActual;
	private int cantidadMinima;
	
	public Producto(String codigo, String codigoZona, String denominacion, 
	                double precio, int cantidadActual, int cantidadMinima) {
		this.codigo = codigo;
		this.codigoZona = codigoZona;
		this.denominacion = denominacion;
		this.precio = precio;
		this.cantidadActual = cantidadActual;
		this.cantidadMinima = cantidadMinima;
	}
	
	private static String extraerValorDeAtributo(String elemento, String nombre) {
		int posicionInicio = elemento.indexOf(nombre) + nombre.length() + 2;
		int posicionFin = elemento.indexOf("\"", posicionInicio);
		String texto = elemento.substring(posicionInicio, posicionFin);		
		return texto;
	}
	
	private static String extraerValorDeElemento(String elemento, String nombre) {
		String marcaInicio = "<" + nombre + ">";
		String marcaFin = "</" + nombre + ">";
		int posicionInicio = elemento.indexOf(marcaInicio) + marcaInicio.length();
		int posicionFin = elemento.indexOf(marcaFin);
		String texto = elemento.substring(posicionInicio, posicionFin);
		return texto;
	}
	
	public Producto(String elemento) {
		this.codigo = extraerValorDeAtributo(elemento, "codigo");
		this.codigoZona = extraerValorDeAtributo(elemento, "codigoZona");
		this.denominacion = extraerValorDeElemento(elemento, "denominacion");
		String texto = extraerValorDeElemento(elemento, "precio");
		this.precio = Double.parseDouble(texto.replace(',', '.'));
		texto = extraerValorDeElemento(elemento, "cantidadActual");
		this.cantidadActual = Integer.parseInt(texto);
		texto = extraerValorDeElemento(elemento, "cantidadMinima");
		this.cantidadMinima = Integer.parseInt(texto);	
	}

	@Override
	public String toString() {
		return "Producto {C�digo = " + codigo + 
		       ", C�digoZona = " + codigoZona + 
		       ", Denominaci�n = " + denominacion + 
		       ", Precio = " + String.format("%.2f", precio) + 
		       ", CantidadActual = "+ cantidadActual + 
		       ", CantidadM�nima = " + cantidadMinima + "}";
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}
	
}
