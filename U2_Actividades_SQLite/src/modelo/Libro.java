package modelo;

public class Libro {
	private int codigo;
	private Escritor escritor;
	private String titulo;
	private int anyoPublicacion;
	private int extension; // Paginas
	private double precio; //euro

	public Libro(int codigo, Escritor escritor, String titulo, int anyoPublicacion, int extension, double precio) {
		this.codigo = codigo;
		this.escritor = escritor;
		this.titulo = titulo;
		this.anyoPublicacion = anyoPublicacion;
		this.extension = extension;
		this.precio = precio;
	}
	
	public Libro(int codigo) {
		this.codigo = codigo;
	}

	public Libro(String titulo2, int anyoPublicacion2, double precio2) {
		// TODO Auto-generated constructor stub
		this.titulo = titulo2;
		this.anyoPublicacion = anyoPublicacion2;
		this.precio = precio2;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", escritor=" + escritor.getCodigo() +
				", titulo=" + titulo + 
				", anyoPublicacion=" + anyoPublicacion +
				", extension=" + extension +
				", precio=" + String.format("%.2f", precio) + "]";
	}
	public String toStringMod() {
		return "Libro [titulo = " + titulo + 
				", anyoPublicacion = " + anyoPublicacion +
				", precio = " + String.format("%.2f", precio) + "]";
	}
//String.format("%d paginas", extension)
	
}
