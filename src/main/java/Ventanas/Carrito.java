/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author Dan_n
 */
public class Carrito {
    
    
    private Map<Integer,Integer> carrito;

    public Carrito(Map<Integer, Integer> carrito) {
        this.carrito = carrito;
    }
    
    public Carrito() {
        this.carrito = new TreeMap();
    }

    public Map<Integer,Integer> getCarrito() {
        return carrito;
    }

    public void setCarrito(Map<Integer,Integer> carrito) {
        this.carrito = carrito;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.carrito);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carrito other = (Carrito) obj;
        return Objects.equals(this.carrito, other.carrito);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrito{");
        sb.append("carrito=").append(carrito);
        sb.append('}');
        return sb.toString();
    }
    
    
}
