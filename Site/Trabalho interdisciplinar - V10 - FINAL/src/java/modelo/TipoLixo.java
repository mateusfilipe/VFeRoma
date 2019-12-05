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
@Table(name = "tipo_lixo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLixo.findAll", query = "SELECT t FROM TipoLixo t")
    , @NamedQuery(name = "TipoLixo.findByIdTipoLixo", query = "SELECT t FROM TipoLixo t WHERE t.idTipoLixo = :idTipoLixo")
    , @NamedQuery(name = "TipoLixo.findByTipoDeLixo", query = "SELECT t FROM TipoLixo t WHERE t.tipoDeLixo = :tipoDeLixo")
    , @NamedQuery(name = "TipoLixo.findByDescricao", query = "SELECT t FROM TipoLixo t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "TipoLixo.findByFacilidadeReciclagem", query = "SELECT t FROM TipoLixo t WHERE t.facilidadeReciclagem = :facilidadeReciclagem")})
public class TipoLixo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Tipo_Lixo")
    private Integer idTipoLixo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_de_lixo")
    private String tipoDeLixo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 100)
    @Column(name = "facilidade_reciclagem")
    private String facilidadeReciclagem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIdTipoLixo")
    private Collection<Lixeira> lixeiraCollection;

    public TipoLixo() {
    }

    public TipoLixo(Integer idTipoLixo) {
        this.idTipoLixo = idTipoLixo;
    }

    public TipoLixo(Integer idTipoLixo, String tipoDeLixo, String descricao) {
        this.idTipoLixo = idTipoLixo;
        this.tipoDeLixo = tipoDeLixo;
        this.descricao = descricao;
    }

    public Integer getIdTipoLixo() {
        return idTipoLixo;
    }

    public void setIdTipoLixo(Integer idTipoLixo) {
        this.idTipoLixo = idTipoLixo;
    }

    public String getTipoDeLixo() {
        return tipoDeLixo;
    }

    public void setTipoDeLixo(String tipoDeLixo) {
        this.tipoDeLixo = tipoDeLixo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFacilidadeReciclagem() {
        return facilidadeReciclagem;
    }

    public void setFacilidadeReciclagem(String facilidadeReciclagem) {
        this.facilidadeReciclagem = facilidadeReciclagem;
    }

    @XmlTransient
    public Collection<Lixeira> getLixeiraCollection() {
        return lixeiraCollection;
    }

    public void setLixeiraCollection(Collection<Lixeira> lixeiraCollection) {
        this.lixeiraCollection = lixeiraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoLixo != null ? idTipoLixo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLixo)) {
            return false;
        }
        TipoLixo other = (TipoLixo) object;
        if ((this.idTipoLixo == null && other.idTipoLixo != null) || (this.idTipoLixo != null && !this.idTipoLixo.equals(other.idTipoLixo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoLixo[ idTipoLixo=" + idTipoLixo + " ]";
    }
    
}
