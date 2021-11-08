/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.DetalleCompra;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juan
 */
@Stateless
public class DetalleCompraFacade extends AbstractFacade<DetalleCompra> implements DetalleCompraFacadeLocal {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraFacade() {
        super(DetalleCompra.class);
    }
    
    @Override
    public void registrarDetalleCompra( int fkCompra, int cantidadSolicitada, Date fechaRecibido, int fkProducto, float costoTotal){
        try {
            Query qr = em.createNativeQuery("INSERT INTO detalle_compra (numero_compra, cantidad_solicitada, fecha_recibido, producto_id_producto, costo_total) VALUES ( ?, ?, ?, ?, ?)");
            qr.setParameter(1, fkCompra);
            qr.setParameter(2, cantidadSolicitada);
            qr.setParameter(3, fechaRecibido);
            qr.setParameter(4, fkProducto);
            qr.setParameter(5, costoTotal);
            qr.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
