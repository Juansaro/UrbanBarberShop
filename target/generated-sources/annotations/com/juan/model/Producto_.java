package com.juan.model;

import com.juan.model.Bodega;
import com.juan.model.DespachoProducto;
import com.juan.model.DetallePedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile ListAttribute<Producto, DetallePedido> detallePedidoList;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile ListAttribute<Producto, DespachoProducto> despachoProductoList;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile SingularAttribute<Producto, Bodega> bodegaIdBodega;
    public static volatile SingularAttribute<Producto, String> nombreProducto;

}