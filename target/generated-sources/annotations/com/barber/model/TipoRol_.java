package com.barber.model;

import com.barber.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T15:39:36")
@StaticMetamodel(TipoRol.class)
public class TipoRol_ { 

    public static volatile SingularAttribute<TipoRol, String> descripcion;
    public static volatile ListAttribute<TipoRol, Usuario> usuarioList;
    public static volatile SingularAttribute<TipoRol, Integer> numeroRol;

}