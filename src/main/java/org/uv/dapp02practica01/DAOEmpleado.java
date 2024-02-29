/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berna
 */
public class DAOEmpleado implements IDAO<PojoEmpleado> {
    @Override
    public boolean guardar(PojoEmpleado p) {
        ConexionDB con = ConexionDB.getInstance();
        TransactionDB t = new TransactionDB<PojoEmpleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into empleadotemporal (nombre, direccion, telefono) values (?,?,?)";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setString(1, pojo.getNombre());
                    pstm.setString(2, pojo.getDireccion());
                    pstm.setString(3, pojo.getTelefono());
                    pstm.execute();
                    return true;

                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }

        };
        con.execute(t);
        return true;
//    @Override
//    public boolean guardar(PojoEmpleado p) {
//        ConexionDB con = ConexionDB.getInstance();
//        String sql = "insert into empleadotemporal (nombre, direccion, telefono)"
//                + "values('" + p.getNombre() + "','" + p.getDireccion() + "'"
//                + ",'" + p.getTelefono() + "')";
//        boolean res = con.execute(sql);
//        return res;
//    }

    }

//    @Override
//    public boolean modificar(PojoEmpleado p) {
//        ConexionDB con = ConexionDB.getInstance();
//        String sql = "update empleadotemporal set nombre='" + p.getNombre() + "', direccion='" + p.getDireccion() + "', telefono='" + p.getTelefono() + "' where id=" + p.getId();
//        boolean res = con.execute(sql);
//        return res;
//    }
    @Override
    public boolean modificar(PojoEmpleado p) {
        ConexionDB con = ConexionDB.getInstance();
        TransactionDB t = new TransactionDB<PojoEmpleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "update empleadotemporal set nombre=?, direccion=?, telefono=? where id=?";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setString(1, pojo.getNombre());
                    pstm.setString(2, pojo.getDireccion());
                    pstm.setString(3, pojo.getTelefono());
                    pstm.setInt(4, pojo.getId());
                    pstm.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        con.execute(t);
        return true;
    }

//    @Override
//    public boolean eliminar(PojoEmpleado p) {
//        ConexionDB con = ConexionDB.getInstance();
//        String sql = "delete from empleadotemporal where id=" + p.getId();
//        boolean res = con.execute(sql);
//        return res;
//    }
    @Override
    public boolean eliminar(PojoEmpleado p) {
        ConexionDB con = ConexionDB.getInstance();
        TransactionDB t = new TransactionDB<PojoEmpleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "delete from empleadotemporal where id=?";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setInt(1, pojo.getId());
                    pstm.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        con.execute(t);
        return true;
    }

    @Override
    public PojoEmpleado buscarById(int id) {
        ConexionDB con = ConexionDB.getInstance();
        String sql = "select * from empleadotemporal where id=" + id;
        ResultSet rs = con.executeQuery(sql);
        PojoEmpleado empleado = null;
        try {
            if (rs.next()) {
                empleado = new PojoEmpleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public List<PojoEmpleado> buscarAll() {
        ConexionDB con = ConexionDB.getInstance();
        String sql = "select * from empleadotemporal";
        ResultSet rs = con.executeQuery(sql);
        List<PojoEmpleado> empleados = new ArrayList<>();
        try {
            while (rs.next()) {
                PojoEmpleado empleado = new PojoEmpleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setTelefono(rs.getString("telefono"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
