/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CitaFacadeLocal;
import com.barber.EJB.EstadoAsignacionFacadeLocal;
import com.barber.EJB.ServicioFacadeLocal;
import com.barber.EJB.UsuarioFacadeLocal;
import com.barber.model.Cita;
import com.barber.model.EstadoAsignacion;
import com.barber.model.Servicio;
import com.barber.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author juan
 */
@Named(value = "citaSesion")
@SessionScoped
public class CitaSesion implements Serializable{
    
    @EJB
    private CitaFacadeLocal citaFacadeLocal;
    @EJB
    private EstadoAsignacionFacadeLocal estadoAsignacionFacadeLocal;
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    
    private UsuarioSesion usu;
    
    private Cita cita;
    
    @Inject
    private EstadoAsignacion estadoAsignacion;
    @Inject
    private Servicio servicio;
    @Inject
    private Usuario usuario;
    
    private List<Cita> citas;
    private List<EstadoAsignacion> estadoAsignaciones;
    private List<Servicio> servicios;
    private List<Usuario> usuarios;
    
    private Cita cit = new Cita();
    private Cita citTemporal = new Cita();
    
    @PostConstruct 
    public void init(){
        citas = citaFacadeLocal.findAll();
        estadoAsignaciones = estadoAsignacionFacadeLocal.findAll();
        servicios = servicioFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
        cita = new Cita();
    }
    
    //Registrar cita
    //Promesa --->Solo para el cliente<--- **Volver**
    public String registrarCita(){
        try {
            this.cit.setEstadoAsignacionIdEstadoAsignacion(estadoAsignacion);
            this.cit.setServicioIdServicio(servicio);
            this.cit.setUsuarioIdUsuario(usuario);
            citaFacadeLocal.create(cit);
            citas = citaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita registrada", "Cita registrada"));
            return "/ClienteVerEstadoCita.xhtml";
        } catch (Exception e) {
            return null;
        }
    }
    
    //guardar temporal
    public String guardarTemporal(Cita c){
        citTemporal = c;
        return "/RecepAsignarBarbero.xhtml";
    }
    
    //Editar
    public String editarCita(){
        try {
            citaFacadeLocal.edit(citTemporal);
            this.cita = new Cita();
            return "/RecepAsignarAgenda.xhtml";
        } catch (Exception e) {
        }
        return null;
    }
    //***Hacer más métodos aquí***
    //Preparar página para 
    public String prepararEliminar(){
        citas = citaFacadeLocal.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita eliminada", "Cita eliminada"));
        return "/ClienteVerEstadoCita.xhtml";
        
    }
    
    //Eliminar
    public void eliminarCita(Cita c){
        try{
            this.citaFacadeLocal.remove(c);
            this.cita = new Cita();
            prepararEliminar();
        }catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de eliminación", "Error de eliminación"));
        }
        
    }
    

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Cita getCit() {
        return cit;
    }

    public void setCit(Cita cit) {
        this.cit = cit;
    }

    public CitaFacadeLocal getCitaFacadeLocal() {
        return citaFacadeLocal;
    }

    public void setCitaFacadeLocal(CitaFacadeLocal citaFacadeLocal) {
        this.citaFacadeLocal = citaFacadeLocal;
    }

    public EstadoAsignacion getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(EstadoAsignacion estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EstadoAsignacion> getEstadoAsignaciones() {
        return estadoAsignaciones;
    }

    public void setEstadoAsignaciones(List<EstadoAsignacion> estadoAsignaciones) {
        this.estadoAsignaciones = estadoAsignaciones;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Cita getCitTemporal() {
        return citTemporal;
    }

    public void setCitTemporal(Cita citTemporal) {
        this.citTemporal = citTemporal;
    }
    
}

