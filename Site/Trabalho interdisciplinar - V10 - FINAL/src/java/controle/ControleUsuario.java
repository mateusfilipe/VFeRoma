/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import modelo.Usuario;

/**
 *
 * @author marco
 */
public abstract class ControleUsuario {
    private static UserTransaction utx = null;
    private static UsuarioJpaController controlador = null;

    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new UsuarioJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getUsuarios() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findUsuarioEntities();
    }
    public static Usuario procuraUsuario(String user) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findUsuario(user);
    }
    public static void inserir(Usuario user) throws NamingException{
        if(utx == null){
            inicializar();
        }
        try {
            controlador.create(user);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
