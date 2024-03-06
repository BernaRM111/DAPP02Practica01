/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Berna
 */
public class DAPP02Practica01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DAOVenta daoVenta = new DAOVenta();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();

        int opcion;
        do {
            System.out.println("MENU");
            System.out.println("1. Guardar venta");
            System.out.println("2. Modificar venta");
            System.out.println("3. Eliminar venta");
            System.out.println("4. Buscar venta por ID");
            System.out.println("5. Mostrar todas las ventas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Guardar venta
                    Venta ventaGuardar = new Venta();
//                    ventaGuardar.setCliente("privado");
                    System.out.print("Ingrese el nombre del cliente: ");
                    String cliente = scanner.nextLine();
                    ventaGuardar.setCliente(cliente);
                    ventaGuardar.setFechaventa(new java.sql.Date(new Date().getTime()));
                    System.out.print("Ingrese el total de la venta: ");
                    double total = scanner.nextDouble();
                    ventaGuardar.setTotal(total);

                    System.out.println("Ingrese la cantidad de detalles de venta:");
                    int cantidadDetalles = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    ventaGuardar.setDetalleVenta(new ArrayList<>());

                    for (int i = 0; i < cantidadDetalles; i++) {
                        DetalleVenta detalle = new DetalleVenta();

                        System.out.println("Ingrese el nombre del producto:");
                        String producto = scanner.nextLine();
                        detalle.setProducto(producto);

                        System.out.println("Ingrese la cantidad:");
                        int cantidad = scanner.nextInt();
                        detalle.setCantidad(cantidad);
                        scanner.nextLine(); // Consumir el salto de línea

                        System.out.println("Ingrese el precio:");
                        double precio = scanner.nextDouble();
                        detalle.setPrecio(precio);
                        scanner.nextLine(); // Consumir el salto de línea

                        detalle.setVenta(ventaGuardar);
                        ventaGuardar.getDetalleVenta().add(detalle);
                    }

                    daoVenta.guardar(ventaGuardar);
                    break;
                case 2:
                    // Modificar venta
                    System.out.print("Ingrese el ID de la venta a modificar: ");
                    long idModificar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    Venta ventaModificar = daoVenta.buscarById(idModificar);

                    if (ventaModificar == null) {
                        System.out.println("No se encontró");
                        return;
                    }
                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                    String nuevoCliente = scanner.nextLine();
                    ventaModificar.setCliente(nuevoCliente);
                    
                    ventaModificar.setFechaventa(new java.sql.Date(new Date().getTime()));
                    
                    System.out.print("Ingrese el nuevo total de la venta: ");
                    double nuevoTotal = scanner.nextDouble();
                    ventaModificar.setTotal(nuevoTotal);
                    
                    daoVenta.modificar(ventaModificar);

                    System.out.println("Actualizado exitosamente");

//                    if (ventaModificar != null) {
//                        System.out.print("Ingrese el nuevo nombre del cliente: ");
//                        String nuevoCliente = scanner.nextLine();
//                        ventaModificar.setCliente(nuevoCliente);
//
//                        ventaModificar.setFechaventa(new java.sql.Date(new Date().getTime()));
//
//                        System.out.print("Ingrese el nuevo total de la venta: ");
//                        double nuevoTotal = scanner.nextDouble();
//                        ventaModificar.setTotal(nuevoTotal);
//
//                        Transaction transactionModificar = session.beginTransaction();
//                        daoVenta.modificar(ventaModificar);
//                        transactionModificar.commit();
//                        System.out.println("Venta modificada correctamente.");
//                    } else {
//                        System.out.println("Venta no encontrada.");
//                    }
                    break;
                case 3:
                    // Eliminar venta
                    System.out.print("Ingrese el ID de la venta a eliminar: ");
                    long idEliminar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    Venta ventaEliminar = daoVenta.buscarById(idEliminar);

                    if (ventaEliminar == null) {
                        System.out.println("No se encontró");
                        return;
                    }

                    daoVenta.eliminar(ventaEliminar);
                    System.out.println("Venta eliminada exitosamente.");
                    break;
                case 4:
//                     Buscar venta por ID
                    System.out.print("Ingrese el ID de la venta a buscar: ");
                    long idBuscar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    Venta ventaBuscar = daoVenta.buscarById(idBuscar);

                    if (ventaBuscar != null) {
                        System.out.println("Venta encontrada:");
                        System.out.println("ID: " + ventaBuscar.getId());
                        System.out.println("Cliente: " + ventaBuscar.getCliente());
                        System.out.println("Fecha: " + ventaBuscar.getFechaventa());
                        System.out.println("Total: " + ventaBuscar.getTotal());

                        List<DetalleVenta> detalles = ventaBuscar.getDetalleVenta();
                        if (!detalles.isEmpty()) {
                            System.out.println("Detalles de la venta:");
                            for (DetalleVenta detalle : detalles) {
                                System.out.println("   ID Detalle: " + detalle.getId());
                                System.out.println("   Producto: " + detalle.getProducto());
                                System.out.println("   Cantidad: " + detalle.getCantidad());
                                System.out.println("   Precio: " + detalle.getPrecio());
                            }
                        }
                    } else {
                        System.out.println("No se encontró");
                    }
                    break;

                case 5:
                    // Mostrar todas las ventas
                    List<Venta> ventas = daoVenta.buscarAll();

                    System.out.println("Listado de ventas:");
                    for (Venta venta : ventas) {
                        System.out.println("ID: " + venta.getId());
                        System.out.println("Cliente: " + venta.getCliente());
                        System.out.println("Fecha de venta: " + venta.getFechaventa());
                        System.out.println("Total: " + venta.getTotal());
                        System.out.println("Detalles de venta:");
                        for (DetalleVenta detalle : venta.getDetalleVenta()) {
                            System.out.println("  Producto: " + detalle.getProducto());
                            System.out.println("  Cantidad: " + detalle.getCantidad());
                            System.out.println("  Precio unitario: " + detalle.getPrecio());
                            System.out.println("");
                        }
                    }
                    break;
                case 0:

                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    System.out.println("");
            }
        } while (opcion != 0);

        scanner.close();
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session session = sf.getCurrentSession();
//        
//        Venta ven = new Venta();
//        ven.setCliente("Public General");
//        ven.setTotal(100.00d);
////        Date fechaLocal=new Date();
////        java.sql.Date mifecha= new java.sql.Date(fechaLocal.getTime());
//        ven.setFechaventa(new java.sql.Date(new Date().getTime()));
//        
////        Transaction tran = session.beginTransaction();
//        //Guardar encabezado
////        session.save(ven);
//        
//        for (int i =0; i < 5; i++ ){
//            DetalleVenta det = new DetalleVenta();
//            det.setPrecio(10);
//            det.setProducto("Producto " + (i+1));
//            det.setCantidad(10);
//            det.setVenta(ven);
//            ven.getDetalleVenta().add(det);
//        }
//        
//        DAOVenta dao = new DAOVenta();
//        dao.guardar(ven);
//        
////        session.save(ven);
////        tran.commit();
//        System.out.println("Se guardo con el ID: " + ven.getId());
//        
//        

