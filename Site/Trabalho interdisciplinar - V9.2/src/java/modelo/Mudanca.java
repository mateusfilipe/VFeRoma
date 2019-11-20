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
 * @author marco
 */
@Entity
@Table(name = "mudanca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mudanca.findAll", query = "SELECT m FROM Mudanca m")
    , @NamedQuery(name = "Mudanca.findByDescricao", query = "SELECT m FROM Mudanca m WHERE m.descricao = :descricao")
    , @NamedQuery(name = "Mudanca.findByIdMudanca", query = "SELECT m FROM Mudanca m WHERE m.idMudanca = :idMudanca")
    , @NamedQuery(name = "Mudanca.findByCodGrafo", query = "SELECT m FROM Mudanca m WHERE m.codGrafo = :codGrafo")})
public class Mudanca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mudanca")
    private Integer idMudanca;
    @Size(max = 200)
    @Column(name = "cod_Grafo")
    private String codGrafo;
    @JoinColumn(name = "id_adm", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario idAdm;

    public Mudanca() {
    }

    public Mudanca(Integer idMudanca) {
        this.idMudanca = idMudanca;
    }

    public Mudanca(Integer idMudanca, String descricao) {
        this.idMudanca = idMudanca;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdMudanca() {
        return idMudanca;
    }

    public void setIdMudanca(Integer idMudanca) {
        this.idMudanca = idMudanca;
    }

    public String getCodGrafo() {
        return codGrafo;
    }

    public void setCodGrafo(String codGrafo) {
        this.codGrafo = codGrafo;
    }

    public Usuario getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(Usuario idAdm) {
        this.idAdm = idAdm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMudanca != null ? idMudanca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mudanca)) {
            return false;
        }
        Mudanca other = (Mudanca) object;
        if ((this.idMudanca == null && other.idMudanca != null) || (this.idMudanca != null && !this.idMudanca.equals(other.idMudanca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Mudanca[ idMudanca=" + idMudanca + " ]";
    }
    
}
