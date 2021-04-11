package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Instituto.class)
public abstract class Instituto_ {

	public static volatile SingularAttribute<Instituto, String> localidade;
	public static volatile SingularAttribute<Instituto, String> campus;
	public static volatile CollectionAttribute<Instituto, Feedback> feedbackCollection;
	public static volatile SingularAttribute<Instituto, Integer> qtdAlunos;
	public static volatile SingularAttribute<Instituto, Integer> idInstituto;
	public static volatile CollectionAttribute<Instituto, Lixeira> lixeiraCollection;
	public static volatile CollectionAttribute<Instituto, Usuario> usuarioCollection;
	public static volatile SingularAttribute<Instituto, String> codGrafo;

}

