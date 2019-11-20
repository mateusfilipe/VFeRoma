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
import modelo.Instituto;
import modelo.Lixeira;
import modelo.TipoLixo;

/**
 *
 * @author marco
 */
public class LixeiraJpaController implements Serializable {

    public LixeiraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lixeira lixeira) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Instituto insitutoIdInstituto = lixeira.getInsitutoIdInstituto();
            if (insitutoIdInstituto != null) {
                insitutoIdInstituto = em.getReference(insitutoIdInstituto.getClass(), insitutoIdInstituto.getIdInstituto());
                lixeira.setInsitutoIdInstituto(insitutoIdInstituto);
            }
            TipoLixo tipoIdTipoLixo = lixeira.getTipoIdTipoLixo();
            if (tipoIdTipoLixo != null) {
                tipoIdTipoLixo = em.getReference(tipoIdTipoLixo.getClass(), tipoIdTipoLixo.getIdTipoLixo());
                lixeira.setTipoIdTipoLixo(tipoIdTipoLixo);
            }
            em.persist(lixeira);
            if (insitutoIdInstituto != null) {
                insitutoIdInstituto.getLixeiraCollection().add(lixeira);
                insitutoIdInstituto = em.merge(insitutoIdInstituto);
            }
            if (tipoIdTipoLixo != null) {
                tipoIdTipoLixo.getLixeiraCollection().add(lixeira);
                tipoIdTipoLixo = em.merge(tipoIdTipoLixo);
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

    public void edit(Lixeira lixeira) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lixeira persistentLixeira = em.find(Lixeira.class, lixeira.getIdLixeira());
            Instituto insitutoIdInstitutoOld = persistentLixeira.getInsitutoIdInstituto();
            Instituto insitutoIdInstitutoNew = lixeira.getInsitutoIdInstituto();
            TipoLixo tipoIdTipoLixoOld = persistentLixeira.getTipoIdTipoLixo();
            TipoLixo tipoIdTipoLixoNew = lixeira.getTipoIdTipoLixo();
            if (insitutoIdInstitutoNew != null) {
                insitutoIdInstitutoNew = em.getReference(insitutoIdInstitutoNew.getClass(), insitutoIdInstitutoNew.getIdInstituto());
                lixeira.setInsitutoIdInstituto(insitutoIdInstitutoNew);
            }
            if (tipoIdTipoLixoNew != null) {
                tipoIdTipoLixoNew = em.getReference(tipoIdTipoLixoNew.getClass(), tipoIdTipoLixoNew.getIdTipoLixo());
                lixeira.setTipoIdTipoLixo(tipoIdTipoLixoNew);
            }
            lixeira = em.merge(lixeira);
            if (insitutoIdInstitutoOld != null && !insitutoIdInstitutoOld.equals(insitutoIdInstitutoNew)) {
                insitutoIdInstitutoOld.getLixeiraCollection().remove(lixeira);
                insitutoIdInstitutoOld = em.merge(insitutoIdInstitutoOld);
            }
            if (insitutoIdInstitutoNew != null && !insitutoIdInstitutoNew.equals(insitutoIdInstitutoOld)) {
                insitutoIdInstitutoNew.getLixeiraCollection().add(lixeira);
                insitutoIdInstitutoNew = em.merge(insitutoIdInstitutoNew);
            }
            if (tipoIdTipoLixoOld != null && !tipoIdTipoLixoOld.equals(tipoIdTipoLixoNew)) {
                tipoIdTipoLixoOld.getLixeiraCollection().remove(lixeira);
                tipoIdTipoLixoOld = em.merge(tipoIdTipoLixoOld);
            }
            if (tipoIdTipoLixoNew != null && !tipoIdTipoLixoNew.equals(tipoIdTipoLixoOld)) {
                tipoIdTipoLixoNew.getLixeiraCollection().add(lixeira);
                tipoIdTipoLixoNew = em.merge(tipoIdTipoLixoNew);
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
                Integer id = lixeira.getIdLixeira();
                if (findLixeira(id) == null) {
                    throw new NonexistentEntityException("The lixeira with id " + id + " no longer exists.");
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
            Lixeira lixeira;
            try {
                lixeira = em.getReference(Lixeira.class, id);
                lixeira.getIdLixeira();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lixeira with id " + id + " no longer exists.", enfe);
            }
            Instituto insitutoIdInstituto = lixeira.getInsitutoIdInstituto();
            if (insitutoIdInstituto != null) {
                insitutoIdInstituto.getLixeiraCollection().remove(lixeira);
                insitutoIdInstituto = em.merge(insitutoIdInstituto);
            }
            TipoLixo tipoIdTipoLixo = lixeira.getTipoIdTipoLixo();
            if (tipoIdTipoLixo != null) {
                tipoIdTipoLixo.getLixeiraCollection().remove(lixeira);
                tipoIdTipoLixo = em.merge(tipoIdTipoLixo);
            }
            em.remove(lixeira);
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

    public List<Lixeira> findLixeiraEntities() {
        return findLixeiraEntities(true, -1, -1);
    }

    public List<Lixeira> findLixeiraEntities(int maxResults, int firstResult) {
        return findLixeiraEntities(false, maxResults, firstResult);
    }

    private List<Lixeira> findLixeiraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lixeira.class));
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

    public Lixeira findLixeira(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lixeira.class, id);
        } finally {
            em.close();
        }
    }

    public int getLixeiraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lixeira> rt = cq.from(Lixeira.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
