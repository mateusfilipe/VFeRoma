/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.NonexistentEntityException;
import controle.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import modelo.Feedback;
import modelo.Instituto;

/**
 *
 * @author aluno
 */
public class FeedbackJpaController implements Serializable {

    public FeedbackJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Feedback feedback) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Instituto idInstituto = feedback.getIdInstituto();
            if (idInstituto != null) {
                idInstituto = em.getReference(idInstituto.getClass(), idInstituto.getIdInstituto());
                feedback.setIdInstituto(idInstituto);
            }
            em.persist(feedback);
            if (idInstituto != null) {
                idInstituto.getFeedbackCollection().add(feedback);
                idInstituto = em.merge(idInstituto);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Feedback feedback) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Feedback persistentFeedback = em.find(Feedback.class, feedback.getIdFeedback());
            Instituto idInstitutoOld = persistentFeedback.getIdInstituto();
            Instituto idInstitutoNew = feedback.getIdInstituto();
            if (idInstitutoNew != null) {
                idInstitutoNew = em.getReference(idInstitutoNew.getClass(), idInstitutoNew.getIdInstituto());
                feedback.setIdInstituto(idInstitutoNew);
            }
            feedback = em.merge(feedback);
            if (idInstitutoOld != null && !idInstitutoOld.equals(idInstitutoNew)) {
                idInstitutoOld.getFeedbackCollection().remove(feedback);
                idInstitutoOld = em.merge(idInstitutoOld);
            }
            if (idInstitutoNew != null && !idInstitutoNew.equals(idInstitutoOld)) {
                idInstitutoNew.getFeedbackCollection().add(feedback);
                idInstitutoNew = em.merge(idInstitutoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = feedback.getIdFeedback();
                if (findFeedback(id) == null) {
                    throw new NonexistentEntityException("The feedback with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Feedback feedback;
            try {
                feedback = em.getReference(Feedback.class, id);
                feedback.getIdFeedback();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The feedback with id " + id + " no longer exists.", enfe);
            }
            Instituto idInstituto = feedback.getIdInstituto();
            if (idInstituto != null) {
                idInstituto.getFeedbackCollection().remove(feedback);
                idInstituto = em.merge(idInstituto);
            }
            em.remove(feedback);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Feedback> findFeedbackEntities() {
        return findFeedbackEntities(true, -1, -1);
    }

    public List<Feedback> findFeedbackEntities(int maxResults, int firstResult) {
        return findFeedbackEntities(false, maxResults, firstResult);
    }

    private List<Feedback> findFeedbackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Feedback.class));
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

    public Feedback findFeedback(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Feedback.class, id);
        } finally {
            em.close();
        }
    }

    public int getFeedbackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Feedback> rt = cq.from(Feedback.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
