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
import modelo.Feedback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.Instituto;
import modelo.Lixeira;
import modelo.Usuario;

/**
 *
 * @author marco
 */

public class InstitutoJpaController implements Serializable {

    public InstitutoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instituto instituto) throws RollbackFailureException, Exception {
        if (instituto.getFeedbackCollection() == null) {
            instituto.setFeedbackCollection(new ArrayList<Feedback>());
        }
        if (instituto.getLixeiraCollection() == null) {
            instituto.setLixeiraCollection(new ArrayList<Lixeira>());
        }
        if (instituto.getUsuarioCollection() == null) {
            instituto.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Feedback> attachedFeedbackCollection = new ArrayList<Feedback>();
            for (Feedback feedbackCollectionFeedbackToAttach : instituto.getFeedbackCollection()) {
                feedbackCollectionFeedbackToAttach = em.getReference(feedbackCollectionFeedbackToAttach.getClass(), feedbackCollectionFeedbackToAttach.getIdFeedback());
                attachedFeedbackCollection.add(feedbackCollectionFeedbackToAttach);
            }
            instituto.setFeedbackCollection(attachedFeedbackCollection);
            Collection<Lixeira> attachedLixeiraCollection = new ArrayList<Lixeira>();
            for (Lixeira lixeiraCollectionLixeiraToAttach : instituto.getLixeiraCollection()) {
                lixeiraCollectionLixeiraToAttach = em.getReference(lixeiraCollectionLixeiraToAttach.getClass(), lixeiraCollectionLixeiraToAttach.getIdLixeira());
                attachedLixeiraCollection.add(lixeiraCollectionLixeiraToAttach);
            }
            instituto.setLixeiraCollection(attachedLixeiraCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : instituto.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getLogin());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            instituto.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(instituto);
            for (Feedback feedbackCollectionFeedback : instituto.getFeedbackCollection()) {
                Instituto oldIdInstitutoOfFeedbackCollectionFeedback = feedbackCollectionFeedback.getIdInstituto();
                feedbackCollectionFeedback.setIdInstituto(instituto);
                feedbackCollectionFeedback = em.merge(feedbackCollectionFeedback);
                if (oldIdInstitutoOfFeedbackCollectionFeedback != null) {
                    oldIdInstitutoOfFeedbackCollectionFeedback.getFeedbackCollection().remove(feedbackCollectionFeedback);
                    oldIdInstitutoOfFeedbackCollectionFeedback = em.merge(oldIdInstitutoOfFeedbackCollectionFeedback);
                }
            }
            for (Lixeira lixeiraCollectionLixeira : instituto.getLixeiraCollection()) {
                Instituto oldInsitutoIdInstitutoOfLixeiraCollectionLixeira = lixeiraCollectionLixeira.getInsitutoIdInstituto();
                lixeiraCollectionLixeira.setInsitutoIdInstituto(instituto);
                lixeiraCollectionLixeira = em.merge(lixeiraCollectionLixeira);
                if (oldInsitutoIdInstitutoOfLixeiraCollectionLixeira != null) {
                    oldInsitutoIdInstitutoOfLixeiraCollectionLixeira.getLixeiraCollection().remove(lixeiraCollectionLixeira);
                    oldInsitutoIdInstitutoOfLixeiraCollectionLixeira = em.merge(oldInsitutoIdInstitutoOfLixeiraCollectionLixeira);
                }
            }
            for (Usuario usuarioCollectionUsuario : instituto.getUsuarioCollection()) {
                Instituto oldInstitutoIdInstitutoOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getInstitutoIdInstituto();
                usuarioCollectionUsuario.setInstitutoIdInstituto(instituto);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldInstitutoIdInstitutoOfUsuarioCollectionUsuario != null) {
                    oldInstitutoIdInstitutoOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldInstitutoIdInstitutoOfUsuarioCollectionUsuario = em.merge(oldInstitutoIdInstitutoOfUsuarioCollectionUsuario);
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

    public void edit(Instituto instituto) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Instituto persistentInstituto = em.find(Instituto.class, instituto.getIdInstituto());
            Collection<Feedback> feedbackCollectionOld = persistentInstituto.getFeedbackCollection();
            Collection<Feedback> feedbackCollectionNew = instituto.getFeedbackCollection();
            Collection<Lixeira> lixeiraCollectionOld = persistentInstituto.getLixeiraCollection();
            Collection<Lixeira> lixeiraCollectionNew = instituto.getLixeiraCollection();
            Collection<Usuario> usuarioCollectionOld = persistentInstituto.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = instituto.getUsuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Lixeira lixeiraCollectionOldLixeira : lixeiraCollectionOld) {
                if (!lixeiraCollectionNew.contains(lixeiraCollectionOldLixeira)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lixeira " + lixeiraCollectionOldLixeira + " since its insitutoIdInstituto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Feedback> attachedFeedbackCollectionNew = new ArrayList<Feedback>();
            for (Feedback feedbackCollectionNewFeedbackToAttach : feedbackCollectionNew) {
                feedbackCollectionNewFeedbackToAttach = em.getReference(feedbackCollectionNewFeedbackToAttach.getClass(), feedbackCollectionNewFeedbackToAttach.getIdFeedback());
                attachedFeedbackCollectionNew.add(feedbackCollectionNewFeedbackToAttach);
            }
            feedbackCollectionNew = attachedFeedbackCollectionNew;
            instituto.setFeedbackCollection(feedbackCollectionNew);
            Collection<Lixeira> attachedLixeiraCollectionNew = new ArrayList<Lixeira>();
            for (Lixeira lixeiraCollectionNewLixeiraToAttach : lixeiraCollectionNew) {
                lixeiraCollectionNewLixeiraToAttach = em.getReference(lixeiraCollectionNewLixeiraToAttach.getClass(), lixeiraCollectionNewLixeiraToAttach.getIdLixeira());
                attachedLixeiraCollectionNew.add(lixeiraCollectionNewLixeiraToAttach);
            }
            lixeiraCollectionNew = attachedLixeiraCollectionNew;
            instituto.setLixeiraCollection(lixeiraCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getLogin());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            instituto.setUsuarioCollection(usuarioCollectionNew);
            instituto = em.merge(instituto);
            for (Feedback feedbackCollectionOldFeedback : feedbackCollectionOld) {
                if (!feedbackCollectionNew.contains(feedbackCollectionOldFeedback)) {
                    feedbackCollectionOldFeedback.setIdInstituto(null);
                    feedbackCollectionOldFeedback = em.merge(feedbackCollectionOldFeedback);
                }
            }
            for (Feedback feedbackCollectionNewFeedback : feedbackCollectionNew) {
                if (!feedbackCollectionOld.contains(feedbackCollectionNewFeedback)) {
                    Instituto oldIdInstitutoOfFeedbackCollectionNewFeedback = feedbackCollectionNewFeedback.getIdInstituto();
                    feedbackCollectionNewFeedback.setIdInstituto(instituto);
                    feedbackCollectionNewFeedback = em.merge(feedbackCollectionNewFeedback);
                    if (oldIdInstitutoOfFeedbackCollectionNewFeedback != null && !oldIdInstitutoOfFeedbackCollectionNewFeedback.equals(instituto)) {
                        oldIdInstitutoOfFeedbackCollectionNewFeedback.getFeedbackCollection().remove(feedbackCollectionNewFeedback);
                        oldIdInstitutoOfFeedbackCollectionNewFeedback = em.merge(oldIdInstitutoOfFeedbackCollectionNewFeedback);
                    }
                }
            }
            for (Lixeira lixeiraCollectionNewLixeira : lixeiraCollectionNew) {
                if (!lixeiraCollectionOld.contains(lixeiraCollectionNewLixeira)) {
                    Instituto oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira = lixeiraCollectionNewLixeira.getInsitutoIdInstituto();
                    lixeiraCollectionNewLixeira.setInsitutoIdInstituto(instituto);
                    lixeiraCollectionNewLixeira = em.merge(lixeiraCollectionNewLixeira);
                    if (oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira != null && !oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira.equals(instituto)) {
                        oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira.getLixeiraCollection().remove(lixeiraCollectionNewLixeira);
                        oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira = em.merge(oldInsitutoIdInstitutoOfLixeiraCollectionNewLixeira);
                    }
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setInstitutoIdInstituto(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Instituto oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getInstitutoIdInstituto();
                    usuarioCollectionNewUsuario.setInstitutoIdInstituto(instituto);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario != null && !oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario.equals(instituto)) {
                        oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario = em.merge(oldInstitutoIdInstitutoOfUsuarioCollectionNewUsuario);
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
                Integer id = instituto.getIdInstituto();
                if (findInstituto(id) == null) {
                    throw new NonexistentEntityException("The instituto with id " + id + " no longer exists.");
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
            Instituto instituto;
            try {
                instituto = em.getReference(Instituto.class, id);
                instituto.getIdInstituto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instituto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Lixeira> lixeiraCollectionOrphanCheck = instituto.getLixeiraCollection();
            for (Lixeira lixeiraCollectionOrphanCheckLixeira : lixeiraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Instituto (" + instituto + ") cannot be destroyed since the Lixeira " + lixeiraCollectionOrphanCheckLixeira + " in its lixeiraCollection field has a non-nullable insitutoIdInstituto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Feedback> feedbackCollection = instituto.getFeedbackCollection();
            for (Feedback feedbackCollectionFeedback : feedbackCollection) {
                feedbackCollectionFeedback.setIdInstituto(null);
                feedbackCollectionFeedback = em.merge(feedbackCollectionFeedback);
            }
            Collection<Usuario> usuarioCollection = instituto.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setInstitutoIdInstituto(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(instituto);
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

    public List<Instituto> findInstitutoEntities() {
        return findInstitutoEntities(true, -1, -1);
    }

    public List<Instituto> findInstitutoEntities(int maxResults, int firstResult) {
        return findInstitutoEntities(false, maxResults, firstResult);
    }

    private List<Instituto> findInstitutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instituto.class));
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

    public Instituto findInstituto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instituto.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instituto> rt = cq.from(Instituto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
