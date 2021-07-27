package com.juan.model;

import com.juan.model.DetallePedido;
import com.juan.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Usuario> usuarioIdUsuario;
    public static volatile SingularAttribute<Pedido, Date> fechaRecibida;
    public static volatile SingularAttribute<Pedido, DetallePedido> detallePedidoNumeroDetalle;
    public static volatile SingularAttribute<Pedido, Integer> numeroPedido;

}