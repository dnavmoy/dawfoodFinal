/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Models.exceptions.NonexistentEntityException;
import Models.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metodos.MetodoEntidades;
import models.Producto;
import models.Productosticket;
import models.ProductosticketPK;
import models.Ticket;

/**
 *
 * @author daniel
 */
public class ProductosticketJpaController implements Serializable {

    public static long productoEnTicket(int idproducto) {
        EntityManager em = MetodoEntidades.emf.createEntityManager();
        long cuenta = (long) em.createNamedQuery("Productosticket.findsienTicket").setParameter("idProducto", idproducto).getSingleResult();
        return cuenta;
    }

    public ProductosticketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productosticket productosticket) throws PreexistingEntityException, Exception {
        if (productosticket.getProductosticketPK() == null) {
            productosticket.setProductosticketPK(new ProductosticketPK());
        }
  
        productosticket.getProductosticketPK().setIdProducto(productosticket.getProducto().getIdProducto());
        productosticket.getProductosticketPK().setIdTicket(productosticket.getTicket().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = productosticket.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                productosticket.setProducto(producto);
            }
            Ticket ticket = productosticket.getTicket();
            if (ticket != null) {
                ticket = em.getReference(ticket.getClass(), ticket.getIdTicket());
                productosticket.setTicket(ticket);
            }
            //em.persist(productosticket);
            //if (producto != null) {
            //    producto.getProductosticketCollection().add(productosticket);
            //    producto = em.merge(producto);
            //}
            if (ticket != null) {
                ticket.getProductosticketCollection().add(productosticket);
                ticket = em.merge(ticket);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductosticket(productosticket.getProductosticketPK()) != null) {
                throw new PreexistingEntityException("Productosticket " + productosticket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productosticket productosticket) throws NonexistentEntityException, Exception {
        productosticket.getProductosticketPK().setIdProducto(productosticket.getProducto().getIdProducto());
        productosticket.getProductosticketPK().setIdTicket(productosticket.getTicket().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productosticket persistentProductosticket = em.find(Productosticket.class, productosticket.getProductosticketPK());
            Producto productoOld = persistentProductosticket.getProducto();
            Producto productoNew = productosticket.getProducto();
            Ticket ticketOld = persistentProductosticket.getTicket();
            Ticket ticketNew = productosticket.getTicket();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                productosticket.setProducto(productoNew);
            }
            if (ticketNew != null) {
                ticketNew = em.getReference(ticketNew.getClass(), ticketNew.getIdTicket());
                productosticket.setTicket(ticketNew);
            }
            //productosticket = em.merge(productosticket);
            //if (productoOld != null && !productoOld.equals(productoNew)) {
            //    productoOld.getProductosticketCollection().remove(productosticket);
            //    productoOld = em.merge(productoOld);
            //}
            //if (productoNew != null && !productoNew.equals(productoOld)) {
//                productoNew.getProductosticketCollection().add(productosticket);
//                productoNew = em.merge(productoNew);
//            }
//            if (ticketOld != null && !ticketOld.equals(ticketNew)) {
//                ticketOld.getProductosticketCollection().remove(productosticket);
//                ticketOld = em.merge(ticketOld);
//            }
//            if (ticketNew != null && !ticketNew.equals(ticketOld)) {
//                ticketNew.getProductosticketCollection().add(productosticket);
//                ticketNew = em.merge(ticketNew);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductosticketPK id = productosticket.getProductosticketPK();
                if (findProductosticket(id) == null) {
                    throw new NonexistentEntityException("The productosticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductosticketPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productosticket productosticket;
            try {
                productosticket = em.getReference(Productosticket.class, id);
                productosticket.getProductosticketPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosticket with id " + id + " no longer exists.", enfe);
            }
//            Producto producto = productosticket.getProducto();
//            if (producto != null) {
//                producto.getProductosticketCollection().remove(productosticket);
//                producto = em.merge(producto);
//            }
            Ticket ticket = productosticket.getTicket();
            if (ticket != null) {
                ticket.getProductosticketCollection().remove(productosticket);
                ticket = em.merge(ticket);
            }
            em.remove(productosticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productosticket> findProductosticketEntities() {
        return findProductosticketEntities(true, -1, -1);
    }

    public List<Productosticket> findProductosticketEntities(int maxResults, int firstResult) {
        return findProductosticketEntities(false, maxResults, firstResult);
    }

    private List<Productosticket> findProductosticketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productosticket.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Productosticket findProductosticket(ProductosticketPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productosticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosticketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productosticket> rt = cq.from(Productosticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
