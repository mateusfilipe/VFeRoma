/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioBean;
import controle.Conexao;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author aluno
 */
public class UsuarioDAO {

    public String salvar(UsuarioBean usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            if(new UsuarioDAO().buscar(usuario.getLogin())==null){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario Não Cadastrado.", ""));
                return "cadastro";
            }else{
            
            PreparedStatement ps =  (PreparedStatement) conexao.prepareCall("INSERT INTO `usuario`(`usuario`,`email`,`senha`,`confirmaSenha`,`adm`,`nome`)VALUES(?,?,?,?,?,?)");
            ps.setString(1,usuario.getLogin());
            ps.setString(2,usuario.getEmail());
            ps.setString(3,usuario.getSenha());
            ps.setString(4,usuario.getConfirmaSenha());
            ps.setBoolean(5,false);
            ps.setString(6,usuario.getNome());
            ps.execute();
            Conexao.fecharConexao();}
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }
    public List<UsuarioBean> buscarTodos()
    {
        try {
            Connection conexao = Conexao.getConexao();
            java.sql.PreparedStatement ps =conexao.prepareStatement("select * from usuario");
            ResultSet resultado = ps.executeQuery();
            List<UsuarioBean> usuarios = new ArrayList<>();
            while(resultado.next()){
                UsuarioBean usuario = new UsuarioBean();
                usuario.setAdm(resultado.getBoolean("adm"));
                usuario.setCargo(resultado.getString("cargo"));
                usuario.setConfirmaSenha(resultado.getString("confirmaSenha"));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setInstituto(resultado.getInt("instituto_Id_Instituto"));
                usuario.setLogin(resultado.getString("usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSenha(resultado.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public UsuarioBean buscar(String login){
        try {
            Connection conexao = Conexao.getConexao();
            String logon = "'"+
                    login+ "'";
//            System.out.println(logon);
            String sql = "select * from usuario u where u.usuario = "+logon;
            java.sql.PreparedStatement ps =conexao.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            UsuarioBean usuario = new UsuarioBean();
                while(resultado.next()){
                    if(resultado.getString("usuario").equals(login)){               
                usuario.setAdm(resultado.getBoolean("adm"));
                usuario.setCargo(resultado.getString("cargo"));
                usuario.setConfirmaSenha(resultado.getString("confirmaSenha"));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setInstituto(resultado.getInt("instituto_Id_Instituto"));
                usuario.setLogin(resultado.getString("usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSenha(resultado.getString("senha"));
              }
            }
                return usuario;
            
        } catch (SQLException ex) {
            Logger.getLogger(InstitutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
