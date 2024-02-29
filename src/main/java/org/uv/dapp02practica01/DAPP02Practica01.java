/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        SessionFactory sf = HibernateUtil.getSessionFactory();

        PojoEmpleado empleado = new PojoEmpleado();
        empleado.setNombre("BRM");
        empleado.setDireccion("av.556");
        empleado.setTelefono("223344");

        Session session = sf.getCurrentSession();
//        Transaction tran = session.beginTransaction();
//        session.save(empleado);
//        tran.commit();
//        System.out.println("Se guardo con el ID: " + empleado.getId());

        Transaction tran = session.beginTransaction();
        PojoEmpleado emp1 = session.get(PojoEmpleado.class, 3);
//        PojoEmpleado emp2 = session.load(PojoEmpleado.class, 1);
        tran.commit();
        if (emp1 != null) {
            System.out.println("Nombre: " + emp1.getNombre());
        } else {
            System.out.println("No se encontro");
        }

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
