package modelo;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(VentaId.class)
public class Venta {

	@Id
	@ManyToOne
	private Cliente cliente;
	@Id
	@ManyToOne
	private Turismo turismo;
	@Id
	private Date fecha;
	private double importe;
	private String metodoPago;
	private String matricula;
	
	public Venta() {
	}
	
	public Venta(Cliente cliente, Turismo turismo, Date fecha, 
	             double importe, String metodoPago, String matricula) {
		this.cliente = cliente;
		this.turismo = turismo;
		this.fecha = fecha;
		this.importe = importe;
		this.metodoPago = metodoPago;
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Venta {C�digoCliente = " + cliente.getCodigo() + 
		       ", C�digoTurismo = " + turismo.getCodigo() + 
		       ", Fecha = " + fecha.toString() + 
		       ", Importe = " + String.format("%.2f", importe) + 
		       ", M�todoPago = " + metodoPago + 
		       ", Matr�cula = " + matricula + "}";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		cliente.insertar(this);
	}

	public Turismo getTurismo() {
		return turismo;
	}

	public void setTurismo(Turismo turismo) {
		this.turismo = turismo;
		turismo.insertar(this);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
