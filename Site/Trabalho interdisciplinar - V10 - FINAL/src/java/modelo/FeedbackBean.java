/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleFeedback;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.naming.NamingException;
import controle.FeedbackDAO;

/**
 *
 * @author marco
 */
@Named
@ManagedBean
public class FeedbackBean {

    private String feedbackUsuario;
    private Integer idFeedback;
    private Integer idInstituto;

    public String getFeedbackUsuario() {
        return feedbackUsuario;
    }

    public void setFeedbackUsuario(String feedbackUsuario) {
        this.feedbackUsuario = feedbackUsuario;
    }

    public Integer getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Integer idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Integer getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Integer idInstituto) {
        this.idInstituto = idInstituto;
    }

    public List getFeedbacks() throws NamingException {
        return ControleFeedback.getFeedbacks();
    }

    public String enviar() throws NamingException {
        new FeedbackDAO().salvar(this);
        return "enviar";
    }
}
