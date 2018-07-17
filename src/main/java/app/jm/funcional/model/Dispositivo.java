package app.jm.funcional.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.controller.NaoUsarNaBase;
import app.jm.funcional.model.Tabela;

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
	public boolean isbackup() {
		return false;
	}
	
	@Override
	public List<String> alterTableAposCriacao() {
		
		List<String> list = new ArrayList<String>();
		list.add( "ALTER TABLE aplicacaocomercial.dbo.Dispositivo ALTER COLUMN token varchar(300);");
		
		return super.alterTableAposCriacao();
	}

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Dispositivo>>(){}.getType();
	}

}