//        PojoEmpleado empleado = new PojoEmpleado();
//        empleado.setNombre("BRM");
//        empleado.setDireccion("av.556");
//        empleado.setTelefono("223344");
//        Transaction tran = session.beginTransaction();
//        List<PojoEmpleado> empleados = session.createQuery("FROM PojoEmpleado").list();
//        tran.commit();
//        System.out.println("Todos los empleados:");
//        for (PojoEmpleado emp : empleados) {
//            System.out.println("ID: " + emp.getId() + ", Nombre: " + emp.getNombre() + ", Direccion: " + emp.getDireccion() + ", Telefono: " + emp.getTelefono());
//        }
//        Transaction tran = session.beginTransaction();
//        session.save(empleado);
//        tran.commit();
//        System.out.println("Se guardo con el ID: " + empleado.getId());
        // Buscar por id
//        Transaction tran = session.beginTransaction();
//        PojoEmpleado emp1 = session.get(PojoEmpleado.class, 3);
////        PojoEmpleado emp2 = session.load(PojoEmpleado.class, 1);
//        tran.commit();
//        if (emp1 != null) {
//            System.out.println("Nombre: " + emp1.getNombre());
//        } else {
//            System.out.println("No se encontro");
//        }
// Eliminar empleado
//        Transaction tran = session.beginTransaction();
//        session.delete(3);
//        tran.commit();
//        System.out.println("Se elimino correctamente");
        // Modificar empleado
