/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.InstitutoJpaController;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import controle.ControleBD;
import controle.ControleInstituto;
/**
 *
 * @author marco
 */
public class InstitutoBean {
   
   private Integer idInstituto;
   private int qtdAlunos;
   private String localidade;
   private String codGrafo;
   private String campus;

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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
   
    public List getInstitutos() throws NamingException 
    {
        return ControleInstituto.getInstitutos();
   
    }
    public Instituto getInstituto(Integer index) throws NamingException
    {
        return ControleInstituto.procuraInstituto(index);
    }
    
    public void salvarInstituto(Integer index) throws NamingException
    {
        Instituto instituto = ControleInstituto.procuraInstituto(index);
        this.campus = instituto.getCampus();
        this.codGrafo = instituto.getCodGrafo();
        this.idInstituto = instituto.getIdInstituto();
        this.localidade = instituto.getLocalidade();
        this.qtdAlunos = instituto.getQtdAlunos();
    }
   
}
