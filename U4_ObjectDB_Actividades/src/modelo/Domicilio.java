package modelo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Domicilio {

	private String via;
	private int numero;
	private int codigoPostal;
	private String localidad;
	
	public Domicilio(String via, int numero, int codigoPostal, String localidad) {
		this.via = via;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return via + ", " + numero + ", " + codigoPostal + ", "	+ localidad;
	}
	
}
