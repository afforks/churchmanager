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
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MonetarioUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "dizimo")
@Table(name = "dizimo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
public class Dizimo extends EntidadeGenerica implements Serializable {
	
	private static final long serialVersionUID = 4517030116936446515L;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	
	@DecimalMin("0.01")
	@Column(name = "valor_dizimo", nullable = false)
	private double valorDizimo;
	
	@Column(name = "valor_oferta", nullable = false)
	private double valorOferta;
	
	@Transient
	private double valorTotal;
	
	@Builder.Default
	@Temporal(TemporalType.DATE)
	@Column(name = "data_referencia", nullable = false)
	private Date dataReferencia = DataUtil.mesAnterior(new Date());
	
	@Builder.Default
	@Temporal(TemporalType.DATE)
	@Column(name = "data_recebimento", nullable = false)
	private Date dataRecebimento = new Date();
	
	@Lob
	@Column(name = "observacao")
	private String observcao;
	
	@Builder.Default
	@Type(type = "true_false")
	@Column(name = "is_13")
	private boolean is13 = false;

	

	public double getValorTotal() {
		this.valorTotal = this.valorDizimo + this.valorOferta;
		return this.valorTotal;
	}

	

	public String toString() {
		return DataUtil.dateParaString(this.dataRecebimento) + ": "
				+ MonetarioUtil.formatarReal(this.valorDizimo + this.valorOferta);
	}
}