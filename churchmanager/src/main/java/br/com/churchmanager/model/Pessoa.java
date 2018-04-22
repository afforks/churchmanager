package br.com.churchmanager.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Past;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.churchmanager.util.DataUtil;

@Entity(name = "pessoa")
@Table(name = "pessoa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pessoa extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2103210206726291934L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "apelido")
	private String apelido;

	@Column(name = "matricula", unique = true, nullable = false)
	private String matricula;

	@ElementCollection
	@CollectionTable(name = "telefones", joinColumns = { @JoinColumn(name = "pessoa_id") })
	@Column(name = "telefone")
	private List<String> telefones;

	@ElementCollection
	@CollectionTable(name = "emails", joinColumns = { @JoinColumn(name = "pessoa_id") })
	@Column(name = "email")
	private List<String> emails;

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
	private Endereco endereco;

	@Column(name = "estado_civil")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

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
	private List<AtividadeEclesiastica> atividadesEclesiastica;

	@OneToMany(mappedBy = "pessoa", cascade = { CascadeType.ALL }, targetEntity = Dizimo.class, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<Dizimo> dizimos;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return this.apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public List<String> getTelefones() {
		if (telefones == null)
			telefones = new ArrayList<String>();
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<String> getEmails() {
		if (emails == null)
			emails = new ArrayList<String>();
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataBatismo() {
		return this.dataBatismo;
	}

	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}

	public Date getDataConversao() {
		return this.dataConversao;
	}

	public void setDataConversao(Date dataConversao) {
		this.dataConversao = dataConversao;
	}

	public Endereco getEndereco() {
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}

		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EstadoCivil getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public PerfilEscolaridade getEscolaridade() {
		return this.escolaridade;
	}

	public void setEscolaridade(PerfilEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<AtividadeEclesiastica> getAtividadesEclesiastica() {
		return this.atividadesEclesiastica;
	}

	public void setAtividadesEclesiastica(List<AtividadeEclesiastica> atividadesEclesiastica) {
		this.atividadesEclesiastica = atividadesEclesiastica;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return DataUtil.calcularIdade(this.dataNascimento);
	}

	public List<Dizimo> getDizimos() {
		return this.dizimos;
	}

	public void setDizimos(List<Dizimo> dizimos) {
		this.dizimos = dizimos;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String toString() {
		return this.nome;
	}

	public void gerarMatricula() {
		this.matricula = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
}