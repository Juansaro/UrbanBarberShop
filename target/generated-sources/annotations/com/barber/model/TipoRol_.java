package com.barber.model;

import com.barber.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T22:35:40")
@StaticMetamodel(TipoRol.class)
public class TipoRol_ { 

    public static volatile SingularAttribute<TipoRol, String> descripcion;
    public static volatile CollectionAttribute<TipoRol, Usuario> usuarioCollection;
    public static volatile SingularAttribute<TipoRol, Integer> numeroRol;

}