/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marco
 */
@Entity
@Table(name = "instituto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instituto.findAll", query = "SELECT i FROM Instituto i")
    , @NamedQuery(name = "Instituto.findByIdInstituto", query = "SELECT i FROM Instituto i WHERE i.idInstituto = :idInstituto")
    , @NamedQuery(name = "Instituto.findByQtdAlunos", query = "SELECT i FROM Instituto i WHERE i.qtdAlunos = :qtdAlunos")
    , @NamedQuery(name = "Instituto.findByLocalidade", query = "SELECT i FROM Instituto i WHERE i.localidade = :localidade")
    , @NamedQuery(name = "Instituto.findByCodGrafo", query = "SELECT i FROM Instituto i WHERE i.codGrafo = :codGrafo")})
public class Instituto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Instituto")
    private Integer idInstituto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_Alunos")
    private int qtdAlunos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "localidade")
    private String localidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cod_Grafo")
    private String codGrafo;
    @OneToMany(mappedBy = "idInstituto")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insitutoIdInstituto")
    private Collection<Lixeira> lixeiraCollection;
    @OneToMany(mappedBy = "institutoIdInstituto")
    private Collection<Usuario> usuarioCollection;

    public Instituto() {
    }

    public Instituto(Integer idInstituto) {
        this.idInstituto = idInstituto;
    }

    public Instituto(Integer idInstituto, int qtdAlunos, String localidade, String codGrafo) {
        this.idInstituto = idInstituto;
        this.qtdAlunos = qtdAlunos;
        this.localidade = localidade;
        this.codGrafo = codGrafo;
    }

    public Integer getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Integer idInstituto) {
        this.idInstituto = idInstituto;
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCodGrafo() {
        return codGrafo;
    }

    public void setCodGrafo(String codGrafo) {
        this.codGrafo = codGrafo;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @XmlTransient
    public Collection<Lixeira> getLixeiraCollection() {
        return lixeiraCollection;
    }

    public void setLixeiraCollection(Collection<Lixeira> lixeiraCollection) {
        this.lixeiraCollection = lixeiraCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstituto != null ? idInstituto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instituto)) {
            return false;
        }
        Instituto other = (Instituto) object;
        if ((this.idInstituto == null && other.idInstituto != null) || (this.idInstituto != null && !this.idInstituto.equals(other.idInstituto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Instituto[ idInstituto=" + idInstituto + " ]";
    }
    
}
