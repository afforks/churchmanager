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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import br.com.churchmanager.util.DataUtil;

@Entity(name = "pessoa")
@Table(name = "pessoa")
public class Pessoa extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2103210206726291934L;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 20)
	@Column(name = "apelido")
	private String apelido;

	@NotNull
	@Size(min = 17, max = 17)
	@Column(name = "matricula", unique = true, nullable = false)
	private String matricula = gerarMatricula();

	@ElementCollection
	@CollectionTable(name = "telefones", joinColumns = { @JoinColumn(name = "pessoa_id") })
	@Column(name = "telefone")
	private List<String> telefones = new ArrayList<>();

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
	private Endereco endereco = new Endereco();

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

	private List<AtividadeEclesiastica> atividadesEclesiastica = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = { CascadeType.ALL }, targetEntity = Dizimo.class, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<Dizimo> dizimos = new ArrayList<>();

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public int getIdade() {
		return DataUtil.calcularIdade(this.dataNascimento);
	}

	public String gerarMatricula() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	public Endereco getEndereco() {
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataBatismo() {
		return dataBatismo;
	}

	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}

	public Date getDataConversao() {
		return dataConversao;
	}

	public void setDataConversao(Date dataConversao) {
		this.dataConversao = dataConversao;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public PerfilEscolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(PerfilEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<AtividadeEclesiastica> getAtividadesEclesiastica() {
		return atividadesEclesiastica;
	}

	public void setAtividadesEclesiastica(List<AtividadeEclesiastica> atividadesEclesiastica) {
		this.atividadesEclesiastica = atividadesEclesiastica;
	}

	public List<Dizimo> getDizimos() {
		return dizimos;
	}

	public void setDizimos(List<Dizimo> dizimos) {
		this.dizimos = dizimos;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}