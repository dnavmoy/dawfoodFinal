package Entidades;

import Entidades.Producto;
import Entidades.ProductosticketPK;
import Entidades.Ticket;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-22T21:58:46", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Productosticket.class)
public class Productosticket_ { 

    public static volatile SingularAttribute<Productosticket, Ticket> ticket;
    public static volatile SingularAttribute<Productosticket, ProductosticketPK> productosticketPK;
    public static volatile SingularAttribute<Productosticket, Integer> cantidad;
    public static volatile SingularAttribute<Productosticket, Producto> producto;

}