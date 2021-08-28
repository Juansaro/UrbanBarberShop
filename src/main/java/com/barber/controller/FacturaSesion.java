/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CitaFacadeLocal;
import com.barber.EJB.FacturaFacadeLocal;
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
@Named(value = "facturaSesion")
@SessionScoped
public class FacturaSesion implements Serializable{
    
    @EJB
    private FacturaFacadeLocal facturaFacadeLocal;
    @EJB
    private CitaFacadeLocal citaFacadeLocal;
    
    private Factura factura;
    @Inject
    private Cita cita;
    
    private List<Factura> facturas;
    private List<Cita> citas;
    
    private Factura fac = new Factura();
    private Factura facTemporal = new Factura();
    
    @PostConstruct
    public void init(){
        citas = citaFacadeLocal.findAll();
        facturas = facturaFacadeLocal.findAll();
        factura = new Factura();
    }
    
    //Registrar Factura
    public String registrarFactura(){
        try {
            fac.setCitaIdCita(cita);
            facturaFacadeLocal.create(fac);
            facturas = facturaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura registrada", "Factura registrada"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarFactura.xhtml");
        } catch (Exception e) {
        }
        return null;
    }
    
    //Guardar temporal
    public String guardarTemporal(Factura f){
        facTemporal = f;
        return "/RecepFacturaModificar.xhtml";
    }
    
    //Editar factura
    public String editarFactura(){
        try {
            this.facTemporal.setCitaIdCita(cita);
            facturaFacadeLocal.edit(facTemporal);
            facTemporal = new Factura();
            cita = new Cita();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura editada", "Factura editada"));
            return "/RecepConsultarFactura.xhtml";
        } catch (Exception e) {
        }
        return null;
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        facturas = facturaFacadeLocal.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura borrada", "Factura borrada"));
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarFactura(Factura f){
        try{
            this.facturaFacadeLocal.remove(f);
            this.factura = new Factura();
            //Colocar prepararEliminar()
            prepararEliminar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Factura getFac() {
        return fac;
    }

    public void setFac(Factura fac) {
        this.fac = fac;
    }

    public Factura getFacTemporal() {
        return facTemporal;
    }

    public void setFacTemporal(Factura facTemporal) {
        this.facTemporal = facTemporal;
    }
    
}

