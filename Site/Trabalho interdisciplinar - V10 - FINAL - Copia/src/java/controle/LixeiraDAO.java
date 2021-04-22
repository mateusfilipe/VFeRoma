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
import modelo.FeedbackBean;
import modelo.LixeiraBean;
import controle.Conexao;

/**
 *
 * @author marco
 */
public class LixeiraDAO {
public void salvar(LixeiraBean feedback){
        try {
            LixeiraBean buscar = buscar(feedback.getIdLixeira());
            if(buscar== null){
                Connection conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO `lixeira`(`qtd_Coletada`,`localidade_Lixeira`,`qtd_Coletada_Total`,`insituto_Id_Instituto`,`tipo_Id_Tipo_Lixo`)VALUES(?,?,?,?,?)");
            ps.setDouble(1,feedback.getQtdColetada());
            ps.setString(2,feedback.getLocalidadeLixeira());
            ps.setDouble(3,feedback.getQtdColetadaTotal());
            ps.setInt(4,feedback.getInsitutoIdInstituto());
            ps.setInt(5,feedback.getTipoIdTipoLixo());
            
            
            ps.execute();
            Conexao.fecharConexao();

            }else{
                               
                Connection conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("UPDATE `vferoma`.`lixeira` SET `qtd_Coletada` = ?,`qtd_Coletada_Total` = ? WHERE `id_Lixeira` = ?");
            ps.setDouble(1,feedback.getQtdColetada());
            ps.setDouble(2,feedback.getQtdColetadaTotal()+ buscar.getQtdColetadaTotal());
            ps.setString(3,buscar.getLocalidadeLixeira());
            }
                    } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<LixeiraBean> buscarTodos()
    {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps =conexao.prepareStatement("select * from lixeira");
            ResultSet resultado = ps.executeQuery();
            List<LixeiraBean> institutos = new ArrayList<>();
            while(resultado.next()){
                LixeiraBean feedback = new LixeiraBean();
                feedback.setQtdColetada(resultado.getDouble("qtd_Coletada"));
                feedback.setIdLixeira(resultado.getInt("id_Lixeira"));
                feedback.setInsitutoIdInstituto(resultado.getInt("insituto_Id_Instituto"));
                feedback.setQtdColetadaTotal(resultado.getDouble("qtd_Coletada_Total"));
                feedback.setLocalidadeLixeira(resultado.getString("localidade_Lixeira"));
                feedback.setTipoIdTipoLixo(resultado.getInt("tipo_Id_Tipo_Lixo"));

                institutos.add(feedback);
            }
            Conexao.fecharConexao();
            return institutos;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public LixeiraBean buscar(int Id){
        try {
            Connection conexao = Conexao.getConexao();
            String logon = "'"+
                    Id+ "'";
            PreparedStatement ps =conexao.prepareStatement("select * from lixeira where id_Lixeira = "+logon);
            ResultSet resultado = ps.executeQuery();
            
            LixeiraBean feedback = new LixeiraBean();
            while(resultado.next()){
                if(resultado.getInt("id_feedback") == Id){
                
                feedback.setQtdColetada(resultado.getDouble("qtd_Coletada"));
                feedback.setIdLixeira(resultado.getInt("id_Lixeira"));
                feedback.setInsitutoIdInstituto(resultado.getInt("insituto_Id_Instituto"));
                feedback.setQtdColetadaTotal(resultado.getDouble("qtd_Coletada_Total"));
                feedback.setLocalidadeLixeira(resultado.getString("localidade_Lixeira"));
                feedback.setTipoIdTipoLixo(resultado.getInt("tipo_Id_Tipo_Lixo"));
                }
            }
                Conexao.fecharConexao();
                return feedback;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<LixeiraBean> buscarIFTP(Integer IdIF,Integer IdTP ){
        try {
            Connection conexao = Conexao.getConexao();

            String logon1 = "'"+IdIF+"'";
            String logon2 = "'"+IdTP+"'";
            PreparedStatement ps =conexao.prepareStatement("select * from lixeira where insituto_Id_Instituto = "+logon1+" and tipo_Id_Tipo_Lixo = "+ logon2);
            ResultSet resultado = ps.executeQuery();
            List<LixeiraBean> institutos = new ArrayList<>();
            LixeiraBean feedback = new LixeiraBean();
            while(resultado.next()){
                
               
                feedback.setQtdColetada(resultado.getDouble("qtd_Coletada"));
                feedback.setIdLixeira(resultado.getInt("id_Lixeira"));
                feedback.setInsitutoIdInstituto(resultado.getInt("insituto_Id_Instituto"));
                feedback.setQtdColetadaTotal(resultado.getDouble("qtd_Coletada_Total"));
                feedback.setLocalidadeLixeira(resultado.getString("localidade_Lixeira"));
                feedback.setTipoIdTipoLixo(resultado.getInt("tipo_Id_Tipo_Lixo"));

                institutos.add(feedback);
                
            }
                Conexao.fecharConexao();
                return institutos;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
