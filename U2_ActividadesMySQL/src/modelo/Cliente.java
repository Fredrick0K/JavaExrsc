package modelo;

import java.sql.Date;

public class Cliente {

	private int codigo;
	private String nombre;
	private String domicilio;
	private String correo;
	private Date fechaRegistro; // AAAA-MM-DD

	public Cliente(int codigo, String nombre, String domicilio, String correo, Date fechaRegistro) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.correo = correo;
		this.fechaRegistro = fechaRegistro;
	}

	public Cliente(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", domicilio=" + domicilio + ", correo=" + correo
				+ ", fechaRegistro=" + fechaRegistro.toString() + "]";
	}

	public int getCodigo() {
		return codigo;
	}

}
