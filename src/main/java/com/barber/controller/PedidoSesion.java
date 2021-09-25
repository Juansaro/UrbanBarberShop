/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.DetallePedidoFacadeLocal;
import com.barber.EJB.PedidoFacadeLocal;
import com.barber.EJB.UsuarioFacadeLocal;
import com.barber.model.DetallePedido;
import com.barber.model.Pedido;
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
@Named(value = "pedidoSesion")
@SessionScoped
public class PedidoSesion implements Serializable{
    
    @EJB
    private PedidoFacadeLocal pedidoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private DetallePedidoFacadeLocal detallePedidoFacade;
    
    private Pedido pedido;
    
    @Inject
    private Usuario usuario;
    @Inject
    private DetallePedido detallePedido;
    
    private List<Pedido> pedidos;
    private List<Usuario> usuarios;
    private List<DetallePedido> detallesPedidos;
    
    private Pedido ped = new Pedido();
    
    @PostConstruct
    public void init(){
        pedidos = pedidoFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
        detallesPedidos = detallePedidoFacade.findAll();
        pedido = new Pedido();
    }
    
    //Registrar pedido
    public String registrarPedido(){
        try {
            this.ped.setUsuarioIdUsuario(usuario);
            this.ped.setDetallePedidoNumeroDetalle(detallePedido);
            pedidoFacadeLocal.create(ped);
            pedidos = pedidoFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido registrado", "Pedido registrado"));
            return "/RecepPedidoRecibidoConsultar.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de registro", "Error de registro"));
        }
        return null;
    }
    
    //Preparar página para 
    public String prepararEliminar(){
        pedidos = pedidoFacadeLocal.findAll();
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarPedido(Pedido pe){
        try{
            this.pedidoFacadeLocal.remove(pe);
            this.pedido = new Pedido();
            //Colocar prepararEliminar()
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido eliminado", "Pedido eliminado"));
            prepararEliminar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Getters y Setters

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<DetallePedido> getDetallesPedidos() {
        return detallesPedidos;
    }

    public void setDetallesPedidos(List<DetallePedido> detallesPedidos) {
        this.detallesPedidos = detallesPedidos;
    }

    public Pedido getPed() {
        return ped;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }
    
}

