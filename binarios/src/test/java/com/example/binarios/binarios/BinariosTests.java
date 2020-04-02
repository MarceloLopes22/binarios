package com.example.binarios.binarios;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.binarios.binarios.basicas.Documento;
import com.example.binarios.binarios.controller.DocumentoRestController;
import com.example.binarios.binarios.conversor.JSONObject;
import com.example.binarios.binarios.dao.DocumentoRepository;
import com.example.binarios.binarios.servico.DocumentoService;
import com.example.binarios.binarios.utils.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@SuppressWarnings("unused")
public class BinariosTests {

	private static final String V1_DIFF_URL = "/v1/diff/";

	private static final String DOCUMENTOS_9999_IDENTICOS = "Documentos 9999 idênticos";

	private static final String DOCUMENTOS_9998_COM_TAMANHOS_DIFERENTES = "Documentos 9998 com tamanhos diferentes";

	private static final String OS_DOCUMENTOS_SAO_DIFERENTES_NA_POSICAO_0 = "Os documentos são diferentes na posição : 0";

	private static final String DOCUMENTO_DO_LADO_DIREITO_SALVO = "Documento do lado direito salvo!";

	private static final String DOCUMENTO_DO_LADO_ESQUERDO_SALVO = "Documento do lado esquerdo salvo!";

	private static final String MENSAGEM_VARIAVEL = "$.mensagem";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DocumentoRestController documentoRestController;

	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Test
    public void testEsquerdo() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.setDado(Util.getBase64Encoder(Util.gerarSenhaRandomica().getBytes()));
		
        Long idRandomico  = Util.gerarIdRandomico();
		mockMvc.perform(post(V1_DIFF_URL+ idRandomico + "/esquerdo")
        		.content(Util.asJsonString(jsonObject))
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(MENSAGEM_VARIAVEL).value(DOCUMENTO_DO_LADO_ESQUERDO_SALVO));
    }
	
	@Test
    public void testDireito() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.setDado(Util.getBase64Encoder(Util.gerarSenhaRandomica().getBytes()));
		
        Long idRandomico  = Util.gerarIdRandomico();
		mockMvc.perform(post(V1_DIFF_URL+ idRandomico + "/direito")
        		.content(Util.asJsonString(jsonObject))
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(MENSAGEM_VARIAVEL).value(DOCUMENTO_DO_LADO_DIREITO_SALVO));
    }
	
	@Test
	public void testDiferencaIdenticos() throws Exception {
		String encoder = Util.getBase64Encoder(Util.gerarSenhaRandomica().getBytes());
		Documento documento = new Documento();
		documento.setId(Long.valueOf(9999));
		documento.setDocumentoDireito(encoder);
		documento.setDocumentoEsquerdo(encoder);
		
		documentoRepository.save(documento);
		
		mockMvc.perform(get(V1_DIFF_URL+ 9999)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(MENSAGEM_VARIAVEL).value(DOCUMENTOS_9999_IDENTICOS));
	}
	
	@Test
	public void testDiferencaTamanhoDiferentes() throws Exception {
		String encoder = Util.getBase64Encoder(Util.gerarSenhaRandomica().getBytes());
		Documento documento = new Documento();
		documento.setId(Long.valueOf(9998));
		documento.setDocumentoDireito(encoder);
		documento.setDocumentoEsquerdo(Util.getBase64Encoder("TamanhoDiferentes".getBytes()));
		
		documentoRepository.save(documento);
		
		mockMvc.perform(get(V1_DIFF_URL+ 9998)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(MENSAGEM_VARIAVEL).value(DOCUMENTOS_9998_COM_TAMANHOS_DIFERENTES));
	}
	
	@Test
	public void testDiferencaEmPosicoes() throws Exception {
		String encoder = Util.getBase64Encoder("tamanhoDiferentes".getBytes());
		Documento documento = new Documento();
		documento.setId(Long.valueOf(9997));
		documento.setDocumentoDireito(encoder);
		documento.setDocumentoEsquerdo(Util.getBase64Encoder("TamanhoDiferentes".getBytes()));
		
		documentoRepository.save(documento);
		
		mockMvc.perform(get(V1_DIFF_URL+ 9997)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(MENSAGEM_VARIAVEL).value(OS_DOCUMENTOS_SAO_DIFERENTES_NA_POSICAO_0));
	}

}
