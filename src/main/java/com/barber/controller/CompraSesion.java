
package com.barber.controller;

import com.barber.EJB.CompraFacadeLocal;
import com.barber.EJB.ProveedorFacadeLocal;
import com.barber.model.Compra;
import com.barber.model.Proveedor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "compraSesion")
@SessionScoped
public class CompraSesion implements Serializable{
    
    @EJB
    private CompraFacadeLocal compraFacadeLocal;
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    
    private Compra compra;
    @Inject
    private Proveedor proveedor;
    
    private List<Compra> compras;
    private List<Proveedor> proveedores;
    
    private Compra com = new Compra();
    private Compra comTemporal = new Compra();
    
    @PostConstruct
    public void init(){
        compras = compraFacadeLocal.findAll();
        proveedores = proveedorFacadeLocal.findAll();
    }

    public void registrarCompra(){
        try {
            this.com.setNumeroProveedor(proveedor);
            compraFacadeLocal.create(com);
            compras = compraFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }
    
    public void guardarTemporal(Compra c){
        comTemporal = c;
    }
    
    public void editarCompra(){
        try {
            this.com.setNumeroProveedor(proveedor);
            compraFacadeLocal.edit(comTemporal);
            compras = compraFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }
    
    public void eliminarCompra(Compra c){
        try {
            compraFacadeLocal.remove(c);
            compras = compraFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }
    
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public Compra getCom() {
        return com;
    }

    public void setCom(Compra com) {
        this.com = com;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Compra getComTemporal() {
        return comTemporal;
    }

    public void setComTemporal(Compra comTemporal) {
        this.comTemporal = comTemporal;
    }
    
}
