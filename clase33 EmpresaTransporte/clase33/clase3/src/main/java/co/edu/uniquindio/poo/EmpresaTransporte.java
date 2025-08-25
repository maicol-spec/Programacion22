package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class EmpresaTransporte implements IEmpresaTransportServices {

    private String nombre;

    private List<VehiculoCarga> listaVehiculosCarga = new ArrayList<>();
    private List<VehiculoPasajero> listaVehiculosPasajeros = new ArrayList<>();
    private List<Propietario> listaAsociados = new ArrayList<>();

    public EmpresaTransporte() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<VehiculoCarga> getListaVehiculosCarga() {
        return listaVehiculosCarga;
    }

    public void setListaVehiculosCarga(List<VehiculoCarga> listaVehiculosCarga) {
        this.listaVehiculosCarga = listaVehiculosCarga;
    }

    public List<VehiculoPasajero> getListaVehiculosPasajeros() {
        return listaVehiculosPasajeros;
    }

    public void setListaVehiculosPasajeros(List<VehiculoPasajero> listaVehiculosPasajeros) {
        this.listaVehiculosPasajeros = listaVehiculosPasajeros;
    }

    public List<Propietario> getListaPropietarios() {
        return listaAsociados;
    }

    public void setListaAsociados(List<Propietario> listaAsociados) {
        this.listaAsociados = listaAsociados;
    }




    // Método asociar un vehículo de carga a un propietario
        public boolean asociarVehiculoCargaAPropietario(String numeroIdentificacionPropietario, String placaVehiculo) {
        Propietario propietario = obtenerPropietario(numeroIdentificacionPropietario);
        VehiculoCarga vehiculo = obtenerVehiculoCarga(placaVehiculo);

        if (propietario != null && vehiculo != null) {
            propietario.getListaVehiculosAsociados().add(vehiculo);
            return true;
        }
        return false;
    }

    // Obtener pasajeros transportados por placa

    public int obtenerPasajerosTransportadosPorPlaca(String placa) {
        for (VehiculoPasajero vehiculo : getListaVehiculosPasajeros()) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo.getPasajerosTransportados(); 
            }
        }
        return 0; 
    }

    //Metodo Obtener cantidad de prpietarios mayores de 40

    public int obtenerCantidadPropietariosMayoresDe40() {
        int contador = 0;
        for (Propietario propietario : getListaPropietarios()) {
            if (propietario.getEdad() > 40) {
                contador++;
            }
        }
        return contador;
    } 
    
    

    // NUEVO MÉTODO: Asociar un vehículo de carga a un propietario
    public boolean asociarVehiculoCargaAPropietario(String numeroIdentificacion, VehiculoCarga vehiculo) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if (propietario != null && vehiculo != null) {
            propietario.getListaVehiculosAsociados().add(vehiculo);
            return true;
        }
        return false;
    }

    

    // NUEVO MÉTODO: Mostrar propietarios que superan un valor de peso
    public void mostrarPropietariosPorPeso(double pesoMinimo) {
    StringBuilder resultado = new StringBuilder();
    resultado.append("Propietarios con vehículos que superan el peso de ")
             .append(pesoMinimo).append(":\n");

    for (Propietario propietario : getListaPropietarios()) {
        for (VehiculoCarga vehiculo : propietario.getListaVehiculosAsociados()) {
            if (vehiculo.getCapacidadCarga() > pesoMinimo) {
                resultado.append("Nombre: ").append(propietario.getNombre())
                         .append(", Vehículo: ").append(vehiculo.getPlaca())
                         .append(", Peso: ").append(vehiculo.getCapacidadCarga())
                         .append("\n");
            }
        }
    }
    if (resultado.toString().equals("Propietarios con vehículos que superan el peso de " + pesoMinimo + ":\n")) {
        JOptionPane.showMessageDialog(null, "No se encontraron propietarios con vehículos que superen " + pesoMinimo);
    } else {
        JOptionPane.showMessageDialog(null, resultado.toString());
    }
}



    // CRUD PROPIETARIO
    @Override
    public boolean agregarPropietario(String nombre, String numeroIdentificacion, String email, String numeroCelular,int edad) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if (propietario == null) {
            propietario = new Propietario();
            propietario.setNombre(nombre);
            propietario.setNumeroIdentificacion(numeroIdentificacion);
            propietario.setEmail(email);
            propietario.setNumeroCelular(numeroCelular);
            propietario.setEdad(edad);
            getListaPropietarios().add(propietario);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Propietario obtenerPropietario(String numeroIdentificacion) {
        Propietario propietarioEncontrado = null;
        for (Propietario propietario : getListaPropietarios()) {
            if (propietario.getNumeroIdentificacion().equalsIgnoreCase(numeroIdentificacion)) {
                propietarioEncontrado = propietario;
                break;
            }
        }
        return propietarioEncontrado;
    }

    @Override
    public boolean eliminarPropietario(String numeroIdentificacion) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if (propietario != null) {
            getListaPropietarios().remove(propietario);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean actualizarPropietario(String nombre, String numeroIdentificacionActual, String numeroIdentificacion,
            String email, String numeroCelular,int edad) {
        Propietario propietario = obtenerPropietario(numeroIdentificacionActual);
        if (propietario != null) {
            propietario.setNombre(nombre);
            propietario.setNumeroIdentificacion(numeroIdentificacion);
            propietario.setEmail(email);
            propietario.setNumeroCelular(numeroCelular);
            return true;
        } else {
            return false;
        }
    }

    // CRUD VEHICULOCARGA
    @Override
    public boolean agregarVehiculoCarga(String placa, String modelo, String marca, String color, double capacidadCarga,
            int numeroEjes) {
        VehiculoCarga vehiculoCarga = obtenerVehiculoCarga(placa);
        if (vehiculoCarga == null) {
            vehiculoCarga = new VehiculoCarga();
            vehiculoCarga.setPlaca(placa);
            vehiculoCarga.setModelo(modelo);
            vehiculoCarga.setMarca(marca);
            vehiculoCarga.setColor(color);
            vehiculoCarga.setCapacidadCarga(capacidadCarga);
            vehiculoCarga.setNumeroEjes(numeroEjes);
            getListaVehiculosCarga().add(vehiculoCarga);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public VehiculoCarga obtenerVehiculoCarga(String placa) {
        VehiculoCarga vehiculoCargaEncontrado = null;
        for (VehiculoCarga vehiculoCarga : getListaVehiculosCarga()) {
            if (vehiculoCarga.getPlaca().equalsIgnoreCase(placa)) {
                vehiculoCargaEncontrado = vehiculoCarga;
                break;
            }
        }
        return vehiculoCargaEncontrado;
    }

    @Override
    public boolean actualizarVehiculoCarga(String placa, String modelo, String marca, String color,
            double capacidadCarga, int numeroEjes) {
        VehiculoCarga vehiculoCarga = obtenerVehiculoCarga(placa);
        if (vehiculoCarga != null) {
            vehiculoCarga.setPlaca(placa);
            vehiculoCarga.setModelo(modelo);
            vehiculoCarga.setMarca(marca);
            vehiculoCarga.setColor(color);
            vehiculoCarga.setCapacidadCarga(capacidadCarga);
            vehiculoCarga.setNumeroEjes(numeroEjes);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculoCarga(String placa) {
        VehiculoCarga vehiculo = obtenerVehiculoCarga(placa);
        if (vehiculo != null) {
            getListaVehiculosCarga().remove(vehiculo);
            return true;
        } else {
            return false;
        }
    }



    // CRUD VEHICULOPASAJERO
    @Override
    public boolean agregarVehiculoPasajero(String placa, String modelo, String marca, String color,
            int numeroMaximoPasajeros,int pasajerosTransportados) {
        VehiculoPasajero vehiculoPasajero = obtenerVehiculoPasajero(placa);
        if (vehiculoPasajero == null) {
            vehiculoPasajero = new VehiculoPasajero();
            vehiculoPasajero.setPlaca(placa);
            vehiculoPasajero.setModelo(modelo);
            vehiculoPasajero.setMarca(marca);
            vehiculoPasajero.setColor(color);
            vehiculoPasajero.setNumeroMaximoPasajeros(numeroMaximoPasajeros);
            vehiculoPasajero.setPasajerosTransportados(pasajerosTransportados); 
            getListaVehiculosPasajeros().add(vehiculoPasajero);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public VehiculoPasajero obtenerVehiculoPasajero(String placa) {
        VehiculoPasajero vehiculoPasajeroEncontrado = null;
        for (VehiculoPasajero vehiculoPasajero : getListaVehiculosPasajeros()) {
            if (vehiculoPasajero.getPlaca().equalsIgnoreCase(placa)) {
                vehiculoPasajeroEncontrado = vehiculoPasajero;
                break;
            }
        }
        return vehiculoPasajeroEncontrado;
    }

    @Override
    public boolean actualizarVehiculoPasajero(String placa, String modelo, String marca, String color,
            int numeroMaximoPasajeros,int pasajerosTransportados) {
        VehiculoPasajero vehiculoPasajero = obtenerVehiculoPasajero(placa);
        if (vehiculoPasajero != null) {
            vehiculoPasajero.setPlaca(placa);
            vehiculoPasajero.setModelo(modelo);
            vehiculoPasajero.setMarca(marca);
            vehiculoPasajero.setColor(color);
            vehiculoPasajero.setNumeroMaximoPasajeros(numeroMaximoPasajeros);
            vehiculoPasajero.setPasajerosTransportados(pasajerosTransportados); 
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculoPasajero(String placa) {
        VehiculoPasajero vehiculo = obtenerVehiculoPasajero(placa);
        if (vehiculo != null) {
            getListaVehiculosPasajeros().remove(vehiculo);
            return true;
        } else {
            return false;
        }
    }
}

