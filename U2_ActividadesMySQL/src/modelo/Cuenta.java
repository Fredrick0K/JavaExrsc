package modelo;

import java.sql.Date;

public class Cuenta {

	private Cliente cliente;
	private Sucursal sucursal;
	private Date fechaApertura;
	private String tipo;
	private double saldo;
	private double interes;

	public Cuenta(Cliente codigoCliente, Sucursal codigoSucursal, Date fechaApertura, String tipo, double saldo,
			double interes) {
		this.cliente = codigoCliente;
		this.sucursal = codigoSucursal;
		this.fechaApertura = fechaApertura;
		this.tipo = tipo;
		this.saldo = saldo;
		this.interes = interes;
	}

	public Cuenta(Cliente codigoCliente, Sucursal codigoSucursal, Date fechaApertura) {

		this.cliente = codigoCliente;
		this.sucursal = codigoSucursal;
		this.fechaApertura = fechaApertura;

	}

	public Cuenta(Cliente codigoCliente) {

		this.cliente = codigoCliente;

	}

	public Cuenta(Sucursal codigoSucursal) {

		this.sucursal = codigoSucursal;

	}

	@Override
	public String toString() {
		return "Cuenta [codigoCliente=" + cliente.getCodigo() + ", codigoSucursal=" + sucursal.getCodigo()
				+ ", fechaApertura=" + fechaApertura.toString() + ", tipo=" + tipo + ", saldo=" + saldo + ", interes="
				+ interes + "]";
	}

	public Cliente getCodigoCliente() {
		return cliente;
	}

	public Sucursal getCodigoSucursal() {
		return sucursal;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

}
