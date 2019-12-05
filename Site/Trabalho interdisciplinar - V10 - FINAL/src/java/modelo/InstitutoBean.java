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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import controle.InstitutoDAO;
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
   
    public List getInstitutos()  
    {
        return new InstitutoDAO().buscarTodos();
    }
    
    public InstitutoBean getInstituto(Integer index) 
    {
        return new InstitutoDAO().buscar(index);
    }
    
    public void salvarInstituto(Integer index)
    {
        InstitutoBean instituto = getInstituto(index);
        if(instituto==null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não há o usuário.", ""));
        }
        this.campus = instituto.getCampus();
        this.codGrafo = instituto.getCodGrafo();
        this.idInstituto = instituto.getIdInstituto();
        this.localidade = instituto.getLocalidade();
        this.qtdAlunos = instituto.getQtdAlunos();
    }
   
}
