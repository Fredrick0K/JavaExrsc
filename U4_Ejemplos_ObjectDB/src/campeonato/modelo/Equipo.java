package campeonato.modelo;

import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Equipo {

    @Id
	private String nombre;
	private String localidad;
	private String estadio;
	private int anioFundacion;
	private double presupuesto;
	
    @OneToMany
    private List<Jugador> jugadores;
	
	public Equipo(String nombre, String localidad, String estadio, 
	              int anioFundacion, double presupuesto) {
		this.nombre = nombre;
		this.localidad = localidad;
		this.estadio = estadio;
		this.anioFundacion = anioFundacion;
		this.presupuesto = presupuesto;
		this.jugadores = new LinkedList<Jugador>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public int getAnioFundacion() {
		return anioFundacion;
	}

	public void setAnioFundacion(int anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public boolean insertar(Jugador jugador) {
		boolean insertado = false;
		if (jugadores != null) {
			insertado = jugadores.add(jugador);
		}
		return insertado;
	}

	@Override
	public String toString() {
		return "Equipo {Nombre = " + nombre + 
				", Localidad = " + localidad + 
				", Estadio = " + estadio + 
				", AñoFundación = " + anioFundacion + 
				", Presupuesto = " + String.format("%.2f", presupuesto) + "}";
	}
	
}
