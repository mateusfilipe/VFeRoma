package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoLixo.class)
public abstract class TipoLixo_ {

	public static volatile SingularAttribute<TipoLixo, String> tipoDeLixo;
	public static volatile SingularAttribute<TipoLixo, String> facilidadeReciclagem;
	public static volatile SingularAttribute<TipoLixo, Integer> idTipoLixo;
	public static volatile CollectionAttribute<TipoLixo, Lixeira> lixeiraCollection;
	public static volatile SingularAttribute<TipoLixo, String> descricao;

}

