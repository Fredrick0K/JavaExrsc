package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Cuenta;

public class AccesoBanco {

	public static void transferir(Cuenta cuentaOrigin, Cuenta cuentaDest, double cantidad)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Connection conexion = null;

		try {

			conexion = MySQLUtil.abrirConexion();
			conexion.setAutoCommit(false);

			String sql1 = "UPDATE cuenta " + "SET saldo = saldo - ? "
					+ "WHERE codigo_cliente = ? and codigo_sucursal = ? and fecha_apertura = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql1);

			sentencia1.setDouble(1, cantidad);
			sentencia1.setInt(2, cuentaOrigin.getCodigoCliente().getCodigo());
			sentencia1.setInt(3, cuentaOrigin.getCodigoSucursal().getCodigo());
			sentencia1.setDate(4, cuentaOrigin.getFechaApertura());
			sentencia1.executeUpdate();

			String sql2 = "UPDATE cuenta " + "SET saldo = saldo + ? "
					+ "WHERE codigo_cliente = ? and codigo_sucursal = ? and fecha_apertura = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(sql2);

			sentencia2.setDouble(1, cantidad);
			sentencia2.setInt(2, cuentaDest.getCodigoCliente().getCodigo());
			sentencia2.setInt(3, cuentaDest.getCodigoSucursal().getCodigo());
			sentencia2.setDate(4, cuentaDest.getFechaApertura());
			sentencia2.executeUpdate();
			conexion.commit();

		} catch (SQLException sqle) {
			if (conexion != null && !conexion.isClosed()) {
				conexion.rollback();
			}
			throw sqle;
		} finally {
			MySQLUtil.cerrarConexion(conexion);
		}

	}

	public static int[] migrarCuentas(int sucurOld, int sucurNew) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conexion = null;
		int cuentasMigradas, sucurEliminadas;
		int[] retorno = new int[2];
		try {
			conexion = MySQLUtil.abrirConexion();
			conexion.setAutoCommit(false);

			String sql1 = "UPDATE cuenta set codigo_sucursal = ? WHERE codigo_sucursal = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql1);
			sentencia1.setInt(1, sucurNew);
			sentencia1.setInt(2, sucurOld);
			cuentasMigradas = sentencia1.executeUpdate();

			String sql2 = "DELETE FROM sucursal WHERE codigo = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
			sentencia2.setInt(1, sucurOld);
			sucurEliminadas = sentencia2.executeUpdate();
			conexion.commit();
			retorno[0] = cuentasMigradas;
			retorno[1] = sucurEliminadas;
			return retorno;

		} catch (SQLException sqle) {
			if (conexion != null && !conexion.isClosed()) {
				conexion.rollback();
			}
			throw sqle;
		} finally {
			MySQLUtil.cerrarConexion(conexion);
		}

	}

}
