package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Instituto;
import modelo.TipoLixo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-20T09:39:01")
@StaticMetamodel(Lixeira.class)
public class Lixeira_ { 

    public static volatile SingularAttribute<Lixeira, Double> qtdColetadaTotal;
    public static volatile SingularAttribute<Lixeira, Instituto> insitutoIdInstituto;
    public static volatile SingularAttribute<Lixeira, Double> qtdColetada;
    public static volatile SingularAttribute<Lixeira, Integer> idLixeira;
    public static volatile SingularAttribute<Lixeira, TipoLixo> tipoIdTipoLixo;
    public static volatile SingularAttribute<Lixeira, String> localidadeLixeira;

}