/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interdisciplinar.web;

import java.io.File;

/**
 *
 * @author aluno
 */
public class IF {
    private String codigo;
    private String localidade;
    private String NomeCampus;
    private int quantidadeAlunos;
    private File imagem;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getNomeCampus() {
        return NomeCampus;
    }

    public void setNomeCampus(String NomeCampus) {
        this.NomeCampus = NomeCampus;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }
    
    
}
