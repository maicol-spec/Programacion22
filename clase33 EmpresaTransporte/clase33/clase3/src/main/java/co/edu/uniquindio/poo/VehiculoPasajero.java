package co.edu.uniquindio.poo;

public class VehiculoPasajero extends Vehiculo {
    private int numeroMaximoPasajeros;
    private int pasajerosTransportados;

    public VehiculoPasajero() {
    }

    public VehiculoPasajero(String placa, String modelo, String marca, String color, int numeroMaximoPasajeros,int pasajerosTransportados) {
        super(placa, modelo, marca, color);
        this.numeroMaximoPasajeros = numeroMaximoPasajeros;
        this.pasajerosTransportados = pasajerosTransportados;
    }

    public int getNumeroMaximoPasajeros() {
        return numeroMaximoPasajeros;
    }

    public void setNumeroMaximoPasajeros(int numeroMaximoPasajeros) {
        this.numeroMaximoPasajeros = numeroMaximoPasajeros;
    }

     public int getPasajerosTransportados() {
        return pasajerosTransportados;
    }

    public void setPasajerosTransportados(int pasajerosTransportados) {
        this.pasajerosTransportados = pasajerosTransportados;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", capacidad máxima=" +numeroMaximoPasajeros +
                ", pasajeros transportados hoy=" + pasajerosTransportados;
    }
}

