package modelo;

import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Turismo {
	
	@Id
	@GeneratedValue
	private long codigo;
	private String fabricante;
	private String modelo;
	private int anioFabricacion;
	private int numeroPlazas;
	private int capacidadMaletero; // litros
	private double precio;         // euros
	
	@OneToMany
	private List<Venta> ventas;
	
	public Turismo() {
		this.ventas = new LinkedList<Venta>();
	}
	
	public Turismo(String fabricante, String modelo, 
	               int anioFabricacion, int numeroPlazas, int capacidadMaletero, double precio) {
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.anioFabricacion = anioFabricacion;
		this.numeroPlazas = numeroPlazas;
		this.capacidadMaletero = capacidadMaletero;
		this.precio = precio;
		this.ventas = new LinkedList<Venta>();
	}

	@Override
	public String toString() {
		return "Turismo {C�digo = " + codigo + 
		       ", Fabricante = " + fabricante + 
		       ", Modelo = " + modelo + 
		       ", A�oFabricaci�n = " + anioFabricacion + 
		       ", N�meroPlazas = " + numeroPlazas + 
		       ", CapacidadMaletero = " + capacidadMaletero + 
		       ", Precio = " + String.format("%.2f", precio) + "}";
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(int anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	public int getCapacidadMaletero() {
		return capacidadMaletero;
	}

	public void setCapacidadMaletero(int capacidadMaletero) {
		this.capacidadMaletero = capacidadMaletero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public boolean insertar(Venta venta) {
		boolean insertada = ventas.add(venta);
		return insertada;
	}
	
}
