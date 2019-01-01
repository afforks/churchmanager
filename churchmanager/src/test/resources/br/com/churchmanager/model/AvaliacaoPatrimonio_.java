package br.com.churchmanager.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-01T16:49:23.585-0300")
@StaticMetamodel(AvaliacaoPatrimonio.class)
public class AvaliacaoPatrimonio_ extends EntidadeGenerica_ {
	public static volatile SingularAttribute<AvaliacaoPatrimonio, Patrimonio> patrimonio;
	public static volatile SingularAttribute<AvaliacaoPatrimonio, String> observacao;
	public static volatile SingularAttribute<AvaliacaoPatrimonio, Date> dataAvaliacao;
	public static volatile SingularAttribute<AvaliacaoPatrimonio, Double> valorAtual;
}
