package com.juan.model;

import com.juan.model.Cita;
import com.juan.model.Ciudad;
import com.juan.model.DespachoProducto;
import com.juan.model.Pedido;
import com.juan.model.TipoIdentificacion;
import com.juan.model.TipoRol;
import com.juan.model.TipoTelefono;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, TipoRol> tipoRolNumeroRol;
    public static volatile ListAttribute<Usuario, DespachoProducto> despachoProductoList;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, TipoIdentificacion> tipoIdentificacionIdTipoIdentificacion;
    public static volatile ListAttribute<Usuario, Pedido> pedidoList;
    public static volatile SingularAttribute<Usuario, Ciudad> ciudadNumeroCiudad;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, TipoTelefono> tipoTelefonoNumeroTipoTelefono;
    public static volatile SingularAttribute<Usuario, String> numeroDocumento;
    public static volatile ListAttribute<Usuario, Cita> citaList;
    public static volatile SingularAttribute<Usuario, String> numeroTelefono;

}