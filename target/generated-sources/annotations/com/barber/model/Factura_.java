package com.barber.model;

import com.barber.model.Calificacion;
import com.barber.model.Cita;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-27T14:43:50")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Cita> citaIdCita;
    public static volatile SingularAttribute<Factura, Float> costo;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile ListAttribute<Factura, Calificacion> calificacionList;

}