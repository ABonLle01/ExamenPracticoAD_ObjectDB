package org.example;

public class Main {
    public static void main(String[] args) {
        Cliente[] clientes = {
                new Cliente(1L, "Juan", 1500L, "Activo"),
                new Cliente(2L, "María", 2000L, "Inactivo"),
                new Cliente(3L, "Pedro", 0L, "Activo"),
                new Cliente(4L, "Laura", 2200L, "Activo"),
                new Cliente(5L, "Ana", 0L, "Inactivo")
        };
        try{
            for (Cliente c : clientes) {
                ClienteDAO.insertarCliente(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Obtener el cliente por su id");
        ClienteDAO.getCliente(3L);
        System.out.println();

        System.out.println("Ver los clientes con un total superior a cierta cantidad");
        ClienteDAO.listarMejoresClientes(1800L);
        System.out.println();

        System.out.println("Ver las estadisticas de todos los clientes");
        ClienteDAO.estadisticas();

        Util.closeEntityManagerFactory();
    }
}