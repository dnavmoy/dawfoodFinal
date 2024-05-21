/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Entidades.exceptions.IllegalOrphanException;
import Entidades.exceptions.NonexistentEntityException;
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

/**
 *
 * @author daniel
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getProductosticketCollection() == null) {
            ticket.setProductosticketCollection(new ArrayList<Productosticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv codigoTpv = ticket.getCodigoTpv();
            if (codigoTpv != null) {
                codigoTpv = em.getReference(codigoTpv.getClass(), codigoTpv.getCodigoTpv());
                ticket.setCodigoTpv(codigoTpv);
            }
            Collection<Productosticket> attachedProductosticketCollection = new ArrayList<Productosticket>();
            for (Productosticket productosticketCollectionProductosticketToAttach : ticket.getProductosticketCollection()) {
                productosticketCollectionProductosticketToAttach = em.getReference(productosticketCollectionProductosticketToAttach.getClass(), productosticketCollectionProductosticketToAttach.getProductosticketPK());
                attachedProductosticketCollection.add(productosticketCollectionProductosticketToAttach);
            }
            ticket.setProductosticketCollection(attachedProductosticketCollection);
            em.persist(ticket);
            if (codigoTpv != null) {
                codigoTpv.getTicketCollection().add(ticket);
                codigoTpv = em.merge(codigoTpv);
            }
            for (Productosticket productosticketCollectionProductosticket : ticket.getProductosticketCollection()) {
                Ticket oldTicketOfProductosticketCollectionProductosticket = productosticketCollectionProductosticket.getTicket();
                productosticketCollectionProductosticket.setTicket(ticket);
                productosticketCollectionProductosticket = em.merge(productosticketCollectionProductosticket);
                if (oldTicketOfProductosticketCollectionProductosticket != null) {
                    oldTicketOfProductosticketCollectionProductosticket.getProductosticketCollection().remove(productosticketCollectionProductosticket);
                    oldTicketOfProductosticketCollectionProductosticket = em.merge(oldTicketOfProductosticketCollectionProductosticket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Tpv codigoTpvOld = persistentTicket.getCodigoTpv();
            Tpv codigoTpvNew = ticket.getCodigoTpv();
            Collection<Productosticket> productosticketCollectionOld = persistentTicket.getProductosticketCollection();
            Collection<Productosticket> productosticketCollectionNew = ticket.getProductosticketCollection();
            List<String> illegalOrphanMessages = null;
            for (Productosticket productosticketCollectionOldProductosticket : productosticketCollectionOld) {
                if (!productosticketCollectionNew.contains(productosticketCollectionOldProductosticket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productosticket " + productosticketCollectionOldProductosticket + " since its ticket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigoTpvNew != null) {
                codigoTpvNew = em.getReference(codigoTpvNew.getClass(), codigoTpvNew.getCodigoTpv());
                ticket.setCodigoTpv(codigoTpvNew);
            }
            Collection<Productosticket> attachedProductosticketCollectionNew = new ArrayList<Productosticket>();
            for (Productosticket productosticketCollectionNewProductosticketToAttach : productosticketCollectionNew) {
                productosticketCollectionNewProductosticketToAttach = em.getReference(productosticketCollectionNewProductosticketToAttach.getClass(), productosticketCollectionNewProductosticketToAttach.getProductosticketPK());
                attachedProductosticketCollectionNew.add(productosticketCollectionNewProductosticketToAttach);
            }
            productosticketCollectionNew = attachedProductosticketCollectionNew;
            ticket.setProductosticketCollection(productosticketCollectionNew);
            ticket = em.merge(ticket);
            if (codigoTpvOld != null && !codigoTpvOld.equals(codigoTpvNew)) {
                codigoTpvOld.getTicketCollection().remove(ticket);
                codigoTpvOld = em.merge(codigoTpvOld);
            }
            if (codigoTpvNew != null && !codigoTpvNew.equals(codigoTpvOld)) {
                codigoTpvNew.getTicketCollection().add(ticket);
                codigoTpvNew = em.merge(codigoTpvNew);
            }
            for (Productosticket productosticketCollectionNewProductosticket : productosticketCollectionNew) {
                if (!productosticketCollectionOld.contains(productosticketCollectionNewProductosticket)) {
                    Ticket oldTicketOfProductosticketCollectionNewProductosticket = productosticketCollectionNewProductosticket.getTicket();
                    productosticketCollectionNewProductosticket.setTicket(ticket);
                    productosticketCollectionNewProductosticket = em.merge(productosticketCollectionNewProductosticket);
                    if (oldTicketOfProductosticketCollectionNewProductosticket != null && !oldTicketOfProductosticketCollectionNewProductosticket.equals(ticket)) {
                        oldTicketOfProductosticketCollectionNewProductosticket.getProductosticketCollection().remove(productosticketCollectionNewProductosticket);
                        oldTicketOfProductosticketCollectionNewProductosticket = em.merge(oldTicketOfProductosticketCollectionNewProductosticket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
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
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Productosticket> productosticketCollectionOrphanCheck = ticket.getProductosticketCollection();
            for (Productosticket productosticketCollectionOrphanCheckProductosticket : productosticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the Productosticket " + productosticketCollectionOrphanCheckProductosticket + " in its productosticketCollection field has a non-nullable ticket field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tpv codigoTpv = ticket.getCodigoTpv();
            if (codigoTpv != null) {
                codigoTpv.getTicketCollection().remove(ticket);
                codigoTpv = em.merge(codigoTpv);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
