/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.DespachoProductoFacadeLocal;
import com.barber.EJB.ProductoFacadeLocal;
import com.barber.EJB.UsuarioFacadeLocal;
import com.barber.model.DespachoProducto;
import com.barber.model.Producto;
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
@Named(value = "despachoSesion")
@SessionScoped
public class DespachoSesion implements Serializable{
    
    @EJB
    private DespachoProductoFacadeLocal despachoProductoFacadeLocal;
    @EJB
    private ProductoFacadeLocal productoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    
    private DespachoProducto despachoProducto;
    
    @Inject
    private Producto producto;
    @Inject
    private Usuario usuario;
    
    private List<DespachoProducto> despachoProductos;
    private List<Producto> productos;
    private List<Usuario> usuarios;
    
    private DespachoProducto des = new DespachoProducto();
    private DespachoProducto desTemporal = new DespachoProducto();
    
    @PostConstruct
    public void init(){
        despachoProductos = despachoProductoFacadeLocal.findAll();
        productos = productoFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
        despachoProducto = new DespachoProducto();
    }
    
    //Registrar
    public void registrarDespachoProducto(){
        try {
            des.setProductoIdProducto(producto);
            des.setUsuarioIdUsuario(usuario);
            despachoProductoFacadeLocal.create(des);
            despachoProductos = despachoProductoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarDespacho.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de registro", "Error de registro"));
        }
    }
    
    //Guardar temportal
    public String guardarTemporal(DespachoProducto d){
        desTemporal = d;
        return "/RecepDespachoModificar";
    }
    
    public String editarDespacho(){
        try {
            despachoProductoFacadeLocal.edit(desTemporal);
            this.despachoProducto = new DespachoProducto();
            return "/RecepDespachoConsultar.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de edición", "Error de edición"));
        }
        return null;
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        despachoProductos = despachoProductoFacadeLocal.findAll();
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarDespachoProducto(DespachoProducto d){
        try{
            this.despachoProductoFacadeLocal.remove(d);
            this.despachoProducto = new DespachoProducto();
            //Colocar prepararEliminar()
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor eliminado", "Proveedor eliminado"));
            prepararEliminar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Getters y Setters

    public DespachoProducto getDespachoProducto() {
        return despachoProducto;
    }

    public void setDespachoProducto(DespachoProducto despachoProducto) {
        this.despachoProducto = despachoProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DespachoProducto> getDespachoProductos() {
        return despachoProductos;
    }

    public void setDespachoProductos(List<DespachoProducto> despachoProductos) {
        this.despachoProductos = despachoProductos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public DespachoProducto getDes() {
        return des;
    }

    public void setDes(DespachoProducto des) {
        this.des = des;
    }

    public DespachoProducto getDesTemporal() {
        return desTemporal;
    }

    public void setDesTemporal(DespachoProducto desTemporal) {
        this.desTemporal = desTemporal;
    }
    
}
