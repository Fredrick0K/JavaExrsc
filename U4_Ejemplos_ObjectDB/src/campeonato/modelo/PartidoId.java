package campeonato.modelo;

import java.io.Serializable;
import java.sql.Date;

public class PartidoId implements Serializable {

	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private Date fecha;
	
	public PartidoId(Equipo equipoLocal, Equipo equipoVisitante, Date fecha) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = fecha;
	}
	
}
