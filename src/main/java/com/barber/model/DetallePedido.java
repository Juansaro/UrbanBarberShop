/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "detalle_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePedido.findAll", query = "SELECT d FROM DetallePedido d"),
    @NamedQuery(name = "DetallePedido.findByNumeroDetalle", query = "SELECT d FROM DetallePedido d WHERE d.numeroDetalle = :numeroDetalle"),
    @NamedQuery(name = "DetallePedido.findByCantidadSolicitada", query = "SELECT d FROM DetallePedido d WHERE d.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "DetallePedido.findByFechaSolicitud", query = "SELECT d FROM DetallePedido d WHERE d.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "DetallePedido.findByCostoTotal", query = "SELECT d FROM DetallePedido d WHERE d.costoTotal = :costoTotal")})
public class DetallePedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_detalle")
    private Integer numeroDetalle;
    @Column(name = "cantidad_solicitada")
    private Integer cantidadSolicitada;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_total")
    private Float costoTotal;
    @JoinColumn(name = "producto_id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto productoIdProducto;
    @JoinColumn(name = "proveedor_numero_proveedor", referencedColumnName = "numero_proveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedorNumeroProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detallePedidoNumeroDetalle")
    private List<Pedido> pedidoList;

    public DetallePedido() {
    }

    public DetallePedido(Integer numeroDetalle) {
        this.numeroDetalle = numeroDetalle;
    }

    public Integer getNumeroDetalle() {
        return numeroDetalle;
    }

    public void setNumeroDetalle(Integer numeroDetalle) {
        this.numeroDetalle = numeroDetalle;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Producto getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(Producto productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    public Proveedor getProveedorNumeroProveedor() {
        return proveedorNumeroProveedor;
    }

    public void setProveedorNumeroProveedor(Proveedor proveedorNumeroProveedor) {
        this.proveedorNumeroProveedor = proveedorNumeroProveedor;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDetalle != null ? numeroDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePedido)) {
            return false;
        }
        DetallePedido other = (DetallePedido) object;
        if ((this.numeroDetalle == null && other.numeroDetalle != null) || (this.numeroDetalle != null && !this.numeroDetalle.equals(other.numeroDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+fechaSolicitud;
    }
    
}
