/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aluno
 */
public class ControleBDUsuario {
     private static ControleBDUsuario singleton;
    private EntityManagerFactory emf;
    
    private ControleBDUsuario ()
    {
         emf = Persistence.createEntityManagerFactory("Trabalho_interdisciplinarPU");
    }

    public static ControleBDUsuario getControle() {
        if(singleton == null)
        {
            singleton = new ControleBDUsuario();
        }
        return singleton;
    }

    public EntityManagerFactory getEmf() {
        if(singleton == null)
        {
            getControle();
        }
        return emf;
    }
    
}
