package campeonato.modelo;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Jugador {

	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	private Date fechaNacimiento;
	private double salario;
	private int numeroCamiseta;
	private String posicion;

	@ManyToOne
    private Equipo equipo;

	public Jugador(long codigo, String nombre, Date fechaNacimiento, 
	               double salario, int numeroCamiseta, String posicion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.salario = salario;
		this.numeroCamiseta = numeroCamiseta;
		this.posicion = posicion;
		this.equipo = null;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(int numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Jugador {Código = " + codigo + 
		       ", Nombre = " + nombre + 
		       ", FechaNacimiento = " + fechaNacimiento.toString() + 
		       ", Salario = " + String.format("%.2f", salario) + 
		       ", NúmeroCamiseta = " + numeroCamiseta + 
		       ", Posición = " + posicion + "}";
	}
	
}
