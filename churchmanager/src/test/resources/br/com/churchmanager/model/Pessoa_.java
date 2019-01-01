package br.com.churchmanager.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-01T16:49:23.771-0300")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ {
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile CollectionAttribute<Pessoa, Object> atividadesEclesiastica;
	public static volatile CollectionAttribute<Pessoa, Object> dizimos;
}
