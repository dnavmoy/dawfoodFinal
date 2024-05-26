/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package views;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
public class DawFoodDanielNavarro {

    // en pedido, buscas por index no por id de producto al añadir quitar etc,
    //esto va a provocar problemas al editar productos-> cambiar por busqueda de idproducto
    //en lista productos
    
    //no pemitir borrar update de productos en tickets!
    
    //usa algun named querys,listo, buscar productos en tickets !!
    
    private static int numPedido=0;
    
    public static Producto buscarEnLista(List<Producto> lista,int id){
        Collections.sort(lista, (k1, k2) -> Integer.compare(k1.getIdProducto(), k2.getIdProducto()));
        Producto x = new Producto(id);
        int posicion = Collections.binarySearch(lista,
                                x,
                                ((k1, k2) -> Integer.compare(k1.getIdProducto(), k2.getIdProducto())));
        return lista.get(posicion);
    }
    
    public static int buscarEnListaPosicion(List<Producto> lista,int id){
        Collections.sort(lista, (k1, k2) -> Integer.compare(k1.getIdProducto(), k2.getIdProducto()));
        Producto x = new Producto(id);
        int posicion = Collections.binarySearch(lista,
                                x,
                                ((k1, k2) -> Integer.compare(k1.getIdProducto(), k2.getIdProducto())));
        return posicion;
    }
    
    public static boolean pasarelaPago(double cantidad,int numero,LocalDate fecha, int cvv) {

       
        boolean correcto = false;
        //menu para hacer el pago- ahora no es necesario pero se puede añadir tarjetas en un futuro
        
                    //al iniciar el pago añadimos la lista de tarjetas para poder comprobarlas
                    ArrayList<Tarjeta> listaTarjetas = tarjetas();
                    //si el carrito esta vacio no llegamos a intentar el pago
                    if (cantidad<=0) {
                        JOptionPane.showMessageDialog(null, "Carrito esta vacio");
                    } else {
                        //ordenar las tarjetas para poder buscarlas
                        Collections.sort(listaTarjetas, (k1, k2) -> Integer.compare(k1.getNumTarjeta(), k2.getNumTarjeta()));
                        //tarjeta ejemplo para buscar 
                        Tarjeta x = new Tarjeta(numero, 001, LocalDate.now(), 0);
                        
                        int posicion = Collections.binarySearch(listaTarjetas,
                                x,
                                ((k1, k2) -> Integer.compare(k1.getNumTarjeta(), k2.getNumTarjeta())));
                        //si encuentra numero de tarjeta 
                        if (posicion >= 0) {
                            boolean cvvFecha = true;
                            //si la encuentra pregunta cvv y fecha vencimiento
                            do {
                               

                                //si es disitno a tres digitos da error
                                if (cvv < 0 || cvv > 999) {
                                    JOptionPane.showMessageDialog(null, "cvv debe ser de tres cifras");

                                } else {
                                    cvvFecha = false;
                                }
                                //repite mientras no tenga formato correcto
                            } while (cvvFecha);
                            //luego probamos si el cvv y la fecha es igual a la de la tarjeta
                            if (listaTarjetas.get(posicion).getCvv() == cvv && listaTarjetas.get(posicion).getFechaVencimiento().equals(fecha)) {
                                //comprobamos el saldo de la tarjeta si es suficiente segun el carrito mandado
                                if (listaTarjetas.get(posicion).getSaldo() >= cantidad) {
                                    listaTarjetas.get(posicion).setSaldo(listaTarjetas.get(posicion).getSaldo() - cantidad);
                                   
                                    
                                    correcto = true;
                                   
                                    
                                    //cambiamos correcto a true para que el pago haya sido correcto
                                } else {
                                    JOptionPane.showMessageDialog(null, "saldo insuficiente");
                                }

                            } else {

                                JOptionPane.showMessageDialog(null, "error en cvv o  fecha");
                            }

                        } else {
                            //si no encuentra tarjeta muestra mensaje error
                            JOptionPane.showMessageDialog(null, "no existe la tarjeta");
                        }
                    }

        

        

        

        return correcto;
    }
    
    
    public static ArrayList<Tarjeta> tarjetas() {
        Tarjeta t1 = new Tarjeta(1234, 123, LocalDate.of(2025, 12, 30), 1);
        Tarjeta t2 = new Tarjeta(4321, 321, LocalDate.of(2024, 12, 30), 200);
        Tarjeta t3 = new Tarjeta(1357, 135, LocalDate.of(2026, 12, 30), 12000);
        ArrayList<Tarjeta> listaTarjeta = new ArrayList();
        listaTarjeta.add(t1);
        listaTarjeta.add(t2);
        listaTarjeta.add(t3);
        return listaTarjeta;
    }
    
    
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dawfood");    
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
    
    public static List<Productosticket> queryListaProductosTicket(int idTicket){
        EntityManager em = emf.createEntityManager();
        Ticket t = em.find(Ticket.class, idTicket);
        List<Productosticket> lista =(List<Productosticket>) t.getProductosticketCollection();
        return lista;
    }
    
    public static long productoEnTicket(int idproducto){
        EntityManager em = emf.createEntityManager();
        long cuenta = ((long)em.createNamedQuery("Productosticket.findsienTicket").setParameter("idProducto", idproducto).getSingleResult());
        return cuenta;
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
    
    public static void crearTicket(double totalPedido,double TotalIva,Tpv tpv,Carrito carrito){
    
        Date date = Date.from(Instant.now());
        
        tc.create(new Ticket(1, numPedido++, 1, date, totalPedido, TotalIva,tpv));
        carrito.getCarrito().forEach((k,v)->{
            try {
                ProductosticketPK prueba=new ProductosticketPK(k, tc.getTicketCount());
                Productosticket pruebaDos=new Productosticket(prueba, v);
                ptjc.create(pruebaDos);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error detalle ticket");
            }
        
        });
    }
    
    public static void borrarProducto(int id){
        
        
        Producto editar= buscarEnLista(getListaProductos(), id);
        try {
            pc.destroy(editar.getIdProducto());
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "producto no existe");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "producto no existe");
        }
    }
    
    public static void cambiarStock(int cantidad,int id){
        int posicion = buscarEnListaPosicion(getListaProductos(), id);
        Producto editar = getListaProductos().get(posicion);
        editar.setStock(editar.getStock()-cantidad);
        try {
            pc.edit(editar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setStock(int cantidad,int id){
        int posicion = buscarEnListaPosicion(getListaProductos(), id);
        Producto editar = getListaProductos().get(posicion);
        editar.setStock(cantidad);
        try {
            pc.edit(editar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public static void editarProducto(Producto p){
        try {
            pc.edit(p);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DawFoodDanielNavarro.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
