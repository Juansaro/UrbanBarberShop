/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CalificacionFacadeLocal;
import com.barber.EJB.CitaFacadeLocal;
import com.barber.EJB.FacturaFacadeLocal;
import com.barber.model.Calificacion;
import com.barber.model.Cita;
import com.barber.model.Factura;
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
@Named(value = "calificacionSesion")
@SessionScoped
public class CalificacionSesion implements Serializable{
    
    @EJB
    private CalificacionFacadeLocal calificacionFacadeLocal;
    @EJB
    private CitaFacadeLocal citaFacadeLocal;
    
    private Calificacion calificacion;
    
    @Inject
    private Cita cita;
    
    private List<Calificacion> calificaciones;
    private List<Cita> citas;
    
    private Calificacion cal = new Calificacion();
    private Calificacion calTemporal = new Calificacion();
    
    @PostConstruct
    private void init(){
        calificaciones = calificacionFacadeLocal.leerTdos();
        citas = citaFacadeLocal.leerCitas();
        calificacion = new Calificacion();
    }
    
    //Registrar
    public String registrarCalificacion(){
        try {
            cal.setCitaTerminada(cita);
            calificacionFacadeLocal.create(cal);
            calificaciones = calificacionFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Calificación registrada", "Calificación registrada"));
            return "/ClienteFidelizacionConsultar.xhtml";
        } catch (Exception e) {
        }
        return null;
    }
    
    //Guardar temporal
    public String guardarTemporal(Calificacion c){
        calTemporal = c;
        return "ClienteFidelizacionModificar.xhtml";
    }
    
    //Editar calificación
    public String editarCalificacion(){
        try {
            calificacionFacadeLocal.edit(calTemporal);
            this.calificacion = new Calificacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Calificación editada", "Calificación editada"));
            return "/ClienteFidelizacionConsultar.xhtml";
        } catch (Exception e) {
        }
        return null;
    }
    
    //preparar eliminar
    public String prepararEliminar(){
        calificaciones = calificacionFacadeLocal.findAll();
        return "/ClienteFidelizacionConsultar.xhtml";
    }
    
    //eliminar
    public void eliminarCalificacion(Calificacion c){
        try {
            calificacionFacadeLocal.remove(c);
            calificacion = new Calificacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó la calificación", "Se eliminó la calificación"));
            prepararEliminar();
        } catch (Exception e) {
        }
    }    

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Calificacion getCal() {
        return cal;
    }

    public void setCal(Calificacion cal) {
        this.cal = cal;
    }

    public Calificacion getCalTemporal() {
        return calTemporal;
    }

    public void setCalTemporal(Calificacion calTemporal) {
        this.calTemporal = calTemporal;
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
   
}

