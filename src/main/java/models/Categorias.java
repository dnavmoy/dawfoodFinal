/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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

//entidad categorias, nombre,cod y tipo -> oneTomany con productos mediante codCategoria asi se pueden consultar los productos de una categoria
@Entity
@Table(name = "categorias")
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByCodCategoria", query = "SELECT c FROM Categorias c WHERE c.codCategoria = :codCategoria"),
    @NamedQuery(name = "Categorias.findByNombreCategoria", query = "SELECT c FROM Categorias c WHERE c.nombreCategoria = :nombreCategoria"),
    @NamedQuery(name = "Categorias.findByTipo", query = "SELECT c FROM Categorias c WHERE c.tipo = :tipo")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodCategoria")
    private Integer codCategoria;
    @Basic(optional = false)
    @Column(name = "NombreCategoria")
    private String nombreCategoria;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    //un producto solo puede ser de una categoria, y una categoria puede tener muchos productos, 
    //
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCategoria")
    private Collection<Producto> productoCollection;

    public Categorias() {
    }

    public Categorias(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Categorias(Integer codCategoria, String nombreCategoria, String tipo) {
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombreCategoria;
        this.tipo = tipo;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventanas.Categorias[ codCategoria=" + codCategoria + " ]";
    }
    
}
