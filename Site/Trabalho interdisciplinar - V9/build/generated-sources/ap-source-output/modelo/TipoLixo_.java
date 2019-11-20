package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Lixeira;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-20T09:39:01")
@StaticMetamodel(TipoLixo.class)
public class TipoLixo_ { 

    public static volatile SingularAttribute<TipoLixo, String> tipoDeLixo;
    public static volatile SingularAttribute<TipoLixo, String> facilidadeReciclagem;
    public static volatile SingularAttribute<TipoLixo, Integer> idTipoLixo;
    public static volatile CollectionAttribute<TipoLixo, Lixeira> lixeiraCollection;
    public static volatile SingularAttribute<TipoLixo, String> descricao;

}