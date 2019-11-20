/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleBDUsuario;
import controle.InstitutoJpaController;

/**
 *
 * @author aluno
 */
public class InstitutoBean {
    int id_Instituto;
    int qtd_Alunos;
    String localidade;
    String cod_Grafo;
 
    public String salvar(){
       Instituto u = new Instituto(7, 20, "Sabara", "Q");
        InstitutoJpaController controlador = new InstitutoJpaController(null,ControleBDUsuario.getControle().getEmf());
        try {
            controlador.create(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "sucesso";
    }
}
