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
import modelo.Mudanca;
import modelo.Usuario;

/**
 *
 * @author marco
 */
public class MudancaJpaController implements Serializable {

    public MudancaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mudanca mudanca) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario idAdm = mudanca.getIdAdm();
            if (idAdm != null) {
                idAdm = em.getReference(idAdm.getClass(), idAdm.getLogin());
                mudanca.setIdAdm(idAdm);
            }
            em.persist(mudanca);
            if (idAdm != null) {
                idAdm.getMudancaCollection().add(mudanca);
                idAdm = em.merge(idAdm);
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

    public void edit(Mudanca mudanca) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Mudanca persistentMudanca = em.find(Mudanca.class, mudanca.getIdMudanca());
            Usuario idAdmOld = persistentMudanca.getIdAdm();
            Usuario idAdmNew = mudanca.getIdAdm();
            if (idAdmNew != null) {
                idAdmNew = em.getReference(idAdmNew.getClass(), idAdmNew.getLogin());
                mudanca.setIdAdm(idAdmNew);
            }
            mudanca = em.merge(mudanca);
            if (idAdmOld != null && !idAdmOld.equals(idAdmNew)) {
                idAdmOld.getMudancaCollection().remove(mudanca);
                idAdmOld = em.merge(idAdmOld);
            }
            if (idAdmNew != null && !idAdmNew.equals(idAdmOld)) {
                idAdmNew.getMudancaCollection().add(mudanca);
                idAdmNew = em.merge(idAdmNew);
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
                Integer id = mudanca.getIdMudanca();
                if (findMudanca(id) == null) {
                    throw new NonexistentEntityException("The mudanca with id " + id + " no longer exists.");
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
            Mudanca mudanca;
            try {
                mudanca = em.getReference(Mudanca.class, id);
                mudanca.getIdMudanca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mudanca with id " + id + " no longer exists.", enfe);
            }
            Usuario idAdm = mudanca.getIdAdm();
            if (idAdm != null) {
                idAdm.getMudancaCollection().remove(mudanca);
                idAdm = em.merge(idAdm);
            }
            em.remove(mudanca);
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

    public List<Mudanca> findMudancaEntities() {
        return findMudancaEntities(true, -1, -1);
    }

    public List<Mudanca> findMudancaEntities(int maxResults, int firstResult) {
        return findMudancaEntities(false, maxResults, firstResult);
    }

    private List<Mudanca> findMudancaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mudanca.class));
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

    public Mudanca findMudanca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mudanca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMudancaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mudanca> rt = cq.from(Mudanca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
