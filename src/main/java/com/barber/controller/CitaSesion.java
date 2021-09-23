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
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Inject
    private RolRequest r_bar;

    private List<Cita> citas;
    private List<EstadoAsignacion> estadoAsignaciones;
    private List<Servicio> servicios;
    private List<Usuario> usuarios;
    private List<Usuario> barberos;
    private List<Servicio> listaServiciosAgendados = new ArrayList<>();
    private List<Servicio> listaServiciosEspera = new ArrayList<>();
    private List<Cita> listaUltimaFecha = new ArrayList<>();

    //Formato de fecha y hora actual
    private float cit_costototal = 0;

    private Servicio serTemporal = new Servicio();
    private Cita cit = new Cita();
    private Cita citTemporal = new Cita();
    //private Servicio ser = new Servicio();

    @PostConstruct
    public void init() {
        citas = citaFacadeLocal.findAll();
        estadoAsignaciones = estadoAsignacionFacadeLocal.findAll();
        servicios = servicioFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
        citTemporal = new Cita();
        listaServiciosAgendados.addAll(servicioFacadeLocal.findAll());
        listaUltimaFecha.addAll(citaFacadeLocal.findAll());
        cit_costototal = 0;
        cita = new Cita();
    }

    public void guardarServicioTemporal(Servicio srIn) {
        this.serTemporal = srIn;
    }

    public void cargaServiciosSolicitados(Servicio srIn) {
        listaServiciosEspera.add(srIn);
        cit_costototal = cit_costototal + srIn.getCosto();
    }

    public void eliminarServicioTemporal(Servicio sTemporal) {
        listaServiciosEspera.remove(sTemporal);
        cit_costototal = cit_costototal - sTemporal.getCosto();
    }

    public Date ObtenerFechaActual() {
        try {
            DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String fechaActual = d.format(LocalDateTime.now());
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date fechaFormateada = formato.parse(fechaActual);
            return fechaFormateada;
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean validarCitaRepetida() {
        listaUltimaFecha.addAll(citaFacadeLocal.findAll());
        if (listaUltimaFecha != null && !listaUltimaFecha.isEmpty()) {
            Cita item = listaUltimaFecha.get(listaUltimaFecha.size() - 1);
            Date registro = item.getRegistroActual();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(registro);
            calendar.add(Calendar.MINUTE, 30);
            Date fechaSalida = calendar.getTime();
            calendar.getTime();
            return ObtenerFechaActual().after(fechaSalida);
        } else {
            return true;
        }
    }

    //Registrar cita
    public void registrarCita() {
        try {
            //Validación en procedimiento almacenado de fechas pasadas y muy futuras, me retorna un boolean
            if (citaFacadeLocal.validarFechaCita(cit.getFechaCita()) && validarCitaRepetida()) {
                if (listaServiciosEspera.isEmpty()) {
                    System.out.println("Error");
                } else {
                    this.cit.setEstadoAsignacionIdEstadoAsignacion(est.citaEspera());
                    //Se establece el usuario logeado con la inyección de dependencias desde el UsuarioSesion -> usuLog
                    this.cit.setIdCliente(usu.getUsuLog());
                    this.cit.setIdBarbero(usuario);
                    this.cit.setRegistroActual(ObtenerFechaActual());
                    //Costo calculado del acumulador
                    this.cit.setCosto(cit_costototal);
                    citaFacadeLocal.create(cit);
                    //Iterator para registrar datos en la colección de citas y servicios (Funciona)
                    for (Servicio srIt : listaServiciosEspera) {
                        cit.setServicioList(listaServiciosEspera);
                    }
                    //Me permite leer SOLO las citas del cliente logeado
                    citas = citaFacadeLocal.leerTodos(usu.getUsuLog());
                    //Se envian los parámetros del nombre y correo desde el usuLog (Inutil hasta poder mandar la lista de las listas asignadas **)
                    /*CitaMail.correoCita(
                        usu.getUsuLog().getNombre(),
                        usu.getUsuLog().getApellido(),
                        cit.getServicioIdServicio().getNombre(),//No funciona así toca con un filtro en la colección de 
                        usu.getUsuLog().getCorreo(),
                        cit.getFechaCita()
                    );*/
                    //Limpieza del arrayList temporal
                    listaServiciosEspera.clear();
                    listaUltimaFecha.clear();
                    //Limpieza del acumulador del costo total en 0
                    this.cit_costototal = 0;
                    //Redirección
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/cliente/consultarCita.xhtml");
                    //Mensaje
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita registrada", "Cita registrada"));
                }
            } else {
                //Mensaje
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error de registro", "Error de registro"));
            }
        } catch (IOException e) {
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
            //this.citTemporal.setServicioIdServicio(servicio);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de eliminación", "Error de eliminación"));
        }

    }

    /*
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
    }*/
    public List<Usuario> leerBarberos() {
        return citaFacadeLocal.leerBarberos(r_bar.getRolBarbero());
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

    public List<Servicio> getListaServiciosAgendados() {
        return listaServiciosAgendados;
    }

    public void setListaServiciosAgendados(List<Servicio> listaServiciosAgendados) {
        this.listaServiciosAgendados = listaServiciosAgendados;
    }

    public Servicio getSerTemporal() {
        return serTemporal;
    }

    public void setSerTemporal(Servicio serTemporal) {
        this.serTemporal = serTemporal;
    }

    public List<Servicio> getListaServiciosEspera() {
        return listaServiciosEspera;
    }

    public void setListaServiciosEspera(List<Servicio> listaServiciosEspera) {
        this.listaServiciosEspera = listaServiciosEspera;
    }

    public float getCit_costototal() {
        return cit_costototal;
    }

    public void setCit_costototal(float cit_costototal) {
        this.cit_costototal = cit_costototal;
    }

    public List<Cita> getListaUltimaFecha() {
        return listaUltimaFecha;
    }

    public void setListaUltimaFecha(List<Cita> listaUltimaFecha) {
        this.listaUltimaFecha = listaUltimaFecha;
    }

}
