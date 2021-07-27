package com.juan.model;

import com.juan.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T18:03:56")
@StaticMetamodel(TipoTelefono.class)
public class TipoTelefono_ { 

    public static volatile SingularAttribute<TipoTelefono, String> descripcion;
    public static volatile ListAttribute<TipoTelefono, Usuario> usuarioList;
    public static volatile SingularAttribute<TipoTelefono, Integer> numeroTipoTelefono;

}