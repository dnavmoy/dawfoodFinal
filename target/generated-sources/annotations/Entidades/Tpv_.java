package Entidades;

import models.Tpv;
import models.Ticket;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-26T20:26:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tpv.class)
public class Tpv_ { 

    public static volatile SingularAttribute<Tpv, String> ubicacion;
    public static volatile CollectionAttribute<Tpv, Ticket> ticketCollection;
    public static volatile SingularAttribute<Tpv, Integer> codigoTpv;

}