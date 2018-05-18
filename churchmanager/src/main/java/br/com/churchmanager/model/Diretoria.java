package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.churchmanager.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "diretoria")
@Table(name = "diretoria")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class Diretoria extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2345438374225768867L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@NotEmpty
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "diretoria_pessoa_cargo", joinColumns = {
			@JoinColumn(name = "diretoria_id") }, inverseJoinColumns = { @JoinColumn(name = "pessoa_cargo_id") })
	private List<PessoaCargo> pessoaCargos = new ArrayList<>();

	@NotNull
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	private Date inicio = DataUtil.stringParaDate("01/01/" + DataUtil.ano());

	@NotNull
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = false)
	private Date termino = DataUtil.stringParaDate("31/12/" + DataUtil.ano());

}