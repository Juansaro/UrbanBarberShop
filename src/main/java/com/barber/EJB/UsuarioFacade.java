/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario validarUsuario(String correoIn, String claveIn) {
        try {
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn AND u.correo = :claveIn");
            qt.setParameter("correoIn", correoIn);
            qt.setParameter("claveIn", claveIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Usuario recuperarClave(String correoIn) {
        try {
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo");
            qt.setParameter("correo", correoIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Usuario> leerTodos() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u");
            return qt.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario encontrarUsuarioCorreo(String correo){
        Query q = em.createNamedQuery("Usuario.findByCorreo", Usuario.class).setParameter("correo", correo);
        
        List<Usuario> listado = q.getResultList();
        
        if(!listado.isEmpty()){
            return listado.get(0);
        }
        return null;
    }
    
    
    @Override
    public Usuario listarBarbero(){
        Query q = em.createStoredProcedureQuery("{CALL ENCONTRAR_ROL_BARBERO()}");
        
        List<Usuario> listado = q.getResultList();
        
        if(!listado.isEmpty()){
            return listado.get(0);
        }
        return null;
    }
    
    @Override
    public Usuario validarSiExiste(String correoIn){
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.correo  LIKE CONCAT('%',:correoIn,'%')");
            q.setParameter("correoIn", correoIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return  null;
        }
    }
    
    @Override
    public boolean crearUsuario(Usuario usuIn) {
        try {
            Query q = em.createNativeQuery("INSERT INTO usuario ( nombre, apellido, contrasena, correo, ciudad_numero_ciudad, tipo_rol_numero_rol, tipo_identificacion_id_tipo_identificacion, numero_documento, tipo_telefono_numero_tipo_telefono, numero_telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            q.setParameter(1, usuIn.getNombre());
            q.setParameter(2, usuIn.getApellido());
            q.setParameter(3, usuIn.getCorreo());
            q.setParameter(4, usuIn.getCiudadNumeroCiudad());
            q.setParameter(5, usuIn.getTipoRolNumeroRol());
            q.setParameter(6, usuIn.getTipoIdentificacionIdTipoIdentificacion());
            q.setParameter(7, usuIn.getNumeroDocumento());
            q.setParameter(8, usuIn.getTipoTelefonoNumeroTipoTelefono());
            q.setParameter(9, usuIn.getNumeroDocumento());
            q.setParameter(10, usuIn.getNumeroTelefono());
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
