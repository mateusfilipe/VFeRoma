package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lixeira.class)
public abstract class Lixeira_ {

	public static volatile SingularAttribute<Lixeira, Double> qtdColetadaTotal;
	public static volatile SingularAttribute<Lixeira, Instituto> insitutoIdInstituto;
	public static volatile SingularAttribute<Lixeira, Double> qtdColetada;
	public static volatile SingularAttribute<Lixeira, Integer> idLixeira;
	public static volatile SingularAttribute<Lixeira, TipoLixo> tipoIdTipoLixo;
	public static volatile SingularAttribute<Lixeira, String> localidadeLixeira;

}

