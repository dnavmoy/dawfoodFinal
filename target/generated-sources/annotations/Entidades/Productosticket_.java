package Entidades;

import models.Productosticket;
import models.Producto;
import models.ProductosticketPK;
import models.Ticket;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-26T20:26:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Productosticket.class)
public class Productosticket_ { 

    public static volatile SingularAttribute<Productosticket, Ticket> ticket;
    public static volatile SingularAttribute<Productosticket, ProductosticketPK> productosticketPK;
    public static volatile SingularAttribute<Productosticket, Integer> cantidad;
    public static volatile SingularAttribute<Productosticket, Producto> producto;

}