package modelo;

public class Fecha {

	private int dia;
	private int mes;
	private int anio;
	
	public static final int TAMANYO_FECHA = 12; //4+4+4 cuz int is 4 bytes

	/**
	 * Crea una fecha a partir de un dia, un mes y un anio.
	 */
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	/**
	 * Crea una fecha a partir de una cadena con formato DD/MM/AAAA.
	 */
	public Fecha(String cadena) {
		String[] datos = cadena.split("/");
		if (datos.length != 3) {
			throw new IllegalArgumentException("La fecha debe tener 3 datos: dia, mes y anyo.");
		}
		this.dia = Integer.parseInt(datos[0]);
		this.mes = Integer.parseInt(datos[1]);
		this.anio = Integer.parseInt(datos[2]);
	}

	/**
	 * Devuelve verdadero si un anio es bisiesto.
	 * Devuelve falso en caso contrario.
	 * Un anio es bisiesto si cumple dos condiciones:
	 * - El anio es divisible entre 4.
	 * - El anio no es divisible entre 100 o el anio es divisible entre 400.
	 */
	private static boolean esBisiesto(int anio) {
		boolean bisiesto = false;
		if (anio >= 1583) {
			if ((anio % 4 == 0) && (anio % 100 != 0 || anio % 400 == 0)) {
				bisiesto = true;
			}
		}
		return bisiesto;
	}

	/**
	 * Devuelve el numero de dias que tiene un mes de un anio:
	 * - Febrero tiene 28 dias si el anio no es bisiesto.
	 * - Febrero tiene 29 dias si el anio es bisiesto.
	 * - Abril, Junio, Septiembre y Noviembre tienen 30 dias.
	 * - Enero, Marzo, Mayo, Julio, Agosto, Octubre y Diciembre tienen 31 dias.
	 */
	private static int obtenerNumeroDiasDeMes(int mes, int anio) {
		int numeroDiasMes = 31;
		if (mes == 2) {
			if (esBisiesto(anio)) {
				numeroDiasMes = 29;
			} 
			else {
				numeroDiasMes = 28;
			}
		}
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			numeroDiasMes = 30;
		}
		return numeroDiasMes;
	}

	/**
	 * Valida una fecha segun las condiciones:
	 * - Sigue el calendario gregoriano, que se basa en anios bisiestos y anios no bisiestos.
	 * - El anio debe ser igual o superior a 1583.
	 * - El mes debe estar comprendido entre 1 y 12.
	 * - El dia debe estar comprendido entre 1 y el numero de dias del mes del anio.
	 * Devuelve verdadero si la fecha es valida.
	 * Devuelve falso en caso contrario.
	 */
	public static boolean esValida(int dia, int mes, int anio) {
		boolean valida = true;
		if (anio < 1583) {
			valida = false;
		}
		else if (mes < 1 || mes > 12) {
			valida = false;
		}
		else if (dia < 1 || dia > obtenerNumeroDiasDeMes(mes, anio)) {
			valida = false;
		}
		return valida;
	}

	/**
	 * Devuelve una cadena con el estado de la fecha con formato DD/MM/AAAA.
	 */
	@Override
	public String toString() {
		String fecha = String.format("%02d/%02d/%04d", dia, mes, anio);
		return fecha;
	}

	/**
	 * Devuelve el dia de la fecha.
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Devuelve el mes de la fecha.
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Devuelve el anio de la fecha.
	 */
	public int getAnio() {
		return anio;
	}

}
