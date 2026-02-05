package modelo;

public class Zona {

	private String codigo;
	private String nombre;
	private String director;
	
	public Zona(String codigo, String nombre, String director) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.director = director;
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
	
	public Zona(String elemento) {
		this.codigo = extraerValorDeAtributo(elemento, "codigo");
		this.nombre = extraerValorDeElemento(elemento, "nombre");
		this.director = extraerValorDeElemento(elemento, "director");
	}
	
	@Override
	public String toString() {
		return "Zona {C�digo = " + codigo + 
		       ", Nombre = " + nombre + 
		       ", Director = " + director + "}";
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirector() {
		return director;
	}
	
}
