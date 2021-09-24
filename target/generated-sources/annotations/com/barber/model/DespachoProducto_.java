package com.barber.model;

import com.barber.model.Producto;
import com.barber.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-24T10:01:58")
@StaticMetamodel(DespachoProducto.class)
public class DespachoProducto_ { 

    public static volatile SingularAttribute<DespachoProducto, Usuario> usuarioIdUsuario;
    public static volatile SingularAttribute<DespachoProducto, Date> fechaSolicitud;
    public static volatile SingularAttribute<DespachoProducto, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<DespachoProducto, Float> costoTotal;
    public static volatile SingularAttribute<DespachoProducto, Integer> numeroDespachoProducto;
    public static volatile SingularAttribute<DespachoProducto, Producto> productoIdProducto;

}