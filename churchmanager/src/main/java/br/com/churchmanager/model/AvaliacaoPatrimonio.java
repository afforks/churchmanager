package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "avaliacao_patrimonio")
@Table(name = "avaliacao_patrimonio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
@Builder
public class AvaliacaoPatrimonio extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 4287443468273L;

	@ManyToOne(targetEntity = Patrimonio.class)
	@JoinColumn(name = "patrimonio_id", nullable = false)
	private Patrimonio patrimonio;

	@Lob
	@Column(name = "observacao")
	private String observacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_avaliacao", nullable = false)
	private Date dataAvaliacao;

	@DecimalMin("0.01")
	@Column(name = "valor_atual", nullable = false)
	private double valorAtual;

}