/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "tpv")
@NamedQueries({
    @NamedQuery(name = "Tpv.findAll", query = "SELECT t FROM Tpv t"),
    @NamedQuery(name = "Tpv.findByCodigoTpv", query = "SELECT t FROM Tpv t WHERE t.codigoTpv = :codigoTpv"),
    @NamedQuery(name = "Tpv.findByUbicacion", query = "SELECT t FROM Tpv t WHERE t.ubicacion = :ubicacion")})
public class Tpv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodigoTpv")
    private Integer codigoTpv;
    @Basic(optional = false)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTpv")
    private Collection<Ticket> ticketCollection;

    public Tpv() {
    }

    public Tpv(Integer codigoTpv) {
        this.codigoTpv = codigoTpv;
    }

    public Tpv(Integer codigoTpv, String ubicacion) {
        this.codigoTpv = codigoTpv;
        this.ubicacion = ubicacion;
    }

    public Integer getCodigoTpv() {
        return codigoTpv;
    }

    public void setCodigoTpv(Integer codigoTpv) {
        this.codigoTpv = codigoTpv;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTpv != null ? codigoTpv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpv)) {
            return false;
        }
        Tpv other = (Tpv) object;
        if ((this.codigoTpv == null && other.codigoTpv != null) || (this.codigoTpv != null && !this.codigoTpv.equals(other.codigoTpv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventanas.Tpv[ codigoTpv=" + codigoTpv + " ]";
    }
    
}
