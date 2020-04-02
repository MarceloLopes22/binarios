package com.example.binarios.binarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.binarios.binarios.conversor.JSONObject;
import com.example.binarios.binarios.enuns.Posicao;
import com.example.binarios.binarios.servico.DocumentoService;

@RestController
@RequestMapping("/v1/diff/{id}")
@CrossOrigin("*")
public class DocumentoRestController {

	@Autowired
	private DocumentoService service;
	
	@RequestMapping(value = "/esquerdo", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<String> esquerdo(@PathVariable Long id, @RequestBody JSONObject dado) throws Exception {
		
		service.save(id, dado.getDado(), Posicao.ESQUERDO);
		
		String mensagem = montarRespostaJSON("Documento do lado esquerdo salvo!");
		return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/direito", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<String> direito(@PathVariable Long id, @RequestBody JSONObject dado) throws Exception {
		
		service.save(id, dado.getDado(), Posicao.DIREITO);
		
		String mensagem = montarRespostaJSON("Documento do lado direito salvo!");
		return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<String> diferenca(@PathVariable Long id) {
		return new ResponseEntity<String>(montarRespostaJSON(service.validarDadoBase64(id)), HttpStatus.OK);
	}
	
	private String montarRespostaJSON(String mensagem) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("mensagem");
		sb.append(":");
		sb.append("");
		sb.append(mensagem);
		sb.append("");
		sb.append("}");
		return sb.toString();
	}
}
