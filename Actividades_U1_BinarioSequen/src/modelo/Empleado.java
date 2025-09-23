package modelo;

import java.io.Serializable;

public class Empleado implements Serializable {

	private int codigo;
	private String nombre;
	private String apellido;
	private String fechaDeAlta;
	private String departamento;
	private double salario;

	public Empleado(int codigo, String nombre, String apellido, String fechaDeAlta, String departamento,
			double salario) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeAlta = fechaDeAlta;
		this.departamento = departamento;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaDeAlta="
				+ fechaDeAlta + ", departamento=" + departamento + ", salario=" + salario + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	

}
