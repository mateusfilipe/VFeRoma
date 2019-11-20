package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Feedback;
import modelo.Lixeira;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-20T09:39:01")
@StaticMetamodel(Instituto.class)
public class Instituto_ { 

    public static volatile SingularAttribute<Instituto, String> localidade;
    public static volatile CollectionAttribute<Instituto, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Instituto, Integer> qtdAlunos;
    public static volatile SingularAttribute<Instituto, Integer> idInstituto;
    public static volatile CollectionAttribute<Instituto, Lixeira> lixeiraCollection;
    public static volatile CollectionAttribute<Instituto, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Instituto, String> codGrafo;

}