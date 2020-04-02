package com.example.binarios.binarios.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Documento {

	@Id
	private Long id;
	
	@Lob
	@Column(length = 32000)
	private String documentoEsquerdo;
	
	@Lob
	@Column(length = 32000)
	private String documentoDireito;
	
	public Documento() {
	}

	public Documento(Long id, String arquivoEsquerdo, String arquivoDireito) {
		setId(id);
		setDocumentoEsquerdo(arquivoEsquerdo);
		setDocumentoDireito(arquivoDireito);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentoEsquerdo() {
		return documentoEsquerdo;
	}

	public void setDocumentoEsquerdo(String documentoEsquerdo) {
		this.documentoEsquerdo = documentoEsquerdo;
	}

	public String getDocumentoDireito() {
		return documentoDireito;
	}

	public void setDocumentoDireito(String documentoDireito) {
		this.documentoDireito = documentoDireito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentoDireito == null) ? 0 : documentoDireito.hashCode());
		result = prime * result + ((documentoEsquerdo == null) ? 0 : documentoEsquerdo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (documentoDireito == null) {
			if (other.documentoDireito != null)
				return false;
		} else if (!documentoDireito.equals(other.documentoDireito))
			return false;
		if (documentoEsquerdo == null) {
			if (other.documentoEsquerdo != null)
				return false;
		} else if (!documentoEsquerdo.equals(other.documentoEsquerdo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", documentoEsquerdo=" + documentoEsquerdo + ", documentoDireito="
				+ documentoDireito + "]";
	}
}
