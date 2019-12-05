/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleTipoLixo;
import java.util.List;
import javax.naming.NamingException;
import controle.TipoLixoDAO;

/**
 *
 * @author marco
 */
public class TipoLixoBean {
     private Integer idTipoLixo;
    private String tipoDeLixo;
    private String descricao;
    private String facilidadeReciclagem;

    public Integer getIdTipoLixo() {
        return idTipoLixo;
    }

    public void setIdTipoLixo(Integer idTipoLixo) {
        this.idTipoLixo = idTipoLixo;
    }

    public String getTipoDeLixo() {
        return tipoDeLixo;
    }

    public void setTipoDeLixo(String tipoDeLixo) {
        this.tipoDeLixo = tipoDeLixo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFacilidadeReciclagem() {
        return facilidadeReciclagem;
    }

    public void setFacilidadeReciclagem(String facilidadeReciclagem) {
        this.facilidadeReciclagem = facilidadeReciclagem;
    }
    
    public List getTipos() throws NamingException 
    {
        return new TipoLixoDAO().buscarTodos();
   
    }
    public TipoLixoBean getTipo(Integer index) throws NamingException{
        return new TipoLixoBean().getTipo(index);
    }
}
