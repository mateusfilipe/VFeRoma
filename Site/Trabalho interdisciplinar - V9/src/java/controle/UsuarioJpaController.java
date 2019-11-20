/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.IllegalOrphanException;
import controle.exceptions.NonexistentEntityException;
import controle.exceptions.PreexistingEntityException;
import controle.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Instituto;
import modelo.Mudanca;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.Usuario;

/**
 *
 * @author aluno
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (usuario.getMudancaCollection() == null) {
            usuario.setMudancaCollection(new ArrayList<Mudanca>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Instituto institutoIdInstituto = usuario.getInstitutoIdInstituto();
            if (institutoIdInstituto != null) {
                institutoIdInstituto = em.getReference(institutoIdInstituto.getClass(), institutoIdInstituto.getIdInstituto());
                usuario.setInstitutoIdInstituto(institutoIdInstituto);
            }
            Collection<Mudanca> attachedMudancaCollection = new ArrayList<Mudanca>();
            for (Mudanca mudancaCollectionMudancaToAttach : usuario.getMudancaCollection()) {
                mudancaCollectionMudancaToAttach = em.getReference(mudancaCollectionMudancaToAttach.getClass(), mudancaCollectionMudancaToAttach.getIdMudanca());
                attachedMudancaCollection.add(mudancaCollectionMudancaToAttach);
            }
            usuario.setMudancaCollection(attachedMudancaCollection);
            em.persist(usuario);
            if (institutoIdInstituto != null) {
                institutoIdInstituto.getUsuarioCollection().add(usuario);
                institutoIdInstituto = em.merge(institutoIdInstituto);
            }
            for (Mudanca mudancaCollectionMudanca : usuario.getMudancaCollection()) {
                Usuario oldIdAdmOfMudancaCollectionMudanca = mudancaCollectionMudanca.getIdAdm();
                mudancaCollectionMudanca.setIdAdm(usuario);
                mudancaCollectionMudanca = em.merge(mudancaCollectionMudanca);
                if (oldIdAdmOfMudancaCollectionMudanca != null) {
                    oldIdAdmOfMudancaCollectionMudanca.getMudancaCollection().remove(mudancaCollectionMudanca);
                    oldIdAdmOfMudancaCollectionMudanca = em.merge(oldIdAdmOfMudancaCollectionMudanca);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsuario(usuario.getLogin()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getLogin());
            Instituto institutoIdInstitutoOld = persistentUsuario.getInstitutoIdInstituto();
            Instituto institutoIdInstitutoNew = usuario.getInstitutoIdInstituto();
            Collection<Mudanca> mudancaCollectionOld = persistentUsuario.getMudancaCollection();
            Collection<Mudanca> mudancaCollectionNew = usuario.getMudancaCollection();
            List<String> illegalOrphanMessages = null;
            for (Mudanca mudancaCollectionOldMudanca : mudancaCollectionOld) {
                if (!mudancaCollectionNew.contains(mudancaCollectionOldMudanca)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mudanca " + mudancaCollectionOldMudanca + " since its idAdm field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (institutoIdInstitutoNew != null) {
                institutoIdInstitutoNew = em.getReference(institutoIdInstitutoNew.getClass(), institutoIdInstitutoNew.getIdInstituto());
                usuario.setInstitutoIdInstituto(institutoIdInstitutoNew);
            }
            Collection<Mudanca> attachedMudancaCollectionNew = new ArrayList<Mudanca>();
            for (Mudanca mudancaCollectionNewMudancaToAttach : mudancaCollectionNew) {
                mudancaCollectionNewMudancaToAttach = em.getReference(mudancaCollectionNewMudancaToAttach.getClass(), mudancaCollectionNewMudancaToAttach.getIdMudanca());
                attachedMudancaCollectionNew.add(mudancaCollectionNewMudancaToAttach);
            }
            mudancaCollectionNew = attachedMudancaCollectionNew;
            usuario.setMudancaCollection(mudancaCollectionNew);
            usuario = em.merge(usuario);
            if (institutoIdInstitutoOld != null && !institutoIdInstitutoOld.equals(institutoIdInstitutoNew)) {
                institutoIdInstitutoOld.getUsuarioCollection().remove(usuario);
                institutoIdInstitutoOld = em.merge(institutoIdInstitutoOld);
            }
            if (institutoIdInstitutoNew != null && !institutoIdInstitutoNew.equals(institutoIdInstitutoOld)) {
                institutoIdInstitutoNew.getUsuarioCollection().add(usuario);
                institutoIdInstitutoNew = em.merge(institutoIdInstitutoNew);
            }
            for (Mudanca mudancaCollectionNewMudanca : mudancaCollectionNew) {
                if (!mudancaCollectionOld.contains(mudancaCollectionNewMudanca)) {
                    Usuario oldIdAdmOfMudancaCollectionNewMudanca = mudancaCollectionNewMudanca.getIdAdm();
                    mudancaCollectionNewMudanca.setIdAdm(usuario);
                    mudancaCollectionNewMudanca = em.merge(mudancaCollectionNewMudanca);
                    if (oldIdAdmOfMudancaCollectionNewMudanca != null && !oldIdAdmOfMudancaCollectionNewMudanca.equals(usuario)) {
                        oldIdAdmOfMudancaCollectionNewMudanca.getMudancaCollection().remove(mudancaCollectionNewMudanca);
                        oldIdAdmOfMudancaCollectionNewMudanca = em.merge(oldIdAdmOfMudancaCollectionNewMudanca);
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
                String id = usuario.getLogin();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mudanca> mudancaCollectionOrphanCheck = usuario.getMudancaCollection();
            for (Mudanca mudancaCollectionOrphanCheckMudanca : mudancaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mudanca " + mudancaCollectionOrphanCheckMudanca + " in its mudancaCollection field has a non-nullable idAdm field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Instituto institutoIdInstituto = usuario.getInstitutoIdInstituto();
            if (institutoIdInstituto != null) {
                institutoIdInstituto.getUsuarioCollection().remove(usuario);
                institutoIdInstituto = em.merge(institutoIdInstituto);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
