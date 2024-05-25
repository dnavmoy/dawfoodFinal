package Entidades;

import Entidades.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-25T23:12:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, String> tipo;
    public static volatile SingularAttribute<Categorias, Integer> codCategoria;
    public static volatile CollectionAttribute<Categorias, Producto> productoCollection;
    public static volatile SingularAttribute<Categorias, String> nombreCategoria;

}