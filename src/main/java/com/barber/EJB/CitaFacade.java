/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.Cita;
import com.barber.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juan
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> implements CitaFacadeLocal {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
    
    @Override
    public Object generarFactura(int idCitaIn) {
        try {
            Query q = em.createNativeQuery("{CALL GENERAR_FACTURA(?);}");
            q.setParameter(1, idCitaIn);
            //Object no se sabe que devuelve
            Object obj = q.getSingleResult();

            return obj;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public boolean crearCita(Cita citaIn, int fk_servicio, int estado_asignacion_id_estado_asignacion, Usuario usuIn) {
        try {
            Query c = em.createNativeQuery(
                    "INSERT INTO cita "
                    + "(fecha_cita, servicio_id_servicio, estado_asignacion_id_estado_asignacion, usuario_id_usuario) "
                    + "VALUES (?, ?, ?, ?);"
            );
            c.setParameter(1, citaIn.getFechaCita());
            c.setParameter(2, citaIn.getEstadoAsignacionIdEstadoAsignacion());
            c.setParameter(3, citaIn.getUsuarioIdUsuario());
            c.setParameter(4, citaIn.getIdCita());
            
            c.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
