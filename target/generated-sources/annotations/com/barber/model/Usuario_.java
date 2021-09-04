package com.barber.model;

import com.barber.model.Cita;
import com.barber.model.Ciudad;
import com.barber.model.DespachoProducto;
import com.barber.model.Pedido;
import com.barber.model.TipoIdentificacion;
import com.barber.model.TipoRol;
import com.barber.model.TipoTelefono;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-04T11:57:57")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, TipoRol> tipoRolNumeroRol;
    public static volatile ListAttribute<Usuario, DespachoProducto> despachoProductoList;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, TipoIdentificacion> tipoIdentificacionIdTipoIdentificacion;
    public static volatile ListAttribute<Usuario, Pedido> pedidoList;
    public static volatile SingularAttribute<Usuario, Ciudad> ciudadNumeroCiudad;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> usuFoto;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, TipoTelefono> tipoTelefonoNumeroTipoTelefono;
    public static volatile SingularAttribute<Usuario, String> numeroDocumento;
    public static volatile ListAttribute<Usuario, Cita> citaList;
    public static volatile SingularAttribute<Usuario, String> numeroTelefono;

}