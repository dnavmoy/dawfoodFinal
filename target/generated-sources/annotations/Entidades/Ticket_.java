package Entidades;

import Entidades.Productosticket;
import Entidades.Tpv;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-23T10:08:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> idTicket;
    public static volatile SingularAttribute<Ticket, Integer> numPedido;
    public static volatile SingularAttribute<Ticket, Date> fecha;
    public static volatile SingularAttribute<Ticket, Tpv> codigoTpv;
    public static volatile SingularAttribute<Ticket, Double> totalIva;
    public static volatile SingularAttribute<Ticket, Double> totalPedido;
    public static volatile SingularAttribute<Ticket, Integer> codTransaccion;
    public static volatile CollectionAttribute<Ticket, Productosticket> productosticketCollection;

}