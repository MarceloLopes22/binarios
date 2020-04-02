package com.example.binarios.binarios.servico;

import java.util.Arrays;
import java.util.Optional;

import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binarios.binarios.basicas.Documento;
import com.example.binarios.binarios.dao.DocumentoRepository;
import com.example.binarios.binarios.enuns.Posicao;

@Service
public class DocumentoService {
	
	@Autowired
	public DocumentoRepository repository;

	
	public Documento save(Long id, String dado, Posicao posicao) throws Exception {
		
		Documento documento = null;
		
		if(validarDados(id, dado)) {
			
			Optional<Documento> optional = repository.findById(id);
			
			if (!optional.isPresent()) {
				documento = new Documento();
				documento.setId(id);
			} else {
				documento = optional.get();
			}
	
			if(Posicao.ESQUERDO.equals(posicao)) {
				
				documento.setDocumentoEsquerdo(dado);
				
			} else if(Posicao.DIREITO.equals(posicao)) {
				
				documento.setDocumentoDireito(dado);
			
			}
			
			documento = repository.save(documento);
			
		}
		return documento;
	}
	
	public boolean validarDados(Long id, String dado) throws ValidationException {
		boolean isValid = true;
		
		if (StringUtils.isEmpty(dado)) {
			isValid = false;
		}
		return isValid;
	}
	
	public String validarDadoBase64(Long id) {
		
		Optional<Documento> optional = repository.findById(id);
		
		String mensagem = "";
		
		if (optional.isPresent()) {
			
			Documento documento = optional.get();
			
			byte[] bytesDocEsquerdo = documento.getDocumentoEsquerdo().getBytes();
			byte[] bytesDocDireito = documento.getDocumentoDireito().getBytes();
			
			boolean saoIguais = Arrays.equals(bytesDocEsquerdo, bytesDocDireito);

			String caracteres = "";
			
			if (saoIguais) {
				
				mensagem = "Documentos "+ documento.getId() +" idênticos";
				
			} else if (bytesDocEsquerdo.length != bytesDocDireito.length) {
				
				mensagem = "Documentos "+ documento.getId() +" com tamanhos diferentes";
				
			} else {
				
				byte diferenca = 0;
				
				for (int index = 0; index < bytesDocEsquerdo.length; index++) {
					
					diferenca = (byte) (bytesDocEsquerdo[index] ^ bytesDocDireito[index]);
					
					if (diferenca != 0) {
						caracteres = caracteres + " " + index;
					}
				}
				mensagem = "Os documentos são diferentes na posição :" + caracteres;
			}
		}
		
		return mensagem;
	}
}
