/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.ServicioFacadeLocal;
import com.barber.model.Servicio;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;

@Named(value = "servicioSesion")
@SessionScoped
public class ServicioSesion implements Serializable{
    //Conexión de FacadeLocal's
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;
    
    private Servicio servicio;
    private List<Servicio> servicios;
    
    private Servicio ser = new Servicio();
    private Servicio serTemporal = new Servicio();
    private Part archivoCarga;
    
    @PostConstruct
    public void init(){
        servicios = servicioFacadeLocal.findAll();
        servicio = new Servicio();
    }
    
    //Registrar servicio
    public String registrarServicio(){
        try {
            servicioFacadeLocal.create(ser);
            servicios = servicioFacadeLocal.findAll();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarServicio.xhtml");
        } catch (Exception e) {
        }
        return null;
    }
   
    //Recupera datos del servico al cual se va a editar
     public void guardarTemporal(Servicio s) {
        serTemporal = s;
    }
     
    //Editar
    public void editarServicio(){
        try{
            servicioFacadeLocal.edit(serTemporal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio editado", "Servicio editado"));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al editar", "Error al editar"));
        }
    }
    
    //Eliminar
    public void eliminarServicio(Servicio s){
        try{
            this.servicioFacadeLocal.remove(s);
            this.servicio = new Servicio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio eliminado", "Servicio eliminado"));
            servicios = servicioFacadeLocal.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Recupera datos del servicio al cual se va a editar
     public void prepararEditar(Servicio serIn) {
        serTemporal = serIn;
    }
     
    public void cargarInicialDatos() {
        if (archivoCarga != null) {
            if (archivoCarga.getSize() > 700000) {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'El archivo !',"
                        + "  text: 'No se puede cargar por el tamaño !!!',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Ok'"
                        + "})");
            } else if (archivoCarga.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {

                try (InputStream is = archivoCarga.getInputStream()) {
                    File carpeta = new File("C:\\ubs\\recepcionista\\archivos");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    Files.copy(is, (new File(carpeta, archivoCarga.getSubmittedFileName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    CSVParser conPuntoyComa = new CSVParserBuilder().withSeparator(';').build();
                    CSVReader reader = new CSVReaderBuilder(new FileReader("C:\\ubs\\recepcionista\\archivos\\" + archivoCarga.getSubmittedFileName())).withCSVParser(conPuntoyComa).build();
                    String[] nextline;
                    while ((nextline = reader.readNext()) != null) {
                        
                        Servicio bogObj = servicioFacadeLocal.validarSiExiste(nextline[0]);
                        if (bogObj == null) {
                            servicioFacadeLocal.crearServicio(nextline[0], nextline[1], Float.parseFloat(nextline[2]));
                        } else {        
                            servicioFacadeLocal.edit(bogObj);
                        }

                    }
                    reader.close();
                    servicios = servicioFacadeLocal.findAll();
                } catch (Exception e) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Problemas !',"
                            + "  text: 'No se puede realizar esta accion',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Ok'"
                            + "})");
                }
            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'El archivo !',"
                        + "  text: 'No es una imagen .png o .jpeg !!!',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Ok'"
                        + "})");
            }

        } else {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Problemas !',"
                    + "  text: 'No se puede realizar esta accion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Ok'"
                    + "})");
        }

        PrimeFaces.current().executeScript("document.getElementById('resetform').click()");

    }

   
    //Getters y Setters
    
    public Servicio getSer() {
        return ser;
    }

    public void setSer(Servicio ser) {
        this.ser = ser;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Servicio getSerTemporal() {
        return serTemporal;
    }

    public void setSerTemporal(Servicio serTemporal) {
        this.serTemporal = serTemporal;
    }

    public Part getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(Part archivoCarga) {
        this.archivoCarga = archivoCarga;
    }
    
}

