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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByNumeroPedido", query = "SELECT p FROM Pedido p WHERE p.numeroPedido = :numeroPedido"),
    @NamedQuery(name = "Pedido.findByFechaRecibida", query = "SELECT p FROM Pedido p WHERE p.fechaRecibida = :fechaRecibida")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_pedido")
    private Integer numeroPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_recibida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecibida;
    @JoinColumn(name = "detalle_pedido_numero_detalle", referencedColumnName = "numero_detalle")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetallePedido detallePedidoNumeroDetalle;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioIdUsuario;

    public Pedido() {
    }

    public Pedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Pedido(Integer numeroPedido, Date fechaRecibida) {
        this.numeroPedido = numeroPedido;
        this.fechaRecibida = fechaRecibida;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public DetallePedido getDetallePedidoNumeroDetalle() {
        return detallePedidoNumeroDetalle;
    }

    public void setDetallePedidoNumeroDetalle(DetallePedido detallePedidoNumeroDetalle) {
        this.detallePedidoNumeroDetalle = detallePedidoNumeroDetalle;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroPedido != null ? numeroPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.numeroPedido == null && other.numeroPedido != null) || (this.numeroPedido != null && !this.numeroPedido.equals(other.numeroPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" +fechaRecibida;
    }
    
}
