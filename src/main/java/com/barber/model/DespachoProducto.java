/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "despacho_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DespachoProducto.findAll", query = "SELECT d FROM DespachoProducto d"),
    @NamedQuery(name = "DespachoProducto.findByNumeroDespachoProducto", query = "SELECT d FROM DespachoProducto d WHERE d.numeroDespachoProducto = :numeroDespachoProducto"),
    @NamedQuery(name = "DespachoProducto.findByFechaSolicitud", query = "SELECT d FROM DespachoProducto d WHERE d.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "DespachoProducto.findByCantidadSolicitada", query = "SELECT d FROM DespachoProducto d WHERE d.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "DespachoProducto.findByCostoTotal", query = "SELECT d FROM DespachoProducto d WHERE d.costoTotal = :costoTotal")})
public class DespachoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_despacho_producto")
    private Integer numeroDespachoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_total")
    private float costoTotal;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioIdUsuario;
    @JoinColumn(name = "producto_id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto productoIdProducto;

    public DespachoProducto() {
    }

    public DespachoProducto(Integer numeroDespachoProducto) {
        this.numeroDespachoProducto = numeroDespachoProducto;
    }

    public DespachoProducto(Integer numeroDespachoProducto, Date fechaSolicitud, int cantidadSolicitada, float costoTotal) {
        this.numeroDespachoProducto = numeroDespachoProducto;
        this.fechaSolicitud = fechaSolicitud;
        this.cantidadSolicitada = cantidadSolicitada;
        this.costoTotal = costoTotal;
    }

    public Integer getNumeroDespachoProducto() {
        return numeroDespachoProducto;
    }

    public void setNumeroDespachoProducto(Integer numeroDespachoProducto) {
        this.numeroDespachoProducto = numeroDespachoProducto;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Producto getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(Producto productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDespachoProducto != null ? numeroDespachoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DespachoProducto)) {
            return false;
        }
        DespachoProducto other = (DespachoProducto) object;
        if ((this.numeroDespachoProducto == null && other.numeroDespachoProducto != null) || (this.numeroDespachoProducto != null && !this.numeroDespachoProducto.equals(other.numeroDespachoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" +fechaSolicitud;
    }
    
}
