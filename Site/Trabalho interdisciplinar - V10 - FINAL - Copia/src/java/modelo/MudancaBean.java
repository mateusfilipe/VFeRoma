/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleMudanca;
import controle.ControleUsuario;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author marco
 */
public class MudancaBean {
    private String descricao;
    private Integer idMudanca;
    private String codGrafo;
    private String idAdm;

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

    public String getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(String idAdm) {
        this.idAdm = idAdm;
    }
    
    public List getMudancas() throws NamingException{
        return ControleMudanca.getMudancas();
    }
    public Mudanca getMudanca(Integer index) throws NamingException{
        return ControleMudanca.procuraMudanca(index);
    }
    public void inserirMudanca() throws NamingException{
        Mudanca mudanca = new Mudanca();
        mudanca.setCodGrafo(codGrafo);
        mudanca.setDescricao(descricao);
        mudanca.setIdAdm(ControleUsuario.procuraUsuario(idAdm));
        mudanca.setIdMudanca(null);        
        ControleMudanca.inserir(mudanca);
    }
}