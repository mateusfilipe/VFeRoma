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
@Table(name = "lixeira")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lixeira.findAll", query = "SELECT l FROM Lixeira l")
    , @NamedQuery(name = "Lixeira.findByIdLixeira", query = "SELECT l FROM Lixeira l WHERE l.idLixeira = :idLixeira")
    , @NamedQuery(name = "Lixeira.findByQtdColetada", query = "SELECT l FROM Lixeira l WHERE l.qtdColetada = :qtdColetada")
    , @NamedQuery(name = "Lixeira.findByLocalidadeLixeira", query = "SELECT l FROM Lixeira l WHERE l.localidadeLixeira = :localidadeLixeira")
    , @NamedQuery(name = "Lixeira.findByQtdColetadaTotal", query = "SELECT l FROM Lixeira l WHERE l.qtdColetadaTotal = :qtdColetadaTotal")})
public class Lixeira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Lixeira")
    private Integer idLixeira;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_Coletada")
    private double qtdColetada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "localidade_Lixeira")
    private String localidadeLixeira;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_Coletada_Total")
    private double qtdColetadaTotal;
    @JoinColumn(name = "insituto_Id_Instituto", referencedColumnName = "id_Instituto")
    @ManyToOne(optional = false)
    private Instituto insitutoIdInstituto;
    @JoinColumn(name = "tipo_Id_Tipo_Lixo", referencedColumnName = "id_Tipo_Lixo")
    @ManyToOne(optional = false)
    private TipoLixo tipoIdTipoLixo;

    public Lixeira() {
    }

    public Lixeira(Integer idLixeira) {
        this.idLixeira = idLixeira;
    }

    public Lixeira(Integer idLixeira, double qtdColetada, String localidadeLixeira, double qtdColetadaTotal) {
        this.idLixeira = idLixeira;
        this.qtdColetada = qtdColetada;
        this.localidadeLixeira = localidadeLixeira;
        this.qtdColetadaTotal = qtdColetadaTotal;
    }

    public Integer getIdLixeira() {
        return idLixeira;
    }

    public void setIdLixeira(Integer idLixeira) {
        this.idLixeira = idLixeira;
    }

    public double getQtdColetada() {
        return qtdColetada;
    }

    public void setQtdColetada(double qtdColetada) {
        this.qtdColetada = qtdColetada;
    }

    public String getLocalidadeLixeira() {
        return localidadeLixeira;
    }

    public void setLocalidadeLixeira(String localidadeLixeira) {
        this.localidadeLixeira = localidadeLixeira;
    }

    public double getQtdColetadaTotal() {
        return qtdColetadaTotal;
    }

    public void setQtdColetadaTotal(double qtdColetadaTotal) {
        this.qtdColetadaTotal = qtdColetadaTotal;
    }

    public Instituto getInsitutoIdInstituto() {
        return insitutoIdInstituto;
    }

    public void setInsitutoIdInstituto(Instituto insitutoIdInstituto) {
        this.insitutoIdInstituto = insitutoIdInstituto;
    }

    public TipoLixo getTipoIdTipoLixo() {
        return tipoIdTipoLixo;
    }

    public void setTipoIdTipoLixo(TipoLixo tipoIdTipoLixo) {
        this.tipoIdTipoLixo = tipoIdTipoLixo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLixeira != null ? idLixeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lixeira)) {
            return false;
        }
        Lixeira other = (Lixeira) object;
        if ((this.idLixeira == null && other.idLixeira != null) || (this.idLixeira != null && !this.idLixeira.equals(other.idLixeira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Lixeira[ idLixeira=" + idLixeira + " ]";
    }
    
}
