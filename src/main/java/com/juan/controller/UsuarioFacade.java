/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.controller;

import com.juan.model.Usuario;
import static com.juan.model.Usuario_.idUsuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario encontrarUsuarioCorreo(String correo){
        Query q = em.createNamedQuery("Usuario.findByCorreo", Usuario.class).setParameter("correo", correo);
        
        List<Usuario> listado = q.getResultList();
        
        if(!listado.isEmpty()){
            return listado.get(0);
        }
        return null;
    }
    
    public Usuario encontrarRol(int numeroRol){
        Query q = em.createNamedQuery("Usuario.findByTipoRolNumeroRol", Usuario.class).setParameter("tipoRolNumeroRol", numeroRol);
        
        List<Usuario> listado = q.getResultList();
        
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        return null;
    }
    
    public Usuario encontrarIdUsuario(int idUsuario){
        Query q = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("idUsuario", idUsuario);
        
        List<Usuario> listado = q.getResultList();
        
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        return null;
    }
    
}
