/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleLixeira;
import java.util.List;
import javax.naming.NamingException;
import controle.LixeiraDAO;

/**
 *
 * @author marco
 */
public class LixeiraBean {

    private Integer idLixeira;
    private double qtdColetada;
    private String localidadeLixeira;
    private double qtdColetadaTotal;
    private Integer insitutoIdInstituto;
    private Integer tipoIdTipoLixo;

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

    public Integer getInsitutoIdInstituto() {
        return insitutoIdInstituto;
    }

    public void setInsitutoIdInstituto(Integer insitutoIdInstituto) {
        this.insitutoIdInstituto = insitutoIdInstituto;
    }

    public Integer getTipoIdTipoLixo() {
        return tipoIdTipoLixo;
    }

    public void setTipoIdTipoLixo(Integer tipoIdTipoLixo) {
        this.tipoIdTipoLixo = tipoIdTipoLixo;
    }

    public List getLixeiras() throws NamingException {
        return new LixeiraDAO().buscarTodos();
    }

    public LixeiraBean procuraLixeira(Integer index) throws NamingException {
        return new LixeiraDAO().buscar(index);
    }

    public List getLixeiraIFTP(Integer idIF, Integer idTL) {
        return new LixeiraDAO().buscarIFTP(idIF, idTL);
    }
}
