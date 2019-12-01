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
import modelo.Instituto;

/**
 *
 * @author marco
 */
public abstract class ControleInstituto {
    private static UserTransaction utx = null;
    private static InstitutoJpaController controlador = null;

    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new InstitutoJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getInstitutos() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findInstitutoEntities();
    }
    public static Instituto procuraInstituto(Integer index) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findInstituto(index);
    }
    public static void inserir(Instituto instituto) throws NamingException{
        if(utx == null){
            inicializar();
        }
        try {
            controlador.create(instituto);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
