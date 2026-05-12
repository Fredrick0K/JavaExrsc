package campeonato.modelo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Resultado {

	private int puntuacionLocal;
	private int puntuacionVisitante;
	
	public Resultado(int puntuacionLocal, int puntuacionVisitante) {
		this.puntuacionLocal = puntuacionLocal;
		this.puntuacionVisitante = puntuacionVisitante;
	}

	@Override
	public String toString() {
		return puntuacionLocal + "-" + puntuacionVisitante;
	}
	
}
