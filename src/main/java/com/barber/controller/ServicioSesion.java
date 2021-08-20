/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.ServicioFacadeLocal;
import com.barber.model.Servicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named(value = "servicioSesion")
@SessionScoped
public class ServicioSesion implements Serializable{
    //Conexión de FacadeLocal's
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;
    
    private Servicio servicio;
    private List<Servicio> servicios;
    
    private Servicio ser = new Servicio();
    private Servicio serTemporal = new Servicio();
    
    @PostConstruct
    public void init(){
        servicios = servicioFacadeLocal.findAll();
        servicio = new Servicio();
    }
    
    //Registrar servicio
    public String registrarServicio(){
        try {
            servicioFacadeLocal.create(ser);
            servicios = servicioFacadeLocal.findAll();
            return "/RecepServicioConsultarEliminar";
        } catch (Exception e) {
        }
        return null;
    }
   
    //Recupera datos del servico al cual se va a editar
     public String guardarTemporal(Servicio s) {
        serTemporal = s;
        return "/RecepServicioModificar.xhtml";
    }
     
    //Editar
    public String editarServicio(Servicio s){
        try{
            servicioFacadeLocal.edit(serTemporal);
            this.servicio = new Servicio();
            return "/RecepServicioConsultarEliminar.xhtml";
        }catch(Exception e){
           
        }
        return null;
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        servicios = servicioFacadeLocal.findAll();
        return "/RecepConsultarUsuarios.xhtml";
    }
    
    //Eliminar
    public void eliminarServicio(Servicio s){
        try{
            this.servicioFacadeLocal.remove(s);
            this.servicio = new Servicio();
            //Colocar prepararEliminar()
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio eliminado", "Servicio eliminado"));
            prepararEliminar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Recupera datos del servicio al cual se va a editar
     public void prepararEditar(Servicio serIn) {
        serTemporal = serIn;
    }

    //Editar servicio (En el modal)
    public String editarServicio() {
        try {
            servicioFacadeLocal.edit(serTemporal);
            this.servicio = new Servicio();
            return "/RecepServicioConsultarEliminar.xhtml";
        } catch (Exception e) {
            
        }
        return null;
    }
    
    //Getters y Setters
    
    public Servicio getSer() {
        return ser;
    }

    public void setSer(Servicio ser) {
        this.ser = ser;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Servicio getSerTemporal() {
        return serTemporal;
    }

    public void setSerTemporal(Servicio serTemporal) {
        this.serTemporal = serTemporal;
    }
    
}

