/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.BodegaFacadeLocal;
import com.barber.EJB.ProductoFacadeLocal;
import com.barber.EJB.ProveedorFacadeLocal;
import com.barber.model.Bodega;
import com.barber.model.Producto;
import com.barber.model.Proveedor;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author juan
 */
@Named(value = "productoView")
@ViewScoped
public class ProductoView implements Serializable{
    
    @EJB
    private ProductoFacadeLocal productoFacadeLocal;
    @EJB
    private BodegaFacadeLocal bodegaFacadeLocal;
    
    private Producto producto;
    
    @Inject
    private Bodega bodega;
    
    private List<Producto> productos;
    private List<Bodega> bodegas;
    
    private Producto pro = new Producto();
    private Producto proTemporal = new Producto();
    private Bodega bodTemporal = new Bodega();
    
    @PostConstruct
    private void init(){
        productos = productoFacadeLocal.leerTodos();
        bodegas = bodegaFacadeLocal.leerTodos();
        producto = new Producto();
    }
    
    //Registrar producto
    public void registrarProducto(){
        try {
            pro.setBodegaIdBodega(bodega);
            productoFacadeLocal.create(pro);
            productos = productoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto registrado", "Producto registrado"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarProducto.xhtml");
        } catch (IOException e) {
        }
    }
    
    //Recupera datos del producto al cual se va a editar
     public void guardarTemporal(Producto p) {
        proTemporal = p;
    }

    //Editar usuario (En el modal)
    public void editarProducto() {
        try {
            this.proTemporal.setBodegaIdBodega(bodega);
            productoFacadeLocal.edit(proTemporal);
            productos = productoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto editado", "Producto editado"));
        } catch (Exception e) {
            
        }
        //return null;
    }
    
    //Eliminar
    public void eliminarProducto(Producto p){
        try{
            this.productoFacadeLocal.remove(p);
            this.producto = new Producto();
            productos = productoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado", "Producto eliminado"));
        }catch(Exception e){
        }
    }
    
    //Getters y Setters
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public Producto getProTemporal() {
        return proTemporal;
    }

    public void setProTemporal(Producto proTemporal) {
        this.proTemporal = proTemporal;
    }

    public Bodega getBodTemporal() {
        return bodTemporal;
    }

    public void setBodTemporal(Bodega bodTemporal) {
        this.bodTemporal = bodTemporal;
    }
    
}

