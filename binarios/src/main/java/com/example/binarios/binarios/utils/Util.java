package com.example.binarios.binarios.utils;

import java.util.Base64;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	
	final Long ate = Long.valueOf(100);
	
	public static Long gerarIdRandomico() {
		Random random = new Random();
		long nextLong = random.nextInt(100);
		return nextLong;
	}
	
	public static String gerarSenhaRandomica(){
		int qtdeMaximaCaracteres = 8;
	    String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
	                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
	                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
	                "x", "y", "z"};
	    
		StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
        
	}
	
	public static String getBase64Encoder(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 

}
