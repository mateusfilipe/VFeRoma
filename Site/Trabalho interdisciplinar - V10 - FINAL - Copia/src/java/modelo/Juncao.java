/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import controle.InstitutoDAO;
import controle.LixeiraDAO;
import controle.TipoLixoDAO;

/**
 *
 * @author marco
 */
public class Juncao {

    public class Bloco {

        public InstitutoBean instituto;
        public TipoLixoBean tipo;
        public LixeiraBean lixeira;

        public Bloco(InstitutoBean instituto, TipoLixoBean tipo, LixeiraBean lixeira) {
            this.instituto = instituto;
            this.tipo = tipo;
            this.lixeira = lixeira;
        }

        public InstitutoBean getInstituto() {
            return instituto;
        }

        public void setInstituto(InstitutoBean instituto) {
            this.instituto = instituto;
        }

        public TipoLixoBean getTipo() {
            return tipo;
        }

        public void setTipo(TipoLixoBean tipo) {
            this.tipo = tipo;
        }

        public LixeiraBean getLixeira() {
            return lixeira;
        }

        public void setLixeira(LixeiraBean lixeira) {
            this.lixeira = lixeira;
        }
    }
    
    public List<Bloco> juncao = new ArrayList<>();

    public Juncao() {
        pegaJuncao();
    }

    public void pegaJuncao() {
        int i = 0, j = 0, k = 0;
        List<InstitutoBean> ins = new InstitutoDAO().buscarTodos();
        while (!ins.isEmpty()) {
            List<TipoLixoBean> tp = new TipoLixoDAO().buscarTodos();
            while (!tp.isEmpty()) {
                List<LixeiraBean> lx = new LixeiraDAO().buscarIFTP(ins.get(0).getIdInstituto(), tp.get(0).getIdTipoLixo());
                while (!lx.isEmpty()) {
                    juncao.add(new Bloco(ins.get(0), tp.get(0), lx.get(0)));
                    lx.remove(0);
                }
                tp.remove(0);
            }
            ins.remove(0);
        }
    }

    public List<Bloco> getJuncao() {
        return juncao;
    }

    public void setJuncao(List<Bloco> juncao) {
        this.juncao = juncao;
    }

    

}
