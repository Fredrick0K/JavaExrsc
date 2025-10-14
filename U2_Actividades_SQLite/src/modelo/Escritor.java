package modelo;

import java.sql.Date;

public class Escritor {
	private int codigo;
	private String nombre;
	private String nacionalidad;
	private Date fechaNacimiento; //formato AAAA-MM-DD
	private Date fechaFallecimiento; //formato AAAA-MM-DD

	public Escritor(int codigo, String nombre, String nacionalidad, Date fechaNacimiento, Date fechaFallecimiento) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public Escritor(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

}
