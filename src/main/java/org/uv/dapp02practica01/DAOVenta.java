/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Berna
 */
public class DAOVenta implements IDAO<Venta> {

//    @Override
//    public boolean guardar(Venta p) {
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session session = sf.getCurrentSession();
//
//        Transaction tran = session.beginTransaction();
//        session.save(p);
//
//        for (DetalleVenta det : p.getDetalleVenta()) {
//            session.save(det);
//        }
//        tran.commit();
//
//        System.out.println("Se guardo con el ID: " + p.getId());
//        return true;
//    }
    @Override
    public boolean guardar(Venta p) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tran = null;

        try {
            tran = session.beginTransaction();
            session.save(p);

            for (DetalleVenta det : p.getDetalleVenta()) {
                session.save(det);
            }

            tran.commit();
            System.out.println("Se guardó con el ID: " + p.getId());
            return true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            System.out.println("Error al guardar la venta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Venta p) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            session.update(p);
            tran.commit();
            return true;
        
    }

    @Override
    public boolean eliminar(Venta p) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            session.delete(p);
            tran.commit();
            return true;
        
    }

    @Override
    public Venta buscarById(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            Venta venta = session.get(Venta.class, id);
            tran.commit();
            return venta;
        
    }

    @Override
    public List<Venta> buscarAll() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();

        Transaction tran = session.beginTransaction();
        List<Venta> ventas = session.createQuery("from Venta").list();
        tran.commit();

        return ventas;
    }

}
