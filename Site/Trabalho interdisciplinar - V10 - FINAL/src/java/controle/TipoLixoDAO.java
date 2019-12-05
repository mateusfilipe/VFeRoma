/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.InstitutoBean;
import modelo.TipoLixoBean;
import controle.Conexao;

/**
 *
 * @author marco
 */
public class TipoLixoDAO {
    public List<TipoLixoBean> buscarTodos()
    {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps =conexao.prepareStatement("select * from tipo_lixo");
            ResultSet resultado = ps.executeQuery();
            List<TipoLixoBean> institutos = new ArrayList<>();
            while(resultado.next()){
                TipoLixoBean instituto = new TipoLixoBean();
                instituto.setIdTipoLixo(resultado.getInt("id_Tipo_Lixo"));
                instituto.setFacilidadeReciclagem(resultado.getString("facilidade_reciclagem"));
                instituto.setDescricao(resultado.getString("descricao"));
                instituto.setTipoDeLixo(resultado.getString("tipo_de_lixo"));
                institutos.add(instituto);
            }
            return institutos;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public TipoLixoBean buscar(int IdInstituto){
        try {
            Connection conexao = Conexao.getConexao();
            String logon = "'"+
                    IdInstituto+ "'";
            PreparedStatement ps =conexao.prepareStatement("select * from tipo_lixo where id_Tipo_Lixo = "+logon);
            ResultSet resultado = ps.executeQuery();
             TipoLixoBean instituto = new TipoLixoBean();
            while(resultado.next()){
                
                instituto.setIdTipoLixo(resultado.getInt("id_Tipo_Lixo"));
                instituto.setFacilidadeReciclagem(resultado.getString("facilidade_reciclagem"));
                instituto.setDescricao(resultado.getString("descricao"));
                instituto.setTipoDeLixo(resultado.getString("tipo_de_lixo"));
                
            }
            
                return instituto;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
