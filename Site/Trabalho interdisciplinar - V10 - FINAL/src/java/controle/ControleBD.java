/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author aluno
 */
public class ControleBD {
     private static ControleBD singleton;
    private EntityManagerFactory emf;
    private EntityTransaction utx;
    private ControleBD()
    {
         emf = (EntityManagerFactory) Persistence.createEntityManagerFactory("Trabalho_interdisciplinarPU");//.createEntityManager();
//         utx = emf.getTranssaction();
//         emf.close();
             }

    public static ControleBD getControle() {
        if(singleton == null)
        {
            singleton = new ControleBD();
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
