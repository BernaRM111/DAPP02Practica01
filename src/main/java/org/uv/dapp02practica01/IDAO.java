/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.dapp02practica01;

import java.util.List;

/**
 *
 * @author Berna
 */
public interface IDAO <T>{
    public boolean guardar(T p);
    public boolean modificar(T p);
    public boolean eliminar(T p);
    public T buscarById(int id);
    public List<T> buscarAll();
}
