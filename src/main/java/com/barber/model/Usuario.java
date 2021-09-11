/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByNumeroDocumento", query = "SELECT u FROM Usuario u WHERE u.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Usuario.findByNumeroTelefono", query = "SELECT u FROM Usuario u WHERE u.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Usuario.findByUsuFoto", query = "SELECT u FROM Usuario u WHERE u.usuFoto = :usuFoto")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "numero_telefono")
    private String numeroTelefono;
    @Size(max = 255)
    @Column(name = "usu_foto")
    private String usuFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario", fetch = FetchType.LAZY)
    private List<DespachoProducto> despachoProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario", fetch = FetchType.LAZY)
    private List<Pedido> pedidoList;
    @JoinColumn(name = "ciudad_numero_ciudad", referencedColumnName = "numero_ciudad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ciudad ciudadNumeroCiudad;
    @JoinColumn(name = "tipo_identificacion_id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoIdentificacion tipoIdentificacionIdTipoIdentificacion;
    @JoinColumn(name = "tipo_rol_numero_rol", referencedColumnName = "numero_rol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRol tipoRolNumeroRol;
    @JoinColumn(name = "tipo_telefono_numero_tipo_telefono", referencedColumnName = "numero_tipo_telefono")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoTelefono tipoTelefonoNumeroTipoTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario", fetch = FetchType.LAZY)
    private List<Cita> citaList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, String contrasena, String correo, String numeroDocumento, String numeroTelefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.correo = correo;
        this.numeroDocumento = numeroDocumento;
        this.numeroTelefono = numeroTelefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }

    @XmlTransient
    public List<DespachoProducto> getDespachoProductoList() {
        return despachoProductoList;
    }

    public void setDespachoProductoList(List<DespachoProducto> despachoProductoList) {
        this.despachoProductoList = despachoProductoList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public Ciudad getCiudadNumeroCiudad() {
        return ciudadNumeroCiudad;
    }

    public void setCiudadNumeroCiudad(Ciudad ciudadNumeroCiudad) {
        this.ciudadNumeroCiudad = ciudadNumeroCiudad;
    }

    public TipoIdentificacion getTipoIdentificacionIdTipoIdentificacion() {
        return tipoIdentificacionIdTipoIdentificacion;
    }

    public void setTipoIdentificacionIdTipoIdentificacion(TipoIdentificacion tipoIdentificacionIdTipoIdentificacion) {
        this.tipoIdentificacionIdTipoIdentificacion = tipoIdentificacionIdTipoIdentificacion;
    }

    public TipoRol getTipoRolNumeroRol() {
        return tipoRolNumeroRol;
    }

    public void setTipoRolNumeroRol(TipoRol tipoRolNumeroRol) {
        this.tipoRolNumeroRol = tipoRolNumeroRol;
    }

    public TipoTelefono getTipoTelefonoNumeroTipoTelefono() {
        return tipoTelefonoNumeroTipoTelefono;
    }

    public void setTipoTelefonoNumeroTipoTelefono(TipoTelefono tipoTelefonoNumeroTipoTelefono) {
        this.tipoTelefonoNumeroTipoTelefono = tipoTelefonoNumeroTipoTelefono;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return correo;
    }
    
}
