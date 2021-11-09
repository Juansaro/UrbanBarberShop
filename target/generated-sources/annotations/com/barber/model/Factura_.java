package com.barber.model;

import com.barber.model.Calificacion;
import com.barber.model.Cita;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-08T20:25:35")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Cita> citaIdCita;
    public static volatile SingularAttribute<Factura, Float> costo;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile CollectionAttribute<Factura, Calificacion> calificacionCollection;

}