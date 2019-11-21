/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleBDUsuario;
import controle.InstitutoJpaController;
import controle.UsuarioJpaController;
import java.net.MalformedURLException;
import static java.rmi.Naming.lookup;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;


/**
 *
 * @author aluno
 */
public class UsuarioBean {
    private String nome;
    private String email;
    private String senha;
    private String confirmaSenha;
    public boolean adm;
    private String login;
    
    public boolean isTipoUsuario() {
        return adm;
    }

    public void setTipoUsuario(boolean tipoUsuario) {
        this.adm = tipoUsuario;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
    
    public String novo(){
        return "cadastro";
    }
  
 
    public String salvar() throws NamingException, NotBoundException, MalformedURLException, RemoteException {

        FacesContext context = FacesContext.getCurrentInstance();

        if(!this.senha.equalsIgnoreCase(this.confirmaSenha))
        {

        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada incorretamente.",""));

        return "cadastro";
        }
        Context context1 = new InitialContext();
        UserTransaction utx = (UserTransaction)   context1.lookup("java:comp/UserTransaction");
        Instituto u = new Instituto();
        u.setIdInstituto(null);
        u.setLocalidade("Sabara");
        u.setQtdAlunos(20);
        u.setCodGrafo("Q");
//        System.out.println("INST: "+u.getLocalidade());
        InstitutoJpaController controlador = new InstitutoJpaController(utx,ControleBDUsuario.getControle().getEmf());
//        Usuario u = new Usuario(login, email, senha, nome);
//        UsuarioJpaController controlador = new UsuarioJpaController(null,ControleBDUsuario.getControle().getEmf());
        try {
            controlador.create(u);
            controlador.findInstitutoEntities();
             List<Instituto> lista = controlador.findInstitutoEntities();
            for (Instituto usuario : lista) {
                System.out.println("-----" + usuario.getCodGrafo());
            }
//    controlador.destroy(1);
//    controlador.edit(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "sucesso";
    }
  
     public String logar() {
        return adm ? "administrador" : "usuario";
    }
}
