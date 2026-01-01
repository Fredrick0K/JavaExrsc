package modelo;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	private Date fechaNacimiento;
	@Embedded
	private Domicilio domicilioResidencia;
	private String telefono;
	private String correo;
	
	@OneToMany
	private List<Venta> ventas;
	
	public Cliente(long codigo, String nombre, Date fechaNacimiento, 
	               Domicilio domicilioResidencia, String telefono, String correo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilioResidencia = domicilioResidencia;
		this.telefono = telefono;
		this.correo = correo;
		this.ventas = new LinkedList<Venta>();
	}

	@Override
	public String toString() {
		return "Cliente {Código = " + codigo + 
		       ", Nombre = " + nombre + 
		       ", FechaNacimiento = " + fechaNacimiento.toString() + 
		       ", DomicilioResidencia = " + domicilioResidencia.toString() + 
		       ", Teléfono = " + telefono + 
		       ", Correo = " + correo + "}";
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

	public Domicilio getDomicilioResidencia() {
		return domicilioResidencia;
	}

	public void setDomicilioResidencia(Domicilio domicilioResidencia) {
		this.domicilioResidencia = domicilioResidencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public boolean insertar(Venta venta) {
		boolean insertada = ventas.add(venta);
		if (insertada) {
			venta.setCliente(this);
		}
		return insertada;
	}
	
}
