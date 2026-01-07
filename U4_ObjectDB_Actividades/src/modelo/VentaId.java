package modelo;

import java.io.Serializable;
import java.sql.Date;

public class VentaId implements Serializable {

	private Cliente cliente;
	private Turismo turismo;
	private Date fecha;
	
	public VentaId(Cliente cliente, Turismo turismo, Date fecha) {
		this.cliente = cliente;
		this.turismo = turismo;
		this.fecha = fecha;
	}
	
}
