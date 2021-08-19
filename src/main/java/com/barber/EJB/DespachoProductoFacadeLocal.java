/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.DespachoProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author juan
 */
@Local
public interface DespachoProductoFacadeLocal {

    void create(DespachoProducto despachoProducto);

    void edit(DespachoProducto despachoProducto);

    void remove(DespachoProducto despachoProducto);

    DespachoProducto find(Object id);

    List<DespachoProducto> findAll();

    List<DespachoProducto> findRange(int[] range);

    int count();
    
}
