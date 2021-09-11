/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.DetallePedidoFacadeLocal;
import com.barber.EJB.ProductoFacadeLocal;
import com.barber.EJB.ProveedorFacadeLocal;
import com.barber.model.DetallePedido;
import com.barber.model.Producto;
import com.barber.model.Proveedor;
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
@Named(value = "detallePedidoSesion")
@SessionScoped
public class DetallePedidoSesion implements Serializable{
    
    @EJB
    private DetallePedidoFacadeLocal detallePedidoFacadeLocal;
    @EJB
    private ProductoFacadeLocal productoFacadeLocal;
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    
    private DetallePedido detallePedido;
    
    @Inject
    private Producto producto;
    @Inject
    private Proveedor proveedor;
    
    private List<DetallePedido> detallePedidos;
    private List<Producto> productos;
    private List<Proveedor> proveedores;
    
    private DetallePedido det = new DetallePedido();
    
    @PostConstruct
    public void init(){
        detallePedidos = detallePedidoFacadeLocal.findAll();
        productos = productoFacadeLocal.findAll();
        proveedores = proveedorFacadeLocal.findAll();
        detallePedido = new DetallePedido();
    }
    
    //Registro
    
    public void registrarDetallePedido(){
        try {
            this.det.setProductoIdProducto(producto);
            //this.det.setProveedorNumeroProveedor(proveedor);
            detallePedidoFacadeLocal.create(det);
            detallePedidos = detallePedidoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/recibirPedido.xhtml");           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido registrado", "Pedido registrado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de registro", "Error de registro"));
        }
    }
    
    //Preparar página para 
    public String prepararEliminar(){
        detallePedidos = detallePedidoFacadeLocal.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Orden de pedido eliminado", "Orden de pedido eliminado"));
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminardetallePedido(DetallePedido d){
        try{
            this.detallePedidoFacadeLocal.remove(d);
            this.detallePedido = new DetallePedido();
            //Colocar prepararEliminar()
            
            prepararEliminar();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar", "Error al eliminar"));
        }
    }
    
    //Getters y Setters

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public DetallePedido getDet() {
        return det;
    }

    public void setDet(DetallePedido det) {
        this.det = det;
    }
    
}

