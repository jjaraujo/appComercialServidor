package com.jmdesenvolvimento.appcomercial.model;

import java.util.ArrayList;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.controller.NaoUsarNaBase;
import com.jmdesenvolvimento.appcomercial.model.Tabela;

public class Dispositivo extends Tabela{
	
	@NaoUsarNaBase
	private long id;
	
	private String token;

	public Dispositivo(String token) {
		this.token = token;
	}
	
	public Dispositivo (){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> alterTableAposCriacao() {
		
		List<String> list = new ArrayList<String>();
		list.add( "ALTER TABLE aplicacaocomercial.dbo.Dispositivo ALTER COLUMN token varchar(300);");
		
		return super.alterTableAposCriacao();
	}

}
