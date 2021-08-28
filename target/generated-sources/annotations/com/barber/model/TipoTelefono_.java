package com.barber.model;

import com.barber.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-27T14:43:50")
@StaticMetamodel(TipoTelefono.class)
public class TipoTelefono_ { 

    public static volatile SingularAttribute<TipoTelefono, String> descripcion;
    public static volatile ListAttribute<TipoTelefono, Usuario> usuarioList;
    public static volatile SingularAttribute<TipoTelefono, Integer> numeroTipoTelefono;

}