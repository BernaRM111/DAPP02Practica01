/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Berna
 */
public class ConexionDB {
    private static ConexionDB cx = null;
    private Connection con = null;

    public static ConexionDB getInstance() {
        if (cx == null) {
            cx = new ConexionDB();
        }
        return cx;
    }

    private ConexionDB() {
        try {
            String url = "jdbc:postgresql://localhost:5432/ejemplo";
            String pwd = "12345";
            String usr = "postgres";

            con = DriverManager.getConnection(url, usr, pwd);
            Logger.getLogger(DAPP02Practica01.class.getName()).log(Level.SEVERE, "Se conecto");
        } catch (SQLException ex) {
            Logger.getLogger(DAPP02Practica01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean execute(String sql){
        try{
            Statement st = con.createStatement();
            return st.execute(sql);
        }catch (SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean execute(TransactionDB t){
        return t.execute(con);
    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAPP02Practica01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}