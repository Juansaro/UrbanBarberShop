/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.Cita;
import com.barber.model.TipoRol;
import com.barber.model.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

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
    public List<Cita> generarFactura(int idCitaIn) {
        try {
            Query q = em.createNativeQuery("{CALL GENERAR_FACTURA(?);}");
            q.setParameter(1, idCitaIn);
            //Object no se sabe que devuelve
            //Object obj = q.getSingleResult();

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean validarFechaCita(Date CitaIn) {
      
            StoredProcedureQuery q = em.createStoredProcedureQuery("VALIDAR_CITA")
                    .registerStoredProcedureParameter(1, Date.class,ParameterMode.IN)
                    .registerStoredProcedureParameter(2, boolean.class,ParameterMode.OUT)
                    .setParameter(1, CitaIn);
                    
            q.execute();
            //Out (2)
            boolean commentCount =  (boolean) q.getOutputParameterValue(2);
           
            return commentCount;
        
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
            //c.setParameter(3, citaIn.getUsuarioIdUsuario());
            c.setParameter(4, citaIn.getIdCita());

            c.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Cita> leerTodos(Usuario usu_cita) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Cita c WHERE c.idCliente = :usu_cita");
            qt.setParameter("usu_cita", usu_cita);
            return qt.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Usuario> leerBarberos(TipoRol r) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.tipoRolNumeroRol = :r");
            qt.setParameter("r", r);
            return qt.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    /*
    public List<Servicio> leerServiciosAgendados(Cita c){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT s FROM Servicio s");
            qt.setParameter(name, c);
            return qt.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    */
}
