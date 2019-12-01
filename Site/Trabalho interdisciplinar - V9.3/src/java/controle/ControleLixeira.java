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
import modelo.Lixeira;

/**
 *
 * @author marco
 */
public abstract class ControleLixeira {
    private static UserTransaction utx = null;
    private static LixeiraJpaController controlador = null;

    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new LixeiraJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getLixeiras() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findLixeiraEntities();
    }
    public static Lixeira procuraLixeira(Integer index) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findLixeira(index);
    }
    public static void inserir(Lixeira lixeira) throws NamingException{
        if(utx == null){
            inicializar();
        }
        try {
            controlador.create(lixeira);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
