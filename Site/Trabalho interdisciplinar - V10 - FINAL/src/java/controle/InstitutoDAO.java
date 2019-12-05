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
import controle.Conexao;

/**
 *
 * @author aluno
 */
public class InstitutoDAO {
    public void salvar(InstitutoBean instituto){
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO `instituto`(`qtd_Alunos`,`localidade`,`cod_Grafo`,`campus`)VALUES (?,?,?,?)");
            ps.setInt(1,instituto.getQtdAlunos());
            ps.setString(2,instituto.getLocalidade());
            ps.setString(3,instituto.getCodGrafo());
            ps.setString(4,instituto.getCampus());
            ps.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<InstitutoBean> buscarTodos()
    {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps =conexao.prepareStatement("select * from instituto");
            ResultSet resultado = ps.executeQuery();
            List<InstitutoBean> institutos = new ArrayList<>();
            while(resultado.next()){
                InstitutoBean instituto = new InstitutoBean();
                instituto.setCampus(resultado.getString("campus"));
                instituto.setCodGrafo(resultado.getString("cod_Grafo"));
                instituto.setIdInstituto(resultado.getInt("id_Instituto"));
                instituto.setLocalidade(resultado.getString("localidade"));
                instituto.setQtdAlunos(resultado.getInt("qtd_Alunos"));
                institutos.add(instituto);
            }
            return institutos;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public InstitutoBean buscar(int IdInstituto){
        try {
            Connection conexao = Conexao.getConexao();
            String logon = "'"+
                    IdInstituto+ "'";
            PreparedStatement ps =conexao.prepareStatement("select * from instituto where id_Instituto = "+logon);
            ResultSet resultado = ps.executeQuery();
             InstitutoBean instituto = new InstitutoBean();
            while(resultado.next()){
                if(resultado.getInt("id_Instituto")==IdInstituto){
                instituto.setCampus(resultado.getString("campus"));
                instituto.setCodGrafo(resultado.getString("cod_Grafo"));
                instituto.setIdInstituto(resultado.getInt("id_Instituto"));
                instituto.setLocalidade(resultado.getString("localidade"));
                instituto.setQtdAlunos(resultado.getInt("qtd_Alunos"));
                }
            }
            
                return instituto;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
