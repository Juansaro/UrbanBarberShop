/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CalificacionFacadeLocal;
import com.barber.EJB.FacturaFacadeLocal;
import com.barber.model.Calificacion;
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
    private FacturaFacadeLocal facturaFacadeLocal;
    
    private Calificacion calificacion;
    
    @Inject
    private Factura factura;
    
    private List<Calificacion> calificaciones;
    private List<Factura> facturas;
    
    private Calificacion cal = new Calificacion();
    private Calificacion calTemporal = new Calificacion();
    
    @PostConstruct
    private void init(){
        calificaciones = calificacionFacadeLocal.findAll();
        facturas = facturaFacadeLocal.findAll();
        calificacion = new Calificacion();
    }
    
    //Registrar
    public String registrarCalificacion(){
        try {
            cal.setFacturaIdFactura(factura);
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
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
    
   
}

