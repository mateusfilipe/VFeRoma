/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.IllegalOrphanException;
import controle.exceptions.NonexistentEntityException;
import controle.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Lixeira;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.TipoLixo;

/**
 *
 * @author marco
 */
public class TipoLixoJpaController implements Serializable {

    public TipoLixoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoLixo tipoLixo) throws RollbackFailureException, Exception {
        if (tipoLixo.getLixeiraCollection() == null) {
            tipoLixo.setLixeiraCollection(new ArrayList<Lixeira>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Lixeira> attachedLixeiraCollection = new ArrayList<Lixeira>();
            for (Lixeira lixeiraCollectionLixeiraToAttach : tipoLixo.getLixeiraCollection()) {
                lixeiraCollectionLixeiraToAttach = em.getReference(lixeiraCollectionLixeiraToAttach.getClass(), lixeiraCollectionLixeiraToAttach.getIdLixeira());
                attachedLixeiraCollection.add(lixeiraCollectionLixeiraToAttach);
            }
            tipoLixo.setLixeiraCollection(attachedLixeiraCollection);
            em.persist(tipoLixo);
            for (Lixeira lixeiraCollectionLixeira : tipoLixo.getLixeiraCollection()) {
                TipoLixo oldTipoIdTipoLixoOfLixeiraCollectionLixeira = lixeiraCollectionLixeira.getTipoIdTipoLixo();
                lixeiraCollectionLixeira.setTipoIdTipoLixo(tipoLixo);
                lixeiraCollectionLixeira = em.merge(lixeiraCollectionLixeira);
                if (oldTipoIdTipoLixoOfLixeiraCollectionLixeira != null) {
                    oldTipoIdTipoLixoOfLixeiraCollectionLixeira.getLixeiraCollection().remove(lixeiraCollectionLixeira);
                    oldTipoIdTipoLixoOfLixeiraCollectionLixeira = em.merge(oldTipoIdTipoLixoOfLixeiraCollectionLixeira);
                }
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

    public void edit(TipoLixo tipoLixo) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoLixo persistentTipoLixo = em.find(TipoLixo.class, tipoLixo.getIdTipoLixo());
            Collection<Lixeira> lixeiraCollectionOld = persistentTipoLixo.getLixeiraCollection();
            Collection<Lixeira> lixeiraCollectionNew = tipoLixo.getLixeiraCollection();
            List<String> illegalOrphanMessages = null;
            for (Lixeira lixeiraCollectionOldLixeira : lixeiraCollectionOld) {
                if (!lixeiraCollectionNew.contains(lixeiraCollectionOldLixeira)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lixeira " + lixeiraCollectionOldLixeira + " since its tipoIdTipoLixo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Lixeira> attachedLixeiraCollectionNew = new ArrayList<Lixeira>();
            for (Lixeira lixeiraCollectionNewLixeiraToAttach : lixeiraCollectionNew) {
                lixeiraCollectionNewLixeiraToAttach = em.getReference(lixeiraCollectionNewLixeiraToAttach.getClass(), lixeiraCollectionNewLixeiraToAttach.getIdLixeira());
                attachedLixeiraCollectionNew.add(lixeiraCollectionNewLixeiraToAttach);
            }
            lixeiraCollectionNew = attachedLixeiraCollectionNew;
            tipoLixo.setLixeiraCollection(lixeiraCollectionNew);
            tipoLixo = em.merge(tipoLixo);
            for (Lixeira lixeiraCollectionNewLixeira : lixeiraCollectionNew) {
                if (!lixeiraCollectionOld.contains(lixeiraCollectionNewLixeira)) {
                    TipoLixo oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira = lixeiraCollectionNewLixeira.getTipoIdTipoLixo();
                    lixeiraCollectionNewLixeira.setTipoIdTipoLixo(tipoLixo);
                    lixeiraCollectionNewLixeira = em.merge(lixeiraCollectionNewLixeira);
                    if (oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira != null && !oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira.equals(tipoLixo)) {
                        oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira.getLixeiraCollection().remove(lixeiraCollectionNewLixeira);
                        oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira = em.merge(oldTipoIdTipoLixoOfLixeiraCollectionNewLixeira);
                    }
                }
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
                Integer id = tipoLixo.getIdTipoLixo();
                if (findTipoLixo(id) == null) {
                    throw new NonexistentEntityException("The tipoLixo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoLixo tipoLixo;
            try {
                tipoLixo = em.getReference(TipoLixo.class, id);
                tipoLixo.getIdTipoLixo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoLixo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Lixeira> lixeiraCollectionOrphanCheck = tipoLixo.getLixeiraCollection();
            for (Lixeira lixeiraCollectionOrphanCheckLixeira : lixeiraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoLixo (" + tipoLixo + ") cannot be destroyed since the Lixeira " + lixeiraCollectionOrphanCheckLixeira + " in its lixeiraCollection field has a non-nullable tipoIdTipoLixo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoLixo);
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

    public List<TipoLixo> findTipoLixoEntities() {
        return findTipoLixoEntities(true, -1, -1);
    }

    public List<TipoLixo> findTipoLixoEntities(int maxResults, int firstResult) {
        return findTipoLixoEntities(false, maxResults, firstResult);
    }

    private List<TipoLixo> findTipoLixoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoLixo.class));
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

    public TipoLixo findTipoLixo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoLixo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoLixoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoLixo> rt = cq.from(TipoLixo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
