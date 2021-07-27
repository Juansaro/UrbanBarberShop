package com.juan.model;

import com.juan.model.EstadoAsignacion;
import com.juan.model.Factura;
import com.juan.model.Servicio;
import com.juan.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> idCita;
    public static volatile SingularAttribute<Cita, Usuario> usuarioIdUsuario;
    public static volatile SingularAttribute<Cita, Date> fechaCita;
    public static volatile ListAttribute<Cita, Factura> facturaList;
    public static volatile SingularAttribute<Cita, Servicio> servicioIdServicio;
    public static volatile SingularAttribute<Cita, EstadoAsignacion> estadoAsignacionIdEstadoAsignacion;

}