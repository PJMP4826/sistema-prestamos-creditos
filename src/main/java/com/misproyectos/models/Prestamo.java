package com.misproyectos.models;

import com.misproyectos.enums.EstadoPrestamo;
import com.misproyectos.interfaces.BuilderInterface;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Prestamo {

    private final int idPrestamo;
    private final String uuid;
    private final int idCliente;
    private final BigDecimal montoPrestado;
    private final double tasaIntereses;
    private final double plazoPago;
    private Timestamp fechaInicio;
    private final double pagoMensual;
    private final double saldoPendiente;
    private final EstadoPrestamo estadoPrestamo;

    private Cliente cliente;
    private Periodicidad periodicidadPago;


    private Prestamo(PrestamoBuilder builder) {
        this.idPrestamo = builder.idPrestamo;
        this.uuid = builder.uuid;
        this.idCliente = builder.idCliente;
        this.montoPrestado = builder.montoPrestado;
        this.tasaIntereses = builder.tasaIntereses;
        this.plazoPago = builder.plazoPago;
        this.pagoMensual = builder.pagoMensual;
        this.saldoPendiente = builder.saldoPendiente;
        this.estadoPrestamo = builder.estadoPrestamo;
        this.cliente = builder.cliente;
        this.periodicidadPago = builder.periodicidadPago;
        this.fechaInicio = builder.fechaInicio;
    }

    public static PrestamoBuilder builder() {
        return new PrestamoBuilder();
    }

    public static class PrestamoBuilder implements BuilderInterface<Prestamo> {

        private int idPrestamo;
        private String uuid = UUID.randomUUID().toString();
        private int idCliente;
        private BigDecimal montoPrestado;
        private double tasaIntereses;
        private double plazoPago;
        private Timestamp fechaInicio;
        private double pagoMensual;
        private double saldoPendiente;
        private EstadoPrestamo estadoPrestamo = EstadoPrestamo.ACTIVO;
        private Cliente cliente;
        private Periodicidad periodicidadPago;

        public PrestamoBuilder() {
        }

        public PrestamoBuilder setIdPrestamo(int idPrestamo) {
            this.idPrestamo = idPrestamo;
            return this;
        }

        public PrestamoBuilder setUUID(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public PrestamoBuilder setIdCliente(int idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public PrestamoBuilder setMontoPrestado(BigDecimal montoPrestado) {
            this.montoPrestado = montoPrestado;
            return this;
        }

        public PrestamoBuilder setTasaIntereses(double tasaIntereses) {
            this.tasaIntereses = tasaIntereses;
            return this;
        }

        public PrestamoBuilder setPlazoPago(double plazoPago) {
            this.plazoPago = plazoPago;
            return this;
        }

        public PrestamoBuilder setFechaInicio(Timestamp fechaInicio){
            this.fechaInicio = fechaInicio;
            return this;
        }

        public PrestamoBuilder setPagoMensual(double pagoMensual) {
            this.pagoMensual = pagoMensual;
            return this;
        }

        public PrestamoBuilder setSaldoPendiente(double saldoPendiente) {
            this.saldoPendiente = saldoPendiente;
            return this;
        }

        public PrestamoBuilder setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
            this.estadoPrestamo = estadoPrestamo;
            return this;
        }

        public PrestamoBuilder setCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public PrestamoBuilder setPeriodicidadPago(Periodicidad periodicidadPago) {
            this.periodicidadPago = periodicidadPago;
            return this;
        }

        @Override
        public Prestamo build() {
            return new Prestamo(this);
        }
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public BigDecimal getMontoPrestado() {
        return montoPrestado;
    }

    public double getTasaIntereses() {
        return tasaIntereses;
    }

    public double getPlazoPago() {
        return plazoPago;
    }

    public Timestamp getFechaInicio(){
        return fechaInicio;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Periodicidad getPeriodicidadPago() {
        return periodicidadPago;
    }


    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", uuid='" + uuid + '\'' +
                ", idCliente=" + idCliente +
                ", montoPrestado=" + montoPrestado +
                ", tasaIntereses=" + tasaIntereses +
                ", plazoPago=" + plazoPago +
                ", fechaInicio=" + fechaInicio +
                ", pagoMensual=" + pagoMensual +
                ", saldoPendiente=" + saldoPendiente +
                ", estadoPrestamo=" + estadoPrestamo +
                ", cliente=" + cliente +
                ", periodicidadPago=" + periodicidadPago +
                '}';
    }
}
