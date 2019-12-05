/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import modelo.Feedback;
import modelo.Instituto;

/**
 *
 * @author marco
 */
public abstract class ControleFeedback {
    private static UserTransaction utx = null;
    private static FeedbackJpaController controlador = null;
    
    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new FeedbackJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getFeedbacks() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findFeedbackEntities();
    }
    public static Feedback procuraFeedback(Integer index) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findFeedback(index);
    }
    public static void inserir(Feedback feed) throws NamingException{
        if(utx == null){
            inicializar();
        }
        try {
            controlador.create(feed);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
