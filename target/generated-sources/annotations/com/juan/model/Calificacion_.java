package com.juan.model;

import com.juan.model.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(Calificacion.class)
public class Calificacion_ { 

    public static volatile SingularAttribute<Calificacion, String> puntaje;
    public static volatile SingularAttribute<Calificacion, Integer> idCalificacion;
    public static volatile SingularAttribute<Calificacion, Factura> facturaIdFactura;
    public static volatile SingularAttribute<Calificacion, String> comentario;

}