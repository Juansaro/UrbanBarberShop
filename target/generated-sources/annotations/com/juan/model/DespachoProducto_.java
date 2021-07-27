package com.juan.model;

import com.juan.model.Producto;
import com.juan.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(DespachoProducto.class)
public class DespachoProducto_ { 

    public static volatile SingularAttribute<DespachoProducto, Usuario> usuarioIdUsuario;
    public static volatile SingularAttribute<DespachoProducto, Date> fechaSolicitud;
    public static volatile SingularAttribute<DespachoProducto, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<DespachoProducto, Float> costoTotal;
    public static volatile SingularAttribute<DespachoProducto, Integer> numeroDespachoProducto;
    public static volatile SingularAttribute<DespachoProducto, Producto> productoIdProducto;

}