/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package metodos;

import clases.Carrito;
import models.Producto;
import controllers.ProductoJpaController;
import models.Productosticket;
import controllers.ProductosticketJpaController;
import models.ProductosticketPK;
import models.Ticket;
import controllers.TicketJpaController;
import models.Tpv;
import controllers.TpvJpaController;
import Models.exceptions.NonexistentEntityException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class MetodoEntidades {

 //Ventana inicial tiene el main, llama a la ventana de usuario o la ventana de administracion
    //ventana de usuario inicializa el carrito (con su clase) e importa una lista de productos mediante metodo en Dawfood  trayendo datos de la base de datos,
    //mediante entidad producto -> metodo getlista 
    //se añade y se quita del carro segun metodos, comprobando stock y cuando se procede al pago (otra view de pasarela de pago) y este se realiza correctamente
    //se utilizan los metodos de la entidad producto para cambiar el stock y luego mediante metodos dela entidad ticket se crea un ticket y se generan las lineas de ticket en 
    //productosticket.
    
    //productos ticket tiene una realizacion de muchos a uno ( y ticket la inversa mapeada por idTicket uno a muchos) 
    
  //ventana admin por experimentar un poco (y arrepentirme luego por lo feo) probe a hacer una barra superior de menu, al seleccionar opcion muestra e importa la lista de productos o ticket en el 
    //mismo jtable y muestra los 
    
    
    private static int numPedido=0;
    
    
    
    
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dawfood");    
    private static final ProductoJpaController pc = new ProductoJpaController(emf);
    private static final TicketJpaController tc = new TicketJpaController(emf);
    private static final TpvJpaController tpvc = new TpvJpaController(emf);
    private static final ProductosticketJpaController ptjc = new ProductosticketJpaController(emf);
    
    public static Tpv obtenerTpv(){
        return tpvc.findTpv(1);
    }
    
    public static List<Producto> QueryListaProductos(){
        EntityManager em = emf.createEntityManager();
        List<Producto> lista = em.createNamedQuery("Producto.findAll").getResultList();
        return lista;
        
    }
    
    

    
    public static void crearTicket(double totalPedido,double TotalIva,Tpv tpv,Carrito carrito){
    
        Date date = Date.from(Instant.now());
        
        tc.create(new Ticket(1, numPedido++, 1, date, totalPedido, TotalIva,tpv));
        carrito.getCarrito().forEach((k,v)->{
            try {
                ProductosticketPK ptpk=new ProductosticketPK(k, tc.getTicketCount());
                Productosticket pt=new Productosticket(ptpk, v);
                ptjc.create(pt);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error detalle ticket");
            }
        
        });
    }
    
    public static void borrarProducto(int id){
        
        
        Producto editar= metodos.buscarEnLista(getListaProductos(), id);
        try {
            pc.destroy(editar.getIdProducto());
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "producto no existe");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "producto no existe");
        }
    }
    
    public static void cambiarStock(int cantidad,int id){
        int posicion = metodos.buscarEnListaPosicion(getListaProductos(), id);
        Producto editar = getListaProductos().get(posicion);
        editar.setStock(editar.getStock()-cantidad);
        try {
            pc.edit(editar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setStock(int cantidad,int id){
        int posicion = metodos.buscarEnListaPosicion(getListaProductos(), id);
        Producto editar = getListaProductos().get(posicion);
        editar.setStock(cantidad);
        try {
            pc.edit(editar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public static void editarProducto(Producto p){
        try {
            pc.edit(p);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MetodoEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    
     public static void crearProducto(Producto p){
         pc.create(p);
         
     }
    
    public static List<Producto> getListaProductos(){
        return pc.findProductoEntities();
    }
    
    public static List<Ticket> getListaTickets(){
        return tc.findTicketEntities();
    }
    
    public static List<Productosticket> getListaProductosTicket(){
        return ptjc.findProductosticketEntities();
    }
    
    
    
        
//    public static void QueryUpdateProducto(Producto p){
//        EntityManager em = emf.createEntityManager();
//        
//        //TypedQuery<Producto> consulta = em.createNamedQuery("Producto.findByIdProducto", Producto.class);
//        
//        em.createNamedQuery("Producto.update").
//                setParameter("Descripcion",p.getDescripcion())
//                .setParameter("Precio", p.getPrecio())
//                .setParameter("Iva", p.getIva())
//                .setParameter("Stock", p.getStock())
//                .setParameter("CodCategoria", p)
//                .setParameter("IdProducto", p.getIdProducto());
//                
//                
//        
//        
//    }
    
}
