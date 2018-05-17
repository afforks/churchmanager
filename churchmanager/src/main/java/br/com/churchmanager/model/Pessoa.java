package br.com.churchmanager.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.churchmanager.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Entity(name = "pessoa")
@Table(name = "pessoa")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class Pessoa extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2103210206726291934L;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 20)
	@Column(name = "apelido")
	private String apelido;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "matricula", unique = true, nullable = false)
	private String matricula;

//	@Builder.Default
	@ElementCollection
	@CollectionTable(name = "telefones", joinColumns = { @JoinColumn(name = "pessoa_id") })
	@Column(name = "telefone")
	@Singular()
	private List<String> telefones = new ArrayList<>();

	@Singular
//	@Builder.Default()
	@ElementCollection
	@CollectionTable(name = "emails", joinColumns = { @JoinColumn(name = "pessoa_id") })
	@Column(name = "email")
	private List<String> emails = new ArrayList<>();

	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	@Past
	private Date dataNascimento;

	@Column(name = "data_batismo")
	@Temporal(TemporalType.DATE)
	@Past
	private Date dataBatismo;

	@Column(name = "data_conversao")
	@Temporal(TemporalType.DATE)
	@Past
	private Date dataConversao;

	@Embedded
	@Builder.Default
	private Endereco endereco = new Endereco();

	@Builder.Default
	@Column(name = "estado_civil")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;

	@Column(name = "escolaridade")
	@Enumerated(EnumType.STRING)
	private PerfilEscolaridade escolaridade;

	@Column(name = "sexo")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(name = "profissao")
	private String profissao;

	@Column(name = "observacao")
	@Lob
	private String observacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "pessoa_atividade_eclesiastica", joinColumns = {
			@JoinColumn(name = "pessoa_id") }, inverseJoinColumns = { @JoinColumn(name = "atividade_eclesiastica_id") })
	@Builder.Default
	private List<AtividadeEclesiastica> atividadesEclesiastica = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = { CascadeType.ALL }, targetEntity = Dizimo.class, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Builder.Default
	private List<Dizimo> dizimos = new ArrayList<>();

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public int getIdade() {
		return DataUtil.calcularIdade(this.dataNascimento);
	}
	
	public void gerarMatricula() {
		this.matricula = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	public Endereco getEndereco(){
		if(this.endereco == null) {this.endereco = new Endereco();}
		return endereco;
	}
}