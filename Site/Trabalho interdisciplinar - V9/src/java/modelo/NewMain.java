/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author marco
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException {
        // TODO code application logic here
        Usuario u =  new Usuario("AAA", "A@gmail.com", "123","W");
        
        Context context1 = new InitialContext();
        UserTransaction utx = (UserTransaction)   context1.lookup("java:comp/UserTransaction");;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho_interdisciplinarPU");
        UsuarioJpaController controlador = new UsuarioJpaController(utx,emf);
        try {
            controlador.create(u);
        } catch (Exception ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
