/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.CitaFacadeLocal;
import com.barber.EJB.EstadoAsignacionFacadeLocal;
import com.barber.EJB.ServicioFacadeLocal;
import com.barber.EJB.UsuarioFacadeLocal;
import com.barber.model.Cita;
import com.barber.model.EstadoAsignacion;
import com.barber.model.Servicio;
import com.barber.model.Usuario;
import com.barber.utilidades.CitaMail;
import com.barber.utilidades.CitaMailAgendado;
import com.barber.utilidades.CitaMailCancelado;
import com.barber.utilidades.CitaMailEspera;
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
@Named(value = "citaSesion")
@SessionScoped
public class CitaSesion implements Serializable {

    @EJB
    private CitaFacadeLocal citaFacadeLocal;
    @EJB
    private EstadoAsignacionFacadeLocal estadoAsignacionFacadeLocal;
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    private Cita cita;

    private CitaMail primerMail;

    @Inject
    private EstadoAsignacionRequest est;
    @Inject
    private UsuarioSesion usu;
    @Inject
    private EstadoAsignacion estadoAsignacion;
    @Inject
    private Servicio servicio;
    @Inject
    private Usuario usuario;

    private List<Cita> citas;
    private List<EstadoAsignacion> estadoAsignaciones;
    private List<Servicio> servicios;
    private List<Usuario> usuarios;

    private Cita cit = new Cita();
    private Cita citTemporal = new Cita();
    private Servicio ser = new Servicio();

    @PostConstruct
    public void init() {
        citas = citaFacadeLocal.findAll();
        estadoAsignaciones = estadoAsignacionFacadeLocal.findAll();
        servicios = servicioFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
        cita = new Cita();
    }

    //Registrar cita
    //Volver por estado de asignación
    public void registrarCita() {
        try {
            //Validación en procedimiento almacenado
            if (citaFacadeLocal.validarFechaCita(cit.getFechaCita())) {
                //Llave foranea por defecto "En espera"
                this.cit.setEstadoAsignacionIdEstadoAsignacion(est.citaEspera());
                this.cit.setServicioIdServicio(servicio);
                //Se establece el usuario logeado con la inyección de dependencias desde el UsuarioSesion -> usuLog
                this.cit.setUsuarioIdUsuario(usu.getUsuLog());
                citaFacadeLocal.create(cit);
                //Me permite leer SOLO las citas del cliente logeado
                citas = citaFacadeLocal.leerTodos(usu.getUsuLog());
                //Se envian los parámetros del nombre y correo desde el usuLog
                CitaMail.correoCita(
                        usu.getUsuLog().getNombre(),
                        usu.getUsuLog().getApellido(),
                        cit.getServicioIdServicio().getNombre(),
                        usu.getUsuLog().getCorreo(),
                        cit.getFechaCita()
                );
                FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/cliente/consultarCita.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita registrada", "Cita registrada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error de registro", "Error de registro"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error de registro", "Error de registro"));
        }
    }

    //guardar temporal
    public void guardarTemporal(Cita c) {
        citTemporal = c;
    }

    //Editar
    public void editarCita() {
        try {
            this.citTemporal.setEstadoAsignacionIdEstadoAsignacion(estadoAsignacion);
            this.citTemporal.setServicioIdServicio(servicio);
            citaFacadeLocal.edit(citTemporal);
            citas = citaFacadeLocal.findAll();

            //this.cita = new Cita();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    //Eliminar
    public void eliminarCita(Cita c) {
        try {
            this.citaFacadeLocal.remove(c);
            this.cita = new Cita();
            citas = citaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita eliminada", "Cita eliminada"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de eliminación", "Error de eliminación"));
        }

    }
    
    public void avisarEmailCliente(Cita c) {
        try {
            switch (c.getEstadoAsignacionIdEstadoAsignacion().toString()) {
                case "Agendado":
                    CitaMailAgendado.correoCita(
                            c.getUsuarioIdUsuario().getNombre(),
                            c.getUsuarioIdUsuario().getApellido(),
                            c.getServicioIdServicio().getNombre(),
                            c.getUsuarioIdUsuario().getCorreo(),
                            c.getFechaCita()
                    );
                    break;
                case "Cancelado":
                    CitaMailCancelado.correoCita(
                            c.getUsuarioIdUsuario().getNombre(),
                            c.getUsuarioIdUsuario().getApellido(),
                            c.getServicioIdServicio().getNombre(),
                            c.getUsuarioIdUsuario().getCorreo(),
                            c.getFechaCita()
                    );
                    break;
                case "Espera":
                    CitaMailEspera.correoCita(
                            c.getUsuarioIdUsuario().getNombre(),
                            c.getUsuarioIdUsuario().getApellido(),
                            c.getServicioIdServicio().getNombre(),
                            c.getUsuarioIdUsuario().getCorreo(),
                            c.getFechaCita()
                    );
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public List<Cita> leerTodos() {
        return citaFacadeLocal.leerTodos(usu.getUsuLog());
    }

    public List<Cita> leerCitas() {
        return citaFacadeLocal.generarFactura(getCita().getIdCita());
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Cita getCit() {
        return cit;
    }

    public void setCit(Cita cit) {
        this.cit = cit;
    }

    public CitaFacadeLocal getCitaFacadeLocal() {
        return citaFacadeLocal;
    }

    public void setCitaFacadeLocal(CitaFacadeLocal citaFacadeLocal) {
        this.citaFacadeLocal = citaFacadeLocal;
    }

    public EstadoAsignacion getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(EstadoAsignacion estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EstadoAsignacion> getEstadoAsignaciones() {
        return estadoAsignaciones;
    }

    public void setEstadoAsignaciones(List<EstadoAsignacion> estadoAsignaciones) {
        this.estadoAsignaciones = estadoAsignaciones;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Cita getCitTemporal() {
        return citTemporal;
    }

    public void setCitTemporal(Cita citTemporal) {
        this.citTemporal = citTemporal;
    }

}
