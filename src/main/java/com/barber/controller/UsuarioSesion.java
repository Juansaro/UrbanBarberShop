/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CiudadFacadeLocal;
import com.barber.EJB.TipoIdentificacionFacadeLocal;
import com.barber.EJB.TipoRolFacadeLocal;
import com.barber.EJB.TipoTelefonoFacadeLocal;
import com.barber.EJB.UsuarioFacadeLocal;
import com.barber.model.Ciudad;
import com.barber.model.TipoIdentificacion;
import com.barber.model.TipoRol;
import com.barber.model.TipoTelefono;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author juan
 */
@Named(value = "usuarioSesion")
@SessionScoped
public class UsuarioSesion implements Serializable{
    
    //Conexión con FacadeLocal's
    //Es un punto de conexión a la base de datos
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    //LLaves FK en FacadeLocal
    @EJB
    private CiudadFacadeLocal ciudadFacadeLocal;
    @EJB
    private TipoRolFacadeLocal rolFacadeLocal;
    @EJB
    private TipoTelefonoFacadeLocal tipoTelefonoFacadeLocal;
    @EJB
    private TipoIdentificacionFacadeLocal tipoIdentificacionFacadeLocal;
    
    private Usuario usuario;
    //Usar esta estructura para las FK
    @Inject
    private Ciudad ciudad;
    @Inject
    private TipoRol tipoRol;
    @Inject
    private TipoTelefono tipoTelefono;
    @Inject
    private TipoIdentificacion tipoIdentificacion;
    
    //Usar esta estructura para las FK (Listar)
    private List<Usuario> usuarios;
    private List<TipoIdentificacion> tipoIdentificaciones;
    private List<TipoTelefono> tipoTelefonos;
    private List<TipoRol> roles;
    private List<Ciudad> ciudades;
    
    //Atributos de clase
    private String correoUsuario;
    private String contrasena;
            
    //------>Instacías de sesión<------
    private Usuario usuReg = new Usuario();
    private Usuario usuLog = new Usuario();
    private Usuario usuTemporal = new Usuario();
    //------>Instacías de sesión<------
    
    //Este código me permite mostrar los datos en un select de un formulario (Me lista los datos en la vista)
    @PostConstruct
    public void init(){
        //Usar esta estructura para las FK
        usuarios = usuarioFacadeLocal.findAll();
        ciudades = ciudadFacadeLocal.findAll();
        roles = rolFacadeLocal.findAll();
        tipoTelefonos = tipoTelefonoFacadeLocal.findAll();
        tipoIdentificaciones = tipoIdentificacionFacadeLocal.findAll();
        usuario = new Usuario();
    }
    
    //Registrar usuario
    public void registrarUsuario(){
        try {
            //Usar esta estructura para las FK
            this.usuReg.setCiudadNumeroCiudad(ciudad);
            this.usuReg.setTipoRolNumeroRol(tipoRol);
            this.usuReg.setTipoIdentificacionIdTipoIdentificacion(tipoIdentificacion);
            this.usuReg.setTipoTelefonoNumeroTipoTelefono(tipoTelefono);
            //Principal
            usuarioFacadeLocal.create(usuReg);
            usuarios = usuarioFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }
    
    //Login
    public String validarUsuario(){
        usuLog = usuarioFacadeLocal.encontrarUsuarioCorreo(correoUsuario);
        //rol = ejbFacade.encontrarRol(numeroRol);
        if(usuLog != null){
            if(usuLog.getCorreo().equals(correoUsuario)){
                if(usuLog.getContrasena().equals(contrasena)){
                    switch (usuLog.getTipoRolNumeroRol().toString()) {
                        case "Recepcionista":
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("correo", correoUsuario);
                            return "/RecepInicio.xhtml";
                        case "Cliente":
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("correo", correoUsuario);
                            return "/ClienteInicio.xhtml";
                        case "Barbero":
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("correo", correoUsuario);
                            return "/BarberInicio.xhtml";
                        default:
                            break;
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No dispones de un rol en el sistema", "No dispones de un rol en el sistema"));
                    return null;
                    
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Clave incorrecta", "Clave incorrecta"));
                return null;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no existe"));
            return null;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no existe"));
        return null;
    }
    
    //Cerrar sesion
    public String cerrarSesion(){
        //Se destruye la información almacenada en el FacesContext (Dentro del método validarUsuario())
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml";
    }
    
    //Recupera datos del usuario al cual se va a editar
     public String guardarTemporal(Usuario u) {
        usuTemporal = u;
        return "/RecepModificarUsuarios.xhtml";
    }

    //Editar usuario (En el modal)
    public String editarUsuario() {
        try {
            usuarioFacadeLocal.edit(usuTemporal);
            this.usuario = new Usuario();
            return "/RecepConsultarUsuarios.xhtml";
        } catch (Exception e) {
            
        }
        return null;
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        usuarios = usuarioFacadeLocal.findAll();
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarUsuario(Usuario u){
        try{
            this.usuarioFacadeLocal.remove(u);
            this.usuario = new Usuario();
            //Colocar prepararEliminar()
            prepararEliminar();
        }catch(Exception e){
          
        }
    }       
    
    //Constructor vacío
    public UsuarioSesion(){
        
    }
    
    //Getters y Setters
    
    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public TipoRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(TipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<TipoIdentificacion> getTipoIdentificaciones() {
        return tipoIdentificaciones;
    }

    public void setTipoIdentificaciones(List<TipoIdentificacion> tipoIdentificaciones) {
        this.tipoIdentificaciones = tipoIdentificaciones;
    }

    public List<TipoTelefono> getTipoTelefonos() {
        return tipoTelefonos;
    }

    public void setTipoTelefonos(List<TipoTelefono> tipoTelefonos) {
        this.tipoTelefonos = tipoTelefonos;
    }

    public List<TipoRol> getRoles() {
        return roles;
    }

    public void setRoles(List<TipoRol> roles) {
        this.roles = roles;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuLog() {
        return usuLog;
    }

    public void setUsuLog(Usuario usuLog) {
        this.usuLog = usuLog;
    }

    public Usuario getUsuTemporal() {
        return usuTemporal;
    }

    public void setUsuTemporal(Usuario usuTemporal) {
        this.usuTemporal = usuTemporal;
    }
    
}

