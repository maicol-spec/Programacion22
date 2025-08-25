package co.edu.uniquindio.poo;

public class ModelFactory implements IModelFactoryServices {

    private static ModelFactory modelFactory;
    private EmpresaTransporte empresaTransporte;

    private ModelFactory(){
        inicializarDatos();
    }

    public static ModelFactory getInstance() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }

        return modelFactory;
    }

    private void inicializarDatos() {
        empresaTransporte = new EmpresaTransporte();

        Propietario propietario1 = new Propietario();
        propietario1.setNombre("Pedro");
        propietario1.setNumeroIdentificacion("978978798");

        Propietario propietario2 = new Propietario();
        propietario2.setNombre("Ana");
        propietario2.setNumeroIdentificacion("234242323");

        VehiculoCarga vehiculoCarga1 = new VehiculoCarga();
        vehiculoCarga1.setPlaca("RRD098");
        vehiculoCarga1.setNumeroEjes(5);

        VehiculoCarga vehiculoCarga2 = new VehiculoCarga();
        vehiculoCarga2.setPlaca("XXX096");
        vehiculoCarga2.setNumeroEjes(3);

        VehiculoCarga vehiculoCarga3 = new VehiculoCarga();
        vehiculoCarga3.setPlaca("77X096");
        vehiculoCarga3.setNumeroEjes(5);

        propietario1.setVehiculo(vehiculoCarga1);
        propietario1.getListaVehiculosAsociados().add(vehiculoCarga2);
        propietario1.getListaVehiculosAsociados().add(vehiculoCarga3);

        propietario2.setVehiculo(vehiculoCarga2);

        empresaTransporte.getListaPropietarios().add(propietario1);
        empresaTransporte.getListaPropietarios().add(propietario2);

        empresaTransporte.getListaVehiculosCarga().add(vehiculoCarga1);
        empresaTransporte.getListaVehiculosCarga().add(vehiculoCarga2);
        empresaTransporte.getListaVehiculosCarga().add(vehiculoCarga3);
    }

    public int obtenerCantidadPropietariosMayoresDe40() {
        return empresaTransporte.obtenerCantidadPropietariosMayoresDe40();
    }

    public int obtenerPasajerosTransportadosPorPlaca(String placa) {
        return empresaTransporte.obtenerPasajerosTransportadosPorPlaca(placa);
    }

    public void mostrarPropietariosPorPeso(double pesoMinimo) {
        empresaTransporte.mostrarPropietariosPorPeso(pesoMinimo);
    }

    // ---------------------- CRUD PROPIETARIO ----------------------

    @Override
    public boolean agregarPropietario(String nombre,String numeroIdentificacion, String email, String numeroCelular, int edad) {
        return empresaTransporte.agregarPropietario(nombre, numeroIdentificacion, email, numeroCelular, edad);
    }

    @Override
    public Propietario obtenerPropietario(String numeroIdentificacion) {
        return empresaTransporte.obtenerPropietario(numeroIdentificacion);
    }

    @Override
    public boolean eliminarPropietario(String numeroIdentificacion) {
        return empresaTransporte.eliminarPropietario(numeroIdentificacion);
    }

    @Override
    public boolean actualizarPropietario(String nombre, String numeroIdentificacionActual, String numeroIdentificacion, String email, String numeroCelular, int edad) {
        return empresaTransporte.actualizarPropietario(nombre, numeroIdentificacionActual, numeroIdentificacion, email, numeroCelular,edad);
    }

    // ---------------------- CRUD VEHÍCULO CARGA ----------------------

    @Override
    public boolean agregarVehiculoCarga(String placa, String modelo, String marca,String color, double capacidadCarga, int numeroEjes) {
        return empresaTransporte.agregarVehiculoCarga(placa, modelo, marca, color, capacidadCarga, numeroEjes);
    }

    @Override
    public VehiculoCarga obtenerVehiculoCarga(String placa) {
        return empresaTransporte.obtenerVehiculoCarga(placa);
    }

    @Override
    public boolean eliminarVehiculoCarga(String placa) {
        return empresaTransporte.eliminarVehiculoCarga(placa);
    }

    @Override
    public boolean actualizarVehiculoCarga(String placa, String modelo,String marca, String color, double capacidadCarga, int numeroEjes) {
        return empresaTransporte.actualizarVehiculoCarga(placa, modelo, marca, color, capacidadCarga, numeroEjes);
    }

    public boolean asociarVehiculoCargaAPropietario(String numeroIdentificacionPropietario, String placaVehiculo) {
        return empresaTransporte.asociarVehiculoCargaAPropietario(numeroIdentificacionPropietario, placaVehiculo);
    }





    // ---------------------- CRUD VEHÍCULO PASAJERO ----------------------
    @Override
    public boolean agregarVehiculoPasajero(String placa, String modelo, String marca, String color, int numeroMaximoPasajeros, int pasajerosTransportados) {
        return empresaTransporte.agregarVehiculoPasajero(placa, modelo, marca, color, numeroMaximoPasajeros, pasajerosTransportados);
    }
    @Override
    public VehiculoPasajero obtenerVehiculoPasajero(String placa) {
        return empresaTransporte.obtenerVehiculoPasajero(placa);
    }

    @Override
    public boolean eliminarVehiculoPasajero(String placa) {
        return empresaTransporte.eliminarVehiculoPasajero(placa);
    }

    @Override
    public boolean actualizarVehiculoPasajero(String placa, String modelo,String marca, String color,int numeroMaximoPasajeros, int pasajerosTransportados) {
        return empresaTransporte.actualizarVehiculoPasajero(placa, modelo, marca, color, numeroMaximoPasajeros, pasajerosTransportados);
    }
}
