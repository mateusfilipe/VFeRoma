/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleFeedback;
import controle.ControleInstituto;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author marco
 */
public class FeedbackBean {

    private String feedbackUsuario;
    private Integer idFeedback;
    private Instituto idInstituto;

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

    public Instituto getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Instituto idInstituto) {
        this.idInstituto = idInstituto;
    }

    public List getFeedbacks() throws NamingException {
        return ControleFeedback.getFeedbacks();
    }

    public String enviar() throws NamingException {
        Feedback feedback = new Feedback();
        feedback.setFeedbackUsuario(feedbackUsuario);
        feedback.setIdFeedback(null);
        feedback.setIdInstituto(ControleInstituto.procuraInstituto(idFeedback));
        return "enviar";
    }
}
