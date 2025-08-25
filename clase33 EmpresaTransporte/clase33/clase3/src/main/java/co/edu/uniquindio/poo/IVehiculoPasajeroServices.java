package co.edu.uniquindio.poo;


public interface IVehiculoPasajeroServices {

    boolean agregarVehiculoPasajero(String placa,
                                 String modelo,
                                 String marca,
                                 String color,
                                 int numeroMaximoPasajeros,
                                 int pasajerosTransportados);

    VehiculoPasajero obtenerVehiculoPasajero(String placa);

    boolean eliminarVehiculoPasajero(String placa);

    boolean actualizarVehiculoPasajero(
                                    String placa,
                                    String modelo,
                                    String marca,
                                    String color,
                                    int numeroMaximoPasajeros,
                                    int pasajerosTransportados
                                    );

    int obtenerPasajerosTransportadosPorPlaca(String placa);
}

