package modelo;

import java.io.Serializable;

public class Empleado implements Serializable {

	private int codigo;
	private String nombre;
	private String apellido;
	private Fecha fechaDeAlta;
	private String departamento;
	private double salario;

	public Empleado(int codigo, String nombre, String apellido, Fecha fechaDeAlta, String departamento,
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Fecha getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Fecha fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	

	

}
