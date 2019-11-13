/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interdisciplinar.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author marco
 */
public class UsuarioBean {
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String confirmaSenha;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
    
    public String salvar(){

        FacesContext context = FacesContext.getCurrentInstance();

        if(!this.senha.equalsIgnoreCase(this.confirmaSenha))
        {

        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada incorretamente.",""));

        return "cadastro";
        }
        return "sucesso";
    }
  
}
