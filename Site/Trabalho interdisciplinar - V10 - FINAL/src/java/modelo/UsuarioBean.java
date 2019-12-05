/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleBD;
import controle.ControleUsuario;
import controle.InstitutoJpaController;
import controle.UsuarioJpaController;
import java.net.MalformedURLException;
import static java.rmi.Naming.lookup;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.BeanParam;
import controle.InstitutoDAO;
import controle.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */

@Named
@ManagedBean
public class UsuarioBean {

    private String nome;
    private String email;
    private String senha;
    private String confirmaSenha;
    public boolean adm;
    private String login;
    private Integer instituto;
    private String cpf;
    private String cargo;

    public UsuarioBean() {
    }

    public Integer getInstituto() {
        return instituto;
    }

    public void setInstituto(Integer instituto) {
        this.instituto = instituto;
    }

    public boolean isTipoUsuario() {
        return adm;
    }

    public void setTipoUsuario(boolean tipoUsuario) {
        this.adm = tipoUsuario;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public boolean isAdm() {
        return adm;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String novo() {
        return "cadastro";
    }

    public String salvar() throws NamingException, NotBoundException, MalformedURLException, RemoteException {

        FacesContext context = FacesContext.getCurrentInstance();

        if (!this.senha.equalsIgnoreCase(this.confirmaSenha)) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada incorretamente.", ""));

            return "cadastro";
        } 
        UsuarioDAO u = new UsuarioDAO();
             return u.salvar(this);
        
        
    }

    public String logar() {
        FacesContext context = FacesContext.getCurrentInstance();
        
       UsuarioBean usuario = new UsuarioDAO().buscar(this.login);
       if(usuario == null){
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario/Senha Incorreta.", ""));
           return "falso";
       }
        if (!usuario.getSenha().equals(senha)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario/Senha Incorreta.", ""));
//             this.login=null;
//             this.senha=null;
            return "falso";
        }
        this.email = usuario.getEmail();
        this.adm = usuario.isAdm();
        this.instituto = usuario.getInstituto();
        this.login = usuario.getLogin();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.cargo = usuario.getCargo();
        return usuario.isAdm() ? "administrador" : "usuario";
    }
}
