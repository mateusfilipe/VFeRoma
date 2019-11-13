/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findByNomeIf", query = "SELECT f FROM Feedback f WHERE f.nomeIf = :nomeIf")
    , @NamedQuery(name = "Feedback.findByFeedbackUsuario", query = "SELECT f FROM Feedback f WHERE f.feedbackUsuario = :feedbackUsuario")
    , @NamedQuery(name = "Feedback.findByIdFeedback", query = "SELECT f FROM Feedback f WHERE f.idFeedback = :idFeedback")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome_if")
    private String nomeIf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "feedback_Usuario")
    private String feedbackUsuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_feedback")
    private Integer idFeedback;
    @JoinColumn(name = "id_aluno", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario idAluno;

    public Feedback() {
    }

    public Feedback(Integer idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Feedback(Integer idFeedback, String nomeIf, String feedbackUsuario) {
        this.idFeedback = idFeedback;
        this.nomeIf = nomeIf;
        this.feedbackUsuario = feedbackUsuario;
    }

    public String getNomeIf() {
        return nomeIf;
    }

    public void setNomeIf(String nomeIf) {
        this.nomeIf = nomeIf;
    }

    public String getFeedbackUsuario() {
        return feedbackUsuario;
    }

    public void setFeedbackUsuario(String feedbackUsuario) {
        this.feedbackUsuario = feedbackUsuario;
    }

    public Integer getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Integer idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Usuario getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Usuario idAluno) {
        this.idAluno = idAluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeedback != null ? idFeedback.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.idFeedback == null && other.idFeedback != null) || (this.idFeedback != null && !this.idFeedback.equals(other.idFeedback))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Feedback[ idFeedback=" + idFeedback + " ]";
    }
    
}
