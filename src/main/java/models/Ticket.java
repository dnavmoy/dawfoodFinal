/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.Productosticket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


// cuidado que has cambiado DAte por localDate!!!

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "ticket")
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByNumPedido", query = "SELECT t FROM Ticket t WHERE t.numPedido = :numPedido"),
    @NamedQuery(name = "Ticket.findByCodTransaccion", query = "SELECT t FROM Ticket t WHERE t.codTransaccion = :codTransaccion"),
    @NamedQuery(name = "Ticket.findByFecha", query = "SELECT t FROM Ticket t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Ticket.findByTotalPedido", query = "SELECT t FROM Ticket t WHERE t.totalPedido = :totalPedido"),
    @NamedQuery(name = "Ticket.findByTotalIva", query = "SELECT t FROM Ticket t WHERE t.totalIva = :totalIva")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTicket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "NumPedido")
    private int numPedido;
    @Basic(optional = false)
    @Column(name = "CodTransaccion")
    private int codTransaccion;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "TotalPedido")
    private double totalPedido;
    @Basic(optional = false)
    @Column(name = "TotalIva")
    private double totalIva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private Collection<Productosticket> productosticketCollection;
    @JoinColumn(name = "CodigoTpv", referencedColumnName = "CodigoTpv")
    @ManyToOne(optional = false)
    private Tpv codigoTpv;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, int numPedido, int codTransaccion, Date fecha, double totalPedido, double totalIva,Tpv codigo) {
        this.idTicket = idTicket;
        this.numPedido = numPedido;
        this.codTransaccion = codTransaccion;
        this.fecha = fecha;
        this.totalPedido = totalPedido;
        this.totalIva = totalIva;
        this.codigoTpv= codigo;
        this.productosticketCollection = new ArrayList<Productosticket>();
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public int getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(int codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    public Collection<Productosticket> getProductosticketCollection() {
        return productosticketCollection;
    }

    public void setProductosticketCollection(ArrayList<Productosticket> productosticketCollection) {
        this.productosticketCollection = productosticketCollection;
    }

    public Tpv getCodigoTpv() {
        return codigoTpv;
    }

    public void setCodigoTpv(Tpv codigoTpv) {
        this.codigoTpv = codigoTpv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventanas.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
