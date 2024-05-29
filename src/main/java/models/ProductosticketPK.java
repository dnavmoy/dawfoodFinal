/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author daniel
 */

//primaryKey compuesta de productosTicket


@Embeddable
public class ProductosticketPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IdProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "IdTicket")
    private int idTicket;

    public ProductosticketPK() {
    }

    public ProductosticketPK(int idProducto, int idTicket) {
        this.idProducto = idProducto;
        this.idTicket = idTicket;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idTicket;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosticketPK)) {
            return false;
        }
        ProductosticketPK other = (ProductosticketPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idTicket != other.idTicket) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventanas.ProductosticketPK[ idProducto=" + idProducto + ", idTicket=" + idTicket + " ]";
    }
    
}
