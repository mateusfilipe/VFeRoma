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
import modelo.TipoLixo;

/**
 *
 * @author marco
 */
public abstract class ControleTipoLixo {
     private static UserTransaction utx = null;
    private static TipoLixoJpaController controlador = null;

    private static void  inicializar() throws NamingException{
       utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
       controlador = new TipoLixoJpaController(utx,ControleBD.getControle().getEmf());
    }
         
    public static List getTipos() throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        
        return controlador.findTipoLixoEntities();
    }
    public static TipoLixo procuraInstituto(Integer index) throws NamingException
    {
        if(utx == null){
            inicializar();
        }
        return controlador.findTipoLixo(index);
    }
    public static void inserir(TipoLixo tipo) throws NamingException{
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
