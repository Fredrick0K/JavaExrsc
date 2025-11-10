package principal;

import java.sql.Date;
import java.sql.SQLException;

import acceso.AccesoBanco;
import entrada.Teclado;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Sucursal;

public class Principal {

	private static void escribirMenu() {
		System.out.println("0) - Salir");
		System.out.println("1) - Transferir cantidad de una cuenta a otra.");
		System.out.println("2) - Migrar cuentas de una sucursal a otra.");
	}

	public static void main(String[] args) {
		Cuenta cuentaOrigin;
		Cuenta cuentaDest;

		int opcion;

		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");

			try {
				switch (opcion) {
				case 0:
					System.out.println("Hasta Pronto!");
					break;

				case 1:
					System.out.println("-------ORIGEN---------");
					int codCliente = Teclado.leerEntero("Codigo del cliente: ");
					int codSucursal = Teclado.leerEntero("Codigo de la Sucursal: ");
					Date fechaApertura = Consola.leerFecha("Fecha de Apertura: ");
					cuentaOrigin = new Cuenta(new Cliente(codCliente), new Sucursal(codSucursal), fechaApertura);

					System.out.println("-------Destino---------");
					codCliente = Teclado.leerEntero("Codigo del cliente: ");
					codSucursal = Teclado.leerEntero("Codigo de la Sucursal: ");
					fechaApertura = Consola.leerFecha("Fecha de Apertura: ");
					cuentaDest = new Cuenta(new Cliente(codCliente), new Sucursal(codSucursal), fechaApertura);

					double cantidad = Teclado.leerReal("Cantidad: ");
					try {
						AccesoBanco.transferir(cuentaOrigin, cuentaDest, cantidad);
						System.out.println("Wire succesfull!");
					} catch (SQLException dql) {
						System.out.println("Error en la transfaccion.");
						dql.printStackTrace();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					break;

				case 2:
					int sucurOld = Teclado.leerEntero("Sucursal antigua: ");
					int SucurNew = Teclado.leerEntero("Sucursal nueva: ");

					try {
						int[] result = AccesoBanco.migrarCuentas(sucurOld, SucurNew);

						if (result[0] == 0 && result[1] == 0) {
							System.out.println("Sucursal no encontrada. Introduzca un valor correcto.");
						} else {
							System.out.println(
									"Se ha(n) migrado " + result[0] + " cuentas y se ha(n) eliminado " + result[1]);
						}

					} catch (SQLException e) {
						// TODO: handle exception
						System.out.println("Error en la transaccion.");
						e.printStackTrace();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} while (opcion != 0);

	}

}
