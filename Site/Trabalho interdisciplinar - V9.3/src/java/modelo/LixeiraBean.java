/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleLixeira;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author marco
 */
public class LixeiraBean {
    private Integer idLixeira;
    private double qtdColetada;
    private String localidadeLixeira;
    private double qtdColetadaTotal;
    private Instituto insitutoIdInstituto;
   private TipoLixo tipoIdTipoLixo;

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
   
   public List getLixeiras() throws NamingException 
    {
        return ControleLixeira.getLixeiras();
    }
   
   public Lixeira procuraLixeira (Integer index) throws NamingException
   {
       return ControleLixeira.procuraLixeira(index);
   }
}
