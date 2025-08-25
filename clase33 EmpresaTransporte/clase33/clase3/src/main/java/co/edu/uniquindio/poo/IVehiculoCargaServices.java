package co.edu.uniquindio.poo;

public interface IVehiculoCargaServices {

    boolean agregarVehiculoCarga(String placa,
                                 String modelo,
                                 String marca,
                                 String color,
                                 double capacidadCarga,
                                 int numeroEjes);

    VehiculoCarga obtenerVehiculoCarga(String placa);

    boolean eliminarVehiculoCarga(String placa);

    boolean actualizarVehiculoCarga(
                                    String placa,
                                    String modelo,
                                    String marca,
                                    String color,
                                    double capacidadCarga,
                                    int numeroEjes);
}
