package com.barber.model;

import com.barber.model.Cita;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-04T11:57:57")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> descripcion;
    public static volatile SingularAttribute<Servicio, Float> costo;
    public static volatile SingularAttribute<Servicio, Integer> idServicio;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile ListAttribute<Servicio, Cita> citaList;

}