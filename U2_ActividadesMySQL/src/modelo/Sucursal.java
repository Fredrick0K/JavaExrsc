package modelo;

public class Sucursal {

	private int codigo;
	private String direccion;
	private String localidad;
	private String telefono;
	private int numeroPuestos;

	public Sucursal(int codigo, String direccion, String localidad, String telefono, int numeroPuestos) {

		this.codigo = codigo;
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
		this.numeroPuestos = numeroPuestos;
	}

	public Sucursal(int codigo) {
		this.codigo = codigo;

	}

	@Override
	public String toString() {
		return "Sucursal [codigo=" + codigo + ", direccion=" + direccion + ", localidad=" + localidad + ", telefono="
				+ telefono + ", numeroPuestos=" + numeroPuestos + "]";
	}

	public int getCodigo() {
		return codigo;
	}

}
