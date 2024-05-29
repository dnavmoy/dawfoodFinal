/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Models.exceptions.IllegalOrphanException;
import Models.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import metodos.MetodoEntidades;
import models.Categorias;
import models.Producto;
import models.Productosticket;

/**
 *
 * @author daniel
 */
public class ProductoJpaController implements Serializable {


    public static List<Producto> QueryListaProductos() {
        EntityManager em = MetodoEntidades.emf.createEntityManager();
        List<Producto> lista = em.createNamedQuery("Producto.findAll").getResultList();
        return lista;
    }

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
//        if (producto.getProductosticketCollection() == null) {
//            producto.setProductosticketCollection(new ArrayList<Productosticket>());
//        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias codCategoria = producto.getCodCategoria();
            if (codCategoria != null) {
                codCategoria = em.getReference(codCategoria.getClass(), codCategoria.getCodCategoria());
                producto.setCodCategoria(codCategoria);
            }
            Collection<Productosticket> attachedProductosticketCollection = new ArrayList<Productosticket>();
//            for (Productosticket productosticketCollectionProductosticketToAttach : producto.getProductosticketCollection()) {
//                productosticketCollectionProductosticketToAttach = em.getReference(productosticketCollectionProductosticketToAttach.getClass(), productosticketCollectionProductosticketToAttach.getProductosticketPK());
//                attachedProductosticketCollection.add(productosticketCollectionProductosticketToAttach);
//            }
//            producto.setProductosticketCollection(attachedProductosticketCollection);
            em.persist(producto);
            if (codCategoria != null) {
                codCategoria.getProductoCollection().add(producto);
                codCategoria = em.merge(codCategoria);
            }
//            for (Productosticket productosticketCollectionProductosticket : producto.getProductosticketCollection()) {
//                Producto oldProductoOfProductosticketCollectionProductosticket = productosticketCollectionProductosticket.getProducto();
//                productosticketCollectionProductosticket.setProducto(producto);
//                productosticketCollectionProductosticket = em.merge(productosticketCollectionProductosticket);
//                if (oldProductoOfProductosticketCollectionProductosticket != null) {
//                    oldProductoOfProductosticketCollectionProductosticket.getProductosticketCollection().remove(productosticketCollectionProductosticket);
//                    oldProductoOfProductosticketCollectionProductosticket = em.merge(oldProductoOfProductosticketCollectionProductosticket);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Categorias codCategoriaOld = persistentProducto.getCodCategoria();
            Categorias codCategoriaNew = producto.getCodCategoria();
//            Collection<Productosticket> productosticketCollectionOld = persistentProducto.getProductosticketCollection();
//            Collection<Productosticket> productosticketCollectionNew = producto.getProductosticketCollection();
            List<String> illegalOrphanMessages = null;
//            for (Productosticket productosticketCollectionOldProductosticket : productosticketCollectionOld) {
//                if (!productosticketCollectionNew.contains(productosticketCollectionOldProductosticket)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Productosticket " + productosticketCollectionOldProductosticket + " since its producto field is not nullable.");
//                }
//            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codCategoriaNew != null) {
                codCategoriaNew = em.getReference(codCategoriaNew.getClass(), codCategoriaNew.getCodCategoria());
                producto.setCodCategoria(codCategoriaNew);
            }
            Collection<Productosticket> attachedProductosticketCollectionNew = new ArrayList<Productosticket>();
//            for (Productosticket productosticketCollectionNewProductosticketToAttach : productosticketCollectionNew) {
//                productosticketCollectionNewProductosticketToAttach = em.getReference(productosticketCollectionNewProductosticketToAttach.getClass(), productosticketCollectionNewProductosticketToAttach.getProductosticketPK());
//                attachedProductosticketCollectionNew.add(productosticketCollectionNewProductosticketToAttach);
//            }
//            productosticketCollectionNew = attachedProductosticketCollectionNew;
//            producto.setProductosticketCollection(productosticketCollectionNew);
            producto = em.merge(producto);
            if (codCategoriaOld != null && !codCategoriaOld.equals(codCategoriaNew)) {
                codCategoriaOld.getProductoCollection().remove(producto);
                codCategoriaOld = em.merge(codCategoriaOld);
            }
            if (codCategoriaNew != null && !codCategoriaNew.equals(codCategoriaOld)) {
                codCategoriaNew.getProductoCollection().add(producto);
                codCategoriaNew = em.merge(codCategoriaNew);
            }
//            for (Productosticket productosticketCollectionNewProductosticket : productosticketCollectionNew) {
//                if (!productosticketCollectionOld.contains(productosticketCollectionNewProductosticket)) {
//                    Producto oldProductoOfProductosticketCollectionNewProductosticket = productosticketCollectionNewProductosticket.getProducto();
//                    productosticketCollectionNewProductosticket.setProducto(producto);
//                    productosticketCollectionNewProductosticket = em.merge(productosticketCollectionNewProductosticket);
//                    if (oldProductoOfProductosticketCollectionNewProductosticket != null && !oldProductoOfProductosticketCollectionNewProductosticket.equals(producto)) {
//                        oldProductoOfProductosticketCollectionNewProductosticket.getProductosticketCollection().remove(productosticketCollectionNewProductosticket);
//                        oldProductoOfProductosticketCollectionNewProductosticket = em.merge(oldProductoOfProductosticketCollectionNewProductosticket);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
//            Collection<Productosticket> productosticketCollectionOrphanCheck = producto.getProductosticketCollection();
//            for (Productosticket productosticketCollectionOrphanCheckProductosticket : productosticketCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Productosticket " + productosticketCollectionOrphanCheckProductosticket + " in its productosticketCollection field has a non-nullable producto field.");
//            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categorias codCategoria = producto.getCodCategoria();
            if (codCategoria != null) {
                codCategoria.getProductoCollection().remove(producto);
                codCategoria = em.merge(codCategoria);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
