package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import br.com.churchmanager.util.AES;

@MappedSuperclass
public class EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "versao", nullable = false)
	private int versao;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	public EntidadeGenerica() {
		this.status = Status.ATIVO;
	}

	public String idCriptografado() {
		AES.encrypt(this.id.toString());
		return AES.getEncryptedString().replace("/", "@").replace("=", "");
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int hashCode() {
		byte result = 1;
		int result1 = 31 * result + (this.id == null ? 0 : this.id.hashCode());
		return result1;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			EntidadeGenerica other = (EntidadeGenerica) obj;
			if (this.id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!this.id.equals(other.id)) {
				return false;
			}

			return true;
		}
	}
}