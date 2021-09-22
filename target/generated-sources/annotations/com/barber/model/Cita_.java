package com.barber.model;

import com.barber.model.EstadoAsignacion;
import com.barber.model.Factura;
import com.barber.model.Servicio;
import com.barber.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T15:39:36")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> idCita;
    public static volatile SingularAttribute<Cita, Date> fechaCita;
    public static volatile SingularAttribute<Cita, Usuario> idCliente;
    public static volatile SingularAttribute<Cita, Usuario> idBarbero;
    public static volatile SingularAttribute<Cita, Float> costo;
    public static volatile ListAttribute<Cita, Factura> facturaList;
    public static volatile ListAttribute<Cita, Servicio> servicioList;
    public static volatile SingularAttribute<Cita, EstadoAsignacion> estadoAsignacionIdEstadoAsignacion;

}