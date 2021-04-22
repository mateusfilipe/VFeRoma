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
import controle.Conexao;

/**
 *
 * @author aluno
 */
public class FeedbackDAO {
    public void salvar(FeedbackBean feedback){
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO `feedback`(`id_Instituto`,`feedback_Usuario`)VALUES(?,?)");
            ps.setInt(1,feedback.getIdInstituto());
            ps.setString(2,feedback.getFeedbackUsuario());
            ps.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<FeedbackBean> buscarTodos()
    {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps =conexao.prepareStatement("select * from feedback");
            ResultSet resultado = ps.executeQuery();
            List<FeedbackBean> institutos = new ArrayList<>();
            while(resultado.next()){
                FeedbackBean feedback = new FeedbackBean();
                feedback.setFeedbackUsuario(resultado.getString("feedback_Usuario"));
                feedback.setIdInstituto(resultado.getInt("id_Instituto"));
                feedback.setIdFeedback(resultado.getInt("id_feedback"));
                institutos.add(feedback);
            }
            Conexao.fecharConexao();
            return institutos;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public FeedbackBean buscar(int Id){
        try {
            Connection conexao = Conexao.getConexao();
            String logon = "'"+
                    Id+ "'";
            PreparedStatement ps =conexao.prepareStatement("select * from instituto where IdInstituto = "+logon);
            ResultSet resultado = ps.executeQuery();
            List<FeedbackBean> institutos = new ArrayList<>();
            FeedbackBean feedback = new FeedbackBean();
            while(resultado.next()){
                if(resultado.getInt("id_feedback") == Id){
                
                feedback.setFeedbackUsuario(resultado.getString("feedback_Usuario"));
                feedback.setIdInstituto(resultado.getInt("id_Instituto"));
                feedback.setIdFeedback(resultado.getInt("id_feedback"));
                institutos.add(feedback);
                }
            }
                Conexao.fecharConexao();
                return feedback;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