//        Transaction tran2 = session.beginTransaction();
//        emp1.setNombre("Nuevo Nombre");
//        session.update(emp1);
//        tran2.commit();
//        Scanner scanner = new Scanner(System.in);
//        DAOEmpleado dao = new DAOEmpleado();
//
//        boolean salir = false;
//        while (!salir) {
//            System.out.println("1. Guardar empleado");
//            System.out.println("2. Modificar empleado");
//            System.out.println("3. Eliminar empleado");
//            System.out.println("4. Buscar empleado por ID");
//            System.out.println("5. Mostrar todos los empleados");
//            System.out.println("6. Salir");
//            System.out.print("Seleccione una opción: ");
//            int opcion = scanner.nextInt();
//
//            switch (opcion) {
//                case 1:
//                    PojoEmpleado empleado = new PojoEmpleado();
//                    System.out.print("Nombre: ");
//                    empleado.setNombre(scanner.next());
//                    System.out.print("Dirección: ");
//                    empleado.setDireccion(scanner.next());
//                    System.out.print("Teléfono: ");
//                    empleado.setTelefono(scanner.next());
//                    boolean res = dao.guardar(empleado);
//                    if (res) {
//                        System.out.println("Empleado guardado exitosamente");
//                    } 
//                    break;
//                case 2:
//                    System.out.print("ID del empleado a modificar: ");
//                    int idModificar = scanner.nextInt();
//                    PojoEmpleado empleadoAModificar = dao.buscarById(idModificar);
//                    if (empleadoAModificar != null) {
//                        System.out.print("Nuevo nombre: ");
//                        empleadoAModificar.setNombre(scanner.next());
//                        System.out.print("Nueva dirección: ");
//                        empleadoAModificar.setDireccion(scanner.next());
//                        System.out.print("Nuevo telefono: ");
//                        empleadoAModificar.setTelefono(scanner.next());
//                        boolean modificacionExitosa = dao.modificar(empleadoAModificar);
//                        if (modificacionExitosa) {
//                            System.out.println("Empleado modificado exitosamente");
//                        } 
//                    } else {
//                        System.out.println("Empleado no encontrado para modificar");
//                    }
//                    break;
//                case 3:
//                    System.out.print("ID del empleado a eliminar: ");
//                    int idEliminar = scanner.nextInt();
//                    PojoEmpleado empleadoAEliminar = dao.buscarById(idEliminar);
//                    if (empleadoAEliminar != null) {
//                        boolean eliminacionExitosa = dao.eliminar(empleadoAEliminar);
//                        if (eliminacionExitosa) {
//                            System.out.println("Empleado eliminado exitosamente");
//                        } 
//                    } else {
//                        System.out.println("Empleado no encontrado para eliminar");
//                    }
//                    break;
//                case 4:
//                    System.out.print("ID del empleado a buscar: ");
//                    int idBuscar = scanner.nextInt();
//                    PojoEmpleado empleadoBuscado = dao.buscarById(idBuscar);
//                    if (empleadoBuscado != null) {
//                        System.out.println("Empleado encontrado: " + empleadoBuscado.getNombre() + ", Dirección: " + empleadoBuscado.getDireccion() + ", Teléfono: " + empleadoBuscado.getTelefono());
//                    } else {
//                        System.out.println("Empleado no encontrado");
//                    }
//                    break;
//                case 5:
//                    List<PojoEmpleado> todosLosEmpleados = dao.buscarAll();
//                    if (!todosLosEmpleados.isEmpty()) {
//                        System.out.println("Todos los empleados:");
//                        for (PojoEmpleado emp : todosLosEmpleados) {
//                            System.out.println("ID: " + emp.getId() + ", Nombre: " + emp.getNombre() + ", Dirección: " + emp.getDireccion() + ", Teléfono: " + emp.getTelefono());
//                        }
//                    } else {
//                        System.out.println("No hay empleados en la base de datos");
//                    }
//                    break;
//                case 6:
//                    salir = true;
//                    break;
//                default:
//                    System.out.println("Opción inválida");
//                    break;
//            }
//        }
//        scanner.close();
//        DAOEmpleado dao = new DAOEmpleado();
//
//        PojoEmpleado empleado = new PojoEmpleado();
//        empleado.setNombre("prueb");
//        empleado.setDireccion("av.42");
//        empleado.setTelefono("2727293899");
//        boolean res = dao.guardar(empleado);
//
//        PojoEmpleado empleadoAModificar = dao.buscarById(1);
//        if (empleadoAModificar != null) {
//            empleadoAModificar.setNombre("berna");
//            empleadoAModificar.setDireccion("av.1");
//            boolean modificacionExitosa = dao.modificar(empleadoAModificar);
//            if (modificacionExitosa) {
//                System.out.println("Empleado modificado exitosamente");
//            } else {
//                
//            }
//        } else {
//            System.out.println("Empleado no encontrado para modificar");
//        }
//
//        PojoEmpleado empleadoAEliminar = dao.buscarById(4);
//        if (empleadoAEliminar != null) {
//            boolean eliminacionExitosa = dao.eliminar(empleadoAEliminar);
//            if (eliminacionExitosa) {
//                System.out.println("Empleado eliminado exitosamente");
//            } else {
//              
//            }
//        } else {
//            System.out.println("Empleado no encontrado para eliminar");
//        }
//        
//        PojoEmpleado empleadoBuscado = dao.buscarById(2);
//        if (empleadoBuscado != null) {
//            System.out.println("Empleado encontrado: " + empleadoBuscado.getNombre()+ ", Dirección: " + empleadoBuscado.getDireccion() + ", Teléfono: " + empleadoBuscado.getTelefono());
//        } else {
//            System.out.println("Empleado no encontrado");
//        }
//        List<PojoEmpleado> todosLosEmpleados = dao.buscarAll();
//        if (!todosLosEmpleados.isEmpty()) {
//            System.out.println("Todos los empleados:");
//            for (PojoEmpleado emp : todosLosEmpleados) {
//                System.out.println("ID: " + emp.getId() + ", Nombre: " + emp.getNombre() + ", Dirección: " + emp.getDireccion() + ", Teléfono: " + emp.getTelefono());
//            }
//        } else {
//            System.out.println("No hay empleados en la base de datos");
//        }
//        IMensaje msg = new IMensaje(){
//            @Override
//            public void imprimir() {
//                System.out.println("otro mensaje");
//            }
//        };
//        ControllerMensaje.MostrarMensaje(msg);
//        
//        IMensaje msg = new Saludo();
//        IMensaje msg = new Despedida();
//        ControllerMensaje.MostrarMensaje(msg);
    }
}
