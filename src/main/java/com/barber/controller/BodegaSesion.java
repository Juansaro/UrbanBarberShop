/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.controller;

import com.barber.EJB.BodegaFacadeLocal;
import com.barber.model.Bodega;
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

/**
 *
 * @author juan
 */
@Named(value = "bodegaSesion")
@SessionScoped
public class BodegaSesion implements Serializable{
    
    @EJB
    private BodegaFacadeLocal bodegaFacadeLocal;
    
    private Bodega bodega;
    private List<Bodega> bodegas;
    
    private Bodega bod = new Bodega();
    private Bodega bodTemporal = new Bodega();
    
    private Part archivoCarga;
    
    @PostConstruct
    public void init(){
        bodegas = bodegaFacadeLocal.findAll();
        bodega = new Bodega();
    }
    
    //Registrar bodega
    public void registrarBodega(){
        try {
            bodegaFacadeLocal.create(bod);
            bodegas = bodegaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/UrbanBarberShop/faces/recepcionista/consultarBodega.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega registrada", "Bodega registrada"));
        } catch (Exception e) {
        }
    }
    //Recupera datos del bodega al cual se va a editar
     public void guardarTemporal(Bodega b) {
        bodTemporal = b;
    }

    //Editar bodega (En el modal)
    public void editarBodega() {
        try {
            bodegaFacadeLocal.edit(bodTemporal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega editada", "Bodega editada"));
        } catch (Exception e) {
            
        }
    }
    
    //Preparar página para eliminar
    public String prepararEliminar(){
        bodegas = bodegaFacadeLocal.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bodega eliminada", "Bodega eliminada"));
        return "/RecepConsultarUsuarios.xhtml";
    }
    //Eliminar
    public void eliminarBodega(Bodega b){
        try{
            this.bodegaFacadeLocal.remove(b);
            this.bodega = new Bodega();
            //Colocar prepararEliminar()
            prepararEliminar();
            
        }catch(Exception e){
            e.printStackTrace();
        }
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
                        
                        Bodega bogObj = bodegaFacadeLocal.validarSiExiste(nextline[0]);
                        if (bogObj == null) {
                            bodegaFacadeLocal.crearBodega(nextline[0], Integer.parseInt(nextline[1]));
                        } else {        
                            bodegaFacadeLocal.edit(bogObj);
                        }

                    }
                    reader.close();

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

    
    //carga inicial
    public void cargaInicialDatos(){
        
    }
    
    //Getters y Setters

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public Bodega getBod() {
        return bod;
    }

    public void setBod(Bodega bod) {
        this.bod = bod;
    }

    public Bodega getBodTemporal() {
        return bodTemporal;
    }

    public void setBodTemporal(Bodega bodTemporal) {
        this.bodTemporal = bodTemporal;
    }

    public Part getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(Part archivoCarga) {
        this.archivoCarga = archivoCarga;
    }
    
}
