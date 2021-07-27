package com.juan.model;

import com.juan.model.Pedido;
import com.juan.model.Producto;
import com.juan.model.Proveedor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(DetallePedido.class)
public class DetallePedido_ { 

    public static volatile SingularAttribute<DetallePedido, Date> fechaSolicitud;
    public static volatile SingularAttribute<DetallePedido, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<DetallePedido, Float> costoTotal;
    public static volatile ListAttribute<DetallePedido, Pedido> pedidoList;
    public static volatile SingularAttribute<DetallePedido, Producto> productoIdProducto;
    public static volatile SingularAttribute<DetallePedido, Proveedor> proveedorNumeroProveedor;
    public static volatile SingularAttribute<DetallePedido, Integer> numeroDetalle;

}