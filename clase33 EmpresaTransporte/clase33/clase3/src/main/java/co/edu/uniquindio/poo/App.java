package co.edu.uniquindio.poo;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        ModelFactory modelFactory = ModelFactory.getInstance();
        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog("""
                    --- Empresa La Carreta ---
                    1. Menu Propietario
                    2. Menu Vehículo de Carga
                    3. Menu Vehículo de Pasajero
                    4. Consultar pasajeros transportados por placa
                    5. Consultar propietarios mayores de 40 años
                    6. Mostrar propietarios por peso de vehículo
                    7. Salir
                    Seleccione una opción:
                    """);

            if (opcion == null) {
                salir = true;
            } else {
                switch (opcion) {
                    case "1" -> menuPropietario(modelFactory);
                    case "2" -> menuVehiculoCarga(modelFactory);
                    case "3" -> menuVehiculoPasajero(modelFactory);
                    case "4" -> consultarPasajerosPorPlaca(modelFactory);
                    case "5" -> consultarPropietariosMayoresDe40(modelFactory);
                    case "6" -> mostrarPropietariosPorPeso(modelFactory);
                    case "7" -> salir = true;
                    default -> JOptionPane.showMessageDialog(null, " Opción no válida");
                }
            }
        }
    }

    // ---------------------- MENÚ PROPIETARIO ----------------------
    private static void menuPropietario(ModelFactory modelFactory) {
        boolean atras = false;

        while (!atras) {
            String opcion = JOptionPane.showInputDialog("""
                    --- PROPIETARIO ---
                    1. Agregar propietario
                    2. Obtener propietario
                    3. Actualizar propietario
                    4. Eliminar propietario
                    5. Atrás
                    """);

            if (opcion == null) {
                atras = true;
            } else {
                switch (opcion) {
                    case "1" -> agregarPropietario(modelFactory);
                    case "2" -> obtenerPropietario(modelFactory);
                    case "3" -> actualizarPropietario(modelFactory);
                    case "4" -> eliminarPropietario(modelFactory);
                    case "5" -> atras = true;
                    default -> JOptionPane.showMessageDialog(null, " Opción no válida");
                }
            }
        }
    }

    private static void agregarPropietario(ModelFactory modelFactory) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificación:");
        String email = JOptionPane.showInputDialog("Ingrese el email:");
        String celular = JOptionPane.showInputDialog("Ingrese el celular:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad:"));

        boolean resultado = modelFactory.agregarPropietario(nombre, identificacion, email, celular, edad);

        if (resultado) JOptionPane.showMessageDialog(null, "Propietario agregado correctamente");
        else JOptionPane.showMessageDialog(null, "Ya existe un propietario con esa identificación");
    }

    private static void obtenerPropietario(ModelFactory modelFactory) {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificación del propietario:");
        Propietario propietario = modelFactory.obtenerPropietario(identificacion);

        if (propietario != null) {
            StringBuilder info = new StringBuilder();
            info.append("Propietario encontrado:\n").append(propietario);

            if (!propietario.getListaVehiculosAsociados().isEmpty()) {
                info.append("\nVehículos de carga asociados:\n");
                for (VehiculoCarga vehiculo : propietario.getListaVehiculosAsociados()) {
                    info.append(vehiculo).append("\n");
                }
            } else {
                info.append("\nNo tiene vehículos de carga asociados.");
            }

            JOptionPane.showMessageDialog(null, info.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Propietario no encontrado");
        }
    }

    private static void actualizarPropietario(ModelFactory modelFactory) {
        String idActual = JOptionPane.showInputDialog("Ingrese la identificación actual del propietario:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        String nuevaId = JOptionPane.showInputDialog("Ingrese la nueva identificación:");
        String email = JOptionPane.showInputDialog("Ingrese el nuevo email:");
        String celular = JOptionPane.showInputDialog("Ingrese el nuevo celular:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad:"));

        boolean resultado = modelFactory.actualizarPropietario(nombre, idActual, nuevaId, email, celular, edad);

        if (resultado) JOptionPane.showMessageDialog(null, "Propietario actualizado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró propietario con esa identificación");
    }

    private static void eliminarPropietario(ModelFactory modelFactory) {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificación del propietario a eliminar:");
        boolean resultado = modelFactory.eliminarPropietario(identificacion);

        if (resultado) JOptionPane.showMessageDialog(null, "Propietario eliminado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró propietario con esa identificación");
    }

    // ---------------------- MENÚ VEHÍCULO CARGA ----------------------
    private static void menuVehiculoCarga(ModelFactory modelFactory) {
        boolean atras = false;

        while (!atras) {
            String opcion = JOptionPane.showInputDialog("""
                    --- CRUD VEHÍCULO DE CARGA ---
                    1. Agregar vehículo de carga
                    2. Obtener vehículo de carga
                    3. Actualizar vehículo de carga
                    4. Eliminar vehículo de carga
                    5. Asociar vehículo de carga a propietario
                    6. Atrás
                    """);

            if (opcion == null) {
                atras = true;
            } else {
                switch (opcion) {
                    case "1" -> agregarVehiculoCarga(modelFactory);
                    case "2" -> obtenerVehiculoCarga(modelFactory);
                    case "3" -> actualizarVehiculoCarga(modelFactory);
                    case "4" -> eliminarVehiculoCarga(modelFactory);
                    case "5" -> asociarVehiculoCargaAPropietario(modelFactory);
                    case "6" -> atras = true;
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            }
        }
    }

    private static void agregarVehiculoCarga(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        String color = JOptionPane.showInputDialog("Ingrese el color:");
        double capacidadCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad de carga:"));
        int numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de ejes:"));

        boolean resultado = modelFactory.agregarVehiculoCarga(placa, modelo, marca, color, capacidadCarga, numeroEjes);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de carga agregado correctamente");
        else JOptionPane.showMessageDialog(null, "Ya existe un vehículo con esa placa");
    }

    private static void obtenerVehiculoCarga(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa:");
        VehiculoCarga vehiculo = modelFactory.obtenerVehiculoCarga(placa);

        if (vehiculo != null) {
            JOptionPane.showMessageDialog(null, "Vehículo encontrado:\n" + vehiculo);
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    private static void actualizarVehiculoCarga(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a actualizar:");
        String modelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo:");
        String marca = JOptionPane.showInputDialog("Ingrese la nueva marca:");
        String color = JOptionPane.showInputDialog("Ingrese el nuevo color:");
        double capacidadCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva capacidad de carga:"));
        int numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número de ejes:"));

        boolean resultado = modelFactory.actualizarVehiculoCarga(placa, modelo, marca, color, capacidadCarga, numeroEjes);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de carga actualizado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró vehículo con esa placa");
    }

    private static void eliminarVehiculoCarga(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a eliminar:");
        boolean resultado = modelFactory.eliminarVehiculoCarga(placa);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de carga eliminado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró vehículo con esa placa");
    }

    private static void asociarVehiculoCargaAPropietario(ModelFactory modelFactory) {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificación del propietario:");
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo de carga:");

        boolean resultado = modelFactory.asociarVehiculoCargaAPropietario(identificacion, placa);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo asociado correctamente al propietario");
        else JOptionPane.showMessageDialog(null, "No se pudo asociar (verifique propietario y vehículo)");
    }

    // ---------------------- MENÚ VEHÍCULO PASAJERO ----------------------
    private static void menuVehiculoPasajero(ModelFactory modelFactory) {
        boolean atras = false;

        while (!atras) {
            String opcion = JOptionPane.showInputDialog("""
                    --- CRUD VEHÍCULO DE PASAJERO ---
                    1. Agregar vehículo de pasajero
                    2. Obtener vehículo de pasajero
                    3. Actualizar vehículo de pasajero
                    4. Eliminar vehículo de pasajero
                    5. Atrás
                    """);

            if (opcion == null) {
                atras = true;
            } else {
                switch (opcion) {
                    case "1" -> agregarVehiculoPasajero(modelFactory);
                    case "2" -> obtenerVehiculoPasajero(modelFactory);
                    case "3" -> actualizarVehiculoPasajero(modelFactory);
                    case "4" -> eliminarVehiculoPasajero(modelFactory);
                    case "5" -> atras = true;
                    default -> JOptionPane.showMessageDialog(null, " Opción no válida");
                }
            }
        }
    }

    private static void agregarVehiculoPasajero(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        String color = JOptionPane.showInputDialog("Ingrese el color:");
        int numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número máximo de pasajeros:"));
        int pasajerosTransportados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de pasajeros transportados hoy:"));

        boolean resultado = modelFactory.agregarVehiculoPasajero(placa, modelo, marca, color, numeroPasajeros, pasajerosTransportados);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de pasajero agregado correctamente");
        else JOptionPane.showMessageDialog(null, "Ya existe un vehículo con esa placa");
    }

    private static void obtenerVehiculoPasajero(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa:");
        VehiculoPasajero vehiculo = modelFactory.obtenerVehiculoPasajero(placa);

        if (vehiculo != null) {
            JOptionPane.showMessageDialog(null, "Vehículo encontrado:\n" + vehiculo);
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    private static void actualizarVehiculoPasajero(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a actualizar:");
        String modelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo:");
        String marca = JOptionPane.showInputDialog("Ingrese la nueva marca:");
        String color = JOptionPane.showInputDialog("Ingrese el nuevo color:");
        int numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número máximo de pasajeros:"));
        int pasajerosTransportados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número de pasajeros transportados hoy:"));

        boolean resultado = modelFactory.actualizarVehiculoPasajero(placa, modelo, marca, color, numeroPasajeros, pasajerosTransportados);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de pasajero actualizado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró vehículo con esa placa");
    }

    private static void eliminarVehiculoPasajero(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a eliminar:");
        boolean resultado = modelFactory.eliminarVehiculoPasajero(placa);

        if (resultado) JOptionPane.showMessageDialog(null, "Vehículo de pasajero eliminado correctamente");
        else JOptionPane.showMessageDialog(null, "No se encontró vehículo con esa placa");
    }

    // ---------------------- CONSULTA DE PASAJEROS POR PLACA ----------------------
    private static void consultarPasajerosPorPlaca(ModelFactory modelFactory) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");
        int pasajeros = modelFactory.obtenerPasajerosTransportadosPorPlaca(placa);

        if (pasajeros >= 0) {
            JOptionPane.showMessageDialog(null, "Número de pasajeros transportados por el vehículo con placa " + placa + ": " + pasajeros);
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    // ---------------------- CONSULTA PROPIETARIOS MAYORES DE 40 ----------------------
    private static void consultarPropietariosMayoresDe40(ModelFactory modelFactory) {
        int cantidad = modelFactory.obtenerCantidadPropietariosMayoresDe40();
        JOptionPane.showMessageDialog(null, "Número de propietarios mayores de 40 años: " + cantidad);
    }

    // ---------------------- NUEVO MÉTODO: MOSTRAR PROPIETARIOS POR PESO ----------------------
    private static void mostrarPropietariosPorPeso(ModelFactory modelFactory) {
        double pesoMinimo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso mínimo:"));
        modelFactory.mostrarPropietariosPorPeso(pesoMinimo);
    }
}







