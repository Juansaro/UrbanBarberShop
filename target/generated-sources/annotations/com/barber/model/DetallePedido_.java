package com.barber.model;

import com.barber.model.Pedido;
import com.barber.model.Producto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-17T21:22:20")
@StaticMetamodel(DetallePedido.class)
public class DetallePedido_ { 

    public static volatile SingularAttribute<DetallePedido, Date> fechaSolicitud;
    public static volatile SingularAttribute<DetallePedido, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<DetallePedido, Float> costoTotal;
    public static volatile ListAttribute<DetallePedido, Pedido> pedidoList;
    public static volatile SingularAttribute<DetallePedido, Producto> productoIdProducto;
    public static volatile SingularAttribute<DetallePedido, Integer> numeroDetalle;

}