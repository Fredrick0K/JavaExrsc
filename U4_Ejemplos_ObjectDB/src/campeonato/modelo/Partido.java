package campeonato.modelo;

import java.sql.Date;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(PartidoId.class)
public class Partido {

	@Id
	@ManyToOne
    private Equipo equipoLocal;
	@Id
    @ManyToOne
    private Equipo equipoVisitante;
	@Id
	private Date fecha;
    @Embedded
	private Resultado resultado;
	
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, 
	               Date fecha, Resultado resultado) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Partido {NombreEquipoLocal = " + equipoLocal.getNombre() + 
		       ", NombreEquipoVisitante = " + equipoVisitante.getNombre() + 
		       ", Fecha = " + fecha.toString() + 
		       ", Resultado = " + resultado.toString() + "}";
	}
	
}
