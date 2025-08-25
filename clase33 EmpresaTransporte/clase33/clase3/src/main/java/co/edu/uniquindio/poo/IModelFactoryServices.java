package co.edu.uniquindio.poo;

public interface IModelFactoryServices 
        extends IPropietarioServices, IVehiculoCargaServices, IVehiculoPasajeroServices {

    int obtenerCantidadPropietariosMayoresDe40();

    int obtenerPasajerosTransportadosPorPlaca(String placa);

    void mostrarPropietariosPorPeso(double pesoMinimo);

    boolean asociarVehiculoCargaAPropietario(String numeroIdentificacionPropietario, String placaVehiculo);
}
