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
public class ControleBDInstituto {
     private static ControleBDInstituto singleton;
    private EntityManagerFactory emf;
    
    private ControleBDInstituto()
    {
         emf = Persistence.createEntityManagerFactory("Trabalho_interdisciplinarPU");
    }

    public static ControleBDInstituto getControle() {
        if(singleton == null)
        {
            singleton = new ControleBDInstituto();
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
