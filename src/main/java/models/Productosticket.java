/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import metodos.MetodoEntidades;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */

//detalle de ticket, idproducto,idticket y cantidad mediante enteros JoinColum y manytoOne con producto y ticket para consultar los id y crear objeto producto y ticket
@Entity
@Table(name = "productosticket")
@NamedQueries({
    @NamedQuery(name = "Productosticket.findAll", query = "SELECT p FROM Productosticket p"),
    @NamedQuery(name = "Productosticket.findByIdProducto", query = "SELECT Count(p) FROM Productosticket p WHERE p.productosticketPK.idProducto = :idProducto"),
    @NamedQuery(name = "Productosticket.findByIdTicket", query = "SELECT p FROM Productosticket p WHERE p.productosticketPK.idTicket = :idTicket"),
    @NamedQuery(name = "Productosticket.findByCantidad", query = "SELECT p FROM Productosticket p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Productosticket.findsienTicket", query = "SELECT Count(p) FROM Productosticket p WHERE p.productosticketPK.idProducto = :idProducto")
})
public class Productosticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosticketPK productosticketPK;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto", insertable = false, updatable = false)
    @ManyToOne()
    private Producto producto;
    @JoinColumn(name = "IdTicket", referencedColumnName = "IdTicket", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ticket ticket;

    public Productosticket() {
    }

       
    public Productosticket(ProductosticketPK productosticketPK) {
        this.productosticketPK = productosticketPK;
    }

    public Productosticket(ProductosticketPK productosticketPK, int cantidad) {
        this.productosticketPK = productosticketPK;
        producto= metodos.Metodos.buscarEnLista(metodos.MetodoEntidades.getListaProductos(), productosticketPK.getIdProducto());
        ticket=MetodoEntidades.getListaTickets().get(productosticketPK.getIdTicket()-1);
        this.cantidad = cantidad;
    }

    public Productosticket(int idProducto, int idTicket) {
        this.productosticketPK = new ProductosticketPK(idProducto, idTicket);
    }

    public ProductosticketPK getProductosticketPK() {
        return productosticketPK;
    }

    public void setProductosticketPK(ProductosticketPK productosticketPK) {
        this.productosticketPK = productosticketPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosticketPK != null ? productosticketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productosticket)) {
            return false;
        }
        Productosticket other = (Productosticket) object;
        if ((this.productosticketPK == null && other.productosticketPK != null) || (this.productosticketPK != null && !this.productosticketPK.equals(other.productosticketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventanas.Productosticket[ productosticketPK=" + productosticketPK + "cantidad"+cantidad+" ]";
    }
    
}
