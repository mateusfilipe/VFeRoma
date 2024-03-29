/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleBDUsuario;
import controle.InstitutoJpaController;
import controle.UsuarioJpaController;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


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
    
    public String salvar() {

        FacesContext context = FacesContext.getCurrentInstance();

        if(!this.senha.equalsIgnoreCase(this.confirmaSenha))
        {

        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada incorretamente.",""));

        return "cadastro";
        }
         Instituto u = new Instituto(7, 20, "Sabara", "Q");
        InstitutoJpaController controlador = new InstitutoJpaController(null,ControleBDUsuario.getControle().getEmf());
//        Usuario u = new Usuario(login, email, senha, nome);
//        UsuarioJpaController controlador = new UsuarioJpaController(null,ControleBDUsuario.getControle().getEmf());
        try {
            controlador.create(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "sucesso";
    }
  
     public String logar() {
        return adm ? "administrador" : "usuario";
    }
}
