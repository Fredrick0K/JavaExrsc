package modelo;

public class Producto {

	// atributos
	private int codigo;
	private String nombre;
	private String categoria;
	private Fecha fechaModificacion;
	private int cantidad;
	private double precio;

	// Constantes
	public static final int LONGITUD_NOMBRE = 15;
	public static final int LONGITUD_CATEGORIA = 20;
	public static final int TAMANYO_REGISTRO = LONGITUD_NOMBRE * 2 + LONGITUD_CATEGORIA * 2 + Fecha.TAMANYO_FECHA
			+ 16 /* <---- sumando tamaño de los Double y INT */;

	public Producto(int codigo, String nombre, String categoria, Fecha fechaModificacion, int cantidad, double precio) {

		this.codigo = codigo;

		StringBuffer bufferCadena = new StringBuffer(nombre);
		bufferCadena.setLength(LONGITUD_NOMBRE);
		this.nombre = bufferCadena.toString();

		bufferCadena = new StringBuffer(categoria);
		bufferCadena.setLength(LONGITUD_CATEGORIA);

		this.categoria = bufferCadena.toString();
		this.fechaModificacion = fechaModificacion;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", " + 
				"nombre=" + nombre.trim() + ", " 
				+ "categoria=" + categoria.trim()
				+ ", fechaModificacion=" + fechaModificacion.toString() + ", " 
				+"cantidad=" + cantidad + ", "
				+ "precio=" + String.format("%.2f", precio) + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public Fecha getFechaModificacion() {
		return fechaModificacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setFechaModificacion(Fecha fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

}
