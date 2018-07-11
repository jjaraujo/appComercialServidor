package com.jmdesenvolvimento.appcomercial.controller.funcoesGerais;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;

public class EstrategiaLogin {
	
	
	public static String criptografaSenha(String senha) {
		try {
			byte[] bytes = senha.getBytes();
			MessageDigest md =	MessageDigest.getInstance("MD5");
			 md.digest(bytes);
			return String.valueOf(new BigInteger(1,md.digest()).toString(16));
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	
}
