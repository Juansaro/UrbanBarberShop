package com.barber.model;

import com.barber.model.Pedido;
import com.barber.model.Producto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-24T12:59:11")
@StaticMetamodel(DetallePedido.class)
public class DetallePedido_ { 

    public static volatile SingularAttribute<DetallePedido, Date> fechaSolicitud;
    public static volatile SingularAttribute<DetallePedido, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<DetallePedido, Float> costoTotal;
    public static volatile SingularAttribute<DetallePedido, Producto> productoIdProducto;
    public static volatile CollectionAttribute<DetallePedido, Pedido> pedidoCollection;
    public static volatile SingularAttribute<DetallePedido, Integer> numeroDetalle;

}