package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Instituto> institutoIdInstituto;
	public static volatile SingularAttribute<Usuario, String> confirmaSenha;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, String> usuario;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, Boolean> adm;
	public static volatile SingularAttribute<Usuario, String> cargo;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile CollectionAttribute<Usuario, Mudanca> mudancaCollection;

}

