package modelo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Domicilio {

	private String via;
	private int numero;
	private int codigoPostal;
	private String localidad;
	
	public Domicilio() {
	}
	
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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}
