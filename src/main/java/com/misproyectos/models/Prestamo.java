package com.misproyectos.models;

import com.misproyectos.enums.EstadoPrestamo;
import com.misproyectos.interfaces.BuilderInterface;

import java.util.UUID;

public class Prestamo {

    private final int idPrestamo;
    private final String uuid;
    private final int idCliente;
    private final double montoPrestado;
    private final double tasaIntereses;
    private final double plazoPago;
    private final double pagoMensual;
    private final double saldoPendiente;
    private final EstadoPrestamo estadoPrestamo;


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
    }

    public static PrestamoBuilder builder() {
        return new PrestamoBuilder();
    }

    public static class PrestamoBuilder implements BuilderInterface<Prestamo> {

        private int idPrestamo;
        private String uuid = UUID.randomUUID().toString();
        private int idCliente;
        private double montoPrestado;
        private double tasaIntereses;
        private double plazoPago;
        private double pagoMensual;
        private double saldoPendiente;
        private EstadoPrestamo estadoPrestamo = EstadoPrestamo.ACTIVO;

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

        public PrestamoBuilder setMontoPrestado(double montoPrestado) {
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

    public double getMontoPrestado() {
        return montoPrestado;
    }

    public double getTasaIntereses() {
        return tasaIntereses;
    }

    public double getPlazoPago() {
        return plazoPago;
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

    @Override
    public String toString() {
        return " Prestamo: {" +
                "\n   idPrestamo=" + idPrestamo +
                ", \n   UUID=" + uuid +
                ", \n   idCliente=" + idCliente +
                ", \n   montoPrestado=" + montoPrestado +
                ", \n   tasaIntereses=" + tasaIntereses +
                ", \n   plazoPago=" + plazoPago +
                ", \n   pagoMensual=" + pagoMensual +
                ", \n   saldoPendiente=" + saldoPendiente +
                ", \n   estadoPrestamo=" + estadoPrestamo +
                "\n }";
    }
}
