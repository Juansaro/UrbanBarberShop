package com.barber.model;

import com.barber.model.Bodega;
import com.barber.model.DespachoProducto;
import com.barber.model.DetalleCompra;
import com.barber.model.DetallePedido;
import com.barber.model.Proveedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-17T14:44:21")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, String> productoFoto;
    public static volatile CollectionAttribute<Producto, DespachoProducto> despachoProductoCollection;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile CollectionAttribute<Producto, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile SingularAttribute<Producto, Bodega> bodegaIdBodega;
    public static volatile CollectionAttribute<Producto, DetallePedido> detallePedidoCollection;
    public static volatile SingularAttribute<Producto, Proveedor> proveedorNumeroProveedor;
    public static volatile SingularAttribute<Producto, String> nombreProducto;

}