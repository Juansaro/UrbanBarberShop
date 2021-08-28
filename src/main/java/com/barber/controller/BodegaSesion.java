/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.BodegaFacadeLocal;
import com.barber.model.Bodega;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author juan
 */
@Named(value = "bodegaSesion")
@SessionScoped
public class BodegaSesion implements Serializable{
    
    @EJB
    private BodegaFacadeLocal bodegaFacadeLocal;
    
    private Bodega bodega;
    private List<Bodega> bodegas;
    
    private Bodega bod = new Bodega();
    private Bodega bodTemporal = new Bodega();
    
    @PostConstruct
    public void init(){
        bodegas = bodegaFacadeLocal.findAll();
        bodega = new Bodega();
    }
    
    //Registrar bodega
    public void registrarBodega(){
        try {
            bodegaFacadeLocal.create(bod);
            bodegas = bodegaFacadeLocal.findAll();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarBodega.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega registrada", "Bodega registrada"));
        } catch (Exception e) {
        }
    }
    //Recupera datos del bodega al cual se va a editar
     public String guardarTemporal(Bodega b) {
        bodTemporal = b;
        return "/RecepBodegasModificar.xhtml";
    }

    //Editar bodega (En el modal)
    public String editarBodega() {
        try {
            bodegaFacadeLocal.edit(bodTemporal);
            this.bodega = new Bodega();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega editada", "Bodega editada"));
            return "/RecepBodegaConsultarEliminar.xhtml";
        } catch (Exception e) {
            
        }
        return null;
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        bodegas = bodegaFacadeLocal.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega eliminada", "Bodega eliminada"));
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarBodega(Bodega b){
        try{
            this.bodegaFacadeLocal.remove(b);
            this.bodega = new Bodega();
            //Colocar prepararEliminar()
            prepararEliminar();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Getters y Setters

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public Bodega getBod() {
        return bod;
    }

    public void setBod(Bodega bod) {
        this.bod = bod;
    }

    public Bodega getBodTemporal() {
        return bodTemporal;
    }

    public void setBodTemporal(Bodega bodTemporal) {
        this.bodTemporal = bodTemporal;
    }
    
}
