package com.barber.model;

import com.barber.model.Cita;
import com.barber.model.Ciudad;
import com.barber.model.DespachoProducto;
import com.barber.model.Pedido;
import com.barber.model.TipoIdentificacion;
import com.barber.model.TipoRol;
import com.barber.model.TipoTelefono;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-08T01:54:50")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, TipoRol> tipoRolNumeroRol;
    public static volatile CollectionAttribute<Usuario, Cita> citaCollection;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, TipoIdentificacion> tipoIdentificacionIdTipoIdentificacion;
    public static volatile SingularAttribute<Usuario, Ciudad> ciudadNumeroCiudad;
    public static volatile CollectionAttribute<Usuario, Cita> citaCollection1;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile CollectionAttribute<Usuario, DespachoProducto> despachoProductoCollection;
    public static volatile SingularAttribute<Usuario, String> usuFoto;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, TipoTelefono> tipoTelefonoNumeroTipoTelefono;
    public static volatile SingularAttribute<Usuario, String> numeroDocumento;
    public static volatile CollectionAttribute<Usuario, Pedido> pedidoCollection;
    public static volatile SingularAttribute<Usuario, String> numeroTelefono;

}