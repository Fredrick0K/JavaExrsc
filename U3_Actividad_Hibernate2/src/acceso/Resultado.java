package acceso;

import java.util.LinkedHashMap;
import java.util.Map;

public class Resultado {

	private Map<String,String> mapaCampos;
	
	/**
	 * Crea un resultado vac�o sin campos.
	 */
	public Resultado() {
		this.mapaCampos = new LinkedHashMap<String,String>();
	}
	
	/**
	 * A�ade un campo (compuesto por un nombre y un valor) al mapa de campos.
	 */
	public void ponerCampo(String nombre, String valor) {
		mapaCampos.put(nombre, valor);
	}

	/**
	 * Devuelve una cadena con el estado del resultado.
	 */
	@Override
	public String toString() {
		int contador = 0;
		String resultado = "Resultado {";
		for (String nombre : mapaCampos.keySet()) {
			String valor = mapaCampos.get(nombre);
			if (contador != 0) {
				resultado = resultado + ", ";
			}
			resultado = resultado + nombre + " = " + valor;
			contador++;
		}
		resultado = resultado + "}";
		return resultado;
	}
	
}
