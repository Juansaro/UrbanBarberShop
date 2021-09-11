/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.Cita;
import com.barber.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author juan
 */
@Local
public interface CitaFacadeLocal {

    void create(Cita cita);

    void edit(Cita cita);

    void remove(Cita cita);

    Cita find(Object id);

    List<Cita> findAll();

    List<Cita> findRange(int[] range);

    int count();

    public Object generarFactura(int idCitaIn);

//    public boolean crearCita(Cita citaIn, int fk_servicio, int estado_asignacion_id_estado_asignacion, int usuario_id_usuario);

    public boolean crearCita(Cita citaIn, int fk_servicio, int estado_asignacion_id_estado_asignacion, Usuario usuario_id_usuario);
    
}
