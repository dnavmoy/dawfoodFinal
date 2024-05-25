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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByIva", query = "SELECT p FROM Producto p WHERE p.iva = :iva"),
    @NamedQuery(name = "Producto.findByStock", query = "SELECT p FROM Producto p WHERE p.stock = :stock"),
    @NamedQuery(name = "Producto.findByCodCategoria", query = "SELECT p FROM Producto p WHERE p.codCategoria = :codCategoria"),
    //@NamedQuery(name = "Producto.findBycoso", query = "SELECT p FROM Producto p WHERE p.codCategoria = :codCategoria"),
    //@NamedQuery(name = "Producto.update", query = "UPDATE Producto p SET p.Descripcion = :Descripcion ,p.Precio = :Precio ,p.Iva = :Iva ,p.Stock= :Stock, p.CodCategoria= :CodCategoria WHERE p.IdProducto= :IdProducto ")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "Precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "Iva")
    private float iva;
    @Basic(optional = false)
    @Column(name = "Stock")
    private int stock;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    //private Collection<Productosticket> productosticketCollection;
    @JoinColumn(name = "CodCategoria", referencedColumnName = "CodCategoria")
    @ManyToOne(optional = false)
    private Categorias codCategoria;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String descripcion, float precio, float iva, int stock,int codigocategoria) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.iva = iva;
        this.stock = stock;
        this.codCategoria=new Categorias(codigocategoria);
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //public Collection<Productosticket> getProductosticketCollection() {
    //    return productosticketCollection;
    //}

    //public void setProductosticketCollection(Collection<Productosticket> productosticketCollection) {
    //    this.productosticketCollection = productosticketCollection;
    //}

    public Categorias getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Categorias codCategoria) {
        this.codCategoria = codCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("idProducto=").append(idProducto);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", iva=").append(iva);
        sb.append(", stock=").append(stock);
        //sb.append(", productosticketCollection=").append(productosticketCollection);
        sb.append(", codCategoria=").append(codCategoria);
        sb.append('}');
        return sb.toString();
    }

   
    
    
}
