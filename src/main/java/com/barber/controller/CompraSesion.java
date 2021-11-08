
package com.barber.controller;

import com.barber.EJB.CompraFacadeLocal;
import com.barber.EJB.DetalleCompraFacadeLocal;
import com.barber.EJB.ProductoFacadeLocal;
import com.barber.EJB.ProveedorFacadeLocal;
import com.barber.model.Compra;
import com.barber.model.DetalleCompra;
import com.barber.model.Producto;
import com.barber.model.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;
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
    @EJB
    private DetalleCompraFacadeLocal detalleCompraFacadeLocal;
    @EJB
    private ProductoFacadeLocal ProductoFacadeLocal;
    
    private Compra compra;
    @Inject
    private Proveedor proveedor;
    @Inject
    private DetalleCompra detalleIn;
    @Inject
    private Producto productoIn;
    
    private List<Compra> compras;
    private List<Proveedor> proveedores;
    private List<DetalleCompra> detalles;
    private List<DetalleCompra> detallesTotales;
    private List<Producto> productos;
    
    private Compra com = new Compra();
    private Compra comTemporal = new Compra();
    
    private float acumuladorCostoTotal = 0;
    
    @PostConstruct
    public void init(){
        detalles = new ArrayList<>();
        compras = compraFacadeLocal.findAll();
        proveedores = proveedorFacadeLocal.findAll();
        productos = ProductoFacadeLocal.findAll();
    }
    
    public void guardarDetallesTemporales(DetalleCompra detIn){
        //Parseo de Integer a float para poder hacer el calculo de cantidades * costo del producto
        Integer parse = detIn.getCantidadSolicitada();
        float cantidadParseada = parse.floatValue();
        //Cálculo
        acumuladorCostoTotal = acumuladorCostoTotal + cantidadParseada * detIn.getProductoIdProducto().getPrecio();
        detIn.setCostoTotal(acumuladorCostoTotal);
        //Agregación
        detalles.add(detIn);
    }
    
    public void eliminarTemporal(DetalleCompra detIn){
        //Cálculo
        acumuladorCostoTotal = acumuladorCostoTotal - detIn.getCostoTotal();
        detIn.setCostoTotal(acumuladorCostoTotal);
        //Agregación
        detalles.remove(detIn);
    }

    public void registrarCompra(){
        try {
            int contador = 0;
            for (DetalleCompra it: detalles){
                detalleIn = detalles.get(contador);
                detalleCompraFacadeLocal.registrarDetalleCompra(detalleIn.getNumeroCompra().getNumeroCompra(), detalleIn.getCantidadSolicitada(), detalleIn.getFechaRecibido(), detalleIn.getProductoIdProducto().getIdProducto(), detalleIn.getCostoTotal()
                );
            }
            compraFacadeLocal.registrarCompra(com.getFechaSolicitud(), proveedor.getNumeroProveedor());
            
            acumuladorCostoTotal = 0;
            detalles = new ArrayList<>();
            detalleIn = new DetalleCompra();
            detallesTotales = detalleCompraFacadeLocal.findAll();
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

    public DetalleCompra getDetalleIn() {
        return detalleIn;
    }

    public void setDetalleIn(DetalleCompra detalleIn) {
        this.detalleIn = detalleIn;
    }

    public Producto getProductoIn() {
        return productoIn;
    }

    public void setProductoIn(Producto productoIn) {
        this.productoIn = productoIn;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

    public List<DetalleCompra> getDetallesTotales() {
        return detallesTotales;
    }

    public void setDetallesTotales(List<DetalleCompra> detallesTotales) {
        this.detallesTotales = detallesTotales;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public float getAcumuladorCostoTotal() {
        return acumuladorCostoTotal;
    }

    public void setAcumuladorCostoTotal(float acumuladorCostoTotal) {
        this.acumuladorCostoTotal = acumuladorCostoTotal;
    }
    
}
