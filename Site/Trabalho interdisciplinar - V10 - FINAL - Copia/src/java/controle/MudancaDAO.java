///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controle;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import modelo.LixeiraBean;
//import modelo.MudancaBean;
//
///**
// *
// * @author marco
// */
//public class MudancaDAO {
//  public void salvar(MudancaBean feedback){
//        try {
//            
//            
//                Connection conexao = Conexao.getConexao();
//            PreparedStatement ps = conexao.prepareStatement("INSERT INTO `mudanca`(`qtd_Coletada`,`localidade_Lixeira`,`qtd_Coletada_Total`,`insituto_Id_Instituto`,`tipo_Id_Tipo_Lixo`)VALUES(?,?,?,?,?)");
//            ps.setDouble(1,feedback.);
//            ps.setString(2,feedback.getLocalidadeLixeira());
//            ps.setDouble(3,feedback.getQtdColetadaTotal());
//            ps.setInt(4,feedback.getInsitutoIdInstituto());
//            ps.setInt(5,feedback.getTipoIdTipoLixo());
//            
//            
//            ps.execute();
//            Conexao.fecharConexao();
//
//            
//                               
//            } catch (SQLException ex) {
//            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }  
//}
