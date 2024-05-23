/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Ventanas;

import Entidades.Producto;
import Entidades.ProductoJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author daniel
 */
public class DawFoodDanielNavarro {

    // en pedido, buscas por index no por id de producto al añadir quitar etc,
    //esto va a provocar problemas al editar productos-> cambiar por busqueda de idproducto
    //en lista productos
    
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dawfood");
    private static final ProductoJpaController pc = new ProductoJpaController(emf);
    
    
   
    
    public static void mostrarProductos() {
        System.out.println("--------- Listado de Productos -------------");
        pc.findProductoEntities().forEach(System.out::println);
        
        System.out.println("--------------------------------------------");
        
    }
    
    public static List<Producto> getListaProductos(){
        return pc.findProductoEntities();
    }
    
    
    
}
