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
import modelo.Mudanca;

/**
 *
 * @author marco
 */
public abstract class ControleMudanca {
    private static UserTransaction utx = null;
    private static MudancaJpaController controlador = null;

    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new MudancaJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getMudancas() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findMudancaEntities();
    }
    public static Mudanca procuraMudanca(Integer index) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findMudanca(index);
    }
    public static void inserir(Mudanca tipo) throws NamingException{
        if(utx == null){
            inicializar();
        }
        try {
            controlador.create(tipo);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
