/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.controller;

import com.juan.model.Calificacion;
import static com.juan.model.Calificacion_.idCalificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juan
 */
@Stateless
public class CalificacionFacade extends AbstractFacade<Calificacion> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionFacade() {
        super(Calificacion.class);
    }
    
    public Calificacion encontrarCalificacion(int idCalificacion){
        
        Query q = em.createNamedQuery("Calificacion.findByIdCalificacion", Calificacion.class).setParameter("idCalificacion", idCalificacion);
        
        List<Calificacion> listado = q.getResultList();
        
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        return null;
    
    }
    
}
