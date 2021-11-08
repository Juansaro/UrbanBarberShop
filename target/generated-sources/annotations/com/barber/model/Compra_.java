package com.barber.model;

import com.barber.model.DetalleCompra;
import com.barber.model.Proveedor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-08T01:54:50")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile CollectionAttribute<Compra, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Compra, Date> fechaSolicitud;
    public static volatile SingularAttribute<Compra, Integer> numeroCompra;
    public static volatile SingularAttribute<Compra, Proveedor> numeroProveedor;

}