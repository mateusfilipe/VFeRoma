/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interdisciplinar.web;

/**
 *
 * @author aluno
 */
public class Administrador extends UsuarioBean{
    
    private String cpf;
    private String funcao;
    private String cargo;
    private int ifAssociado;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public boolean getTipoUsuario() {
        return tipoUsuario;
    } 

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIfAssociado() {
        return ifAssociado;
    }

    public void setIfAssociado(int ifAssociado) {
        this.ifAssociado = ifAssociado;
    }
    
}
