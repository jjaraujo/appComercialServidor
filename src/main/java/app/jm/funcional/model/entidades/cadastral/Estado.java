package app.jm.funcional.model.entidades.cadastral;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.Entidade;
import app.jm.funcional.model.entidades.estoque.Grupo;

public class Estado extends Entidade implements Serializable {
	
	private String nome_estado;

	public String getNome_estado() {
		return nome_estado;
	}
	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Estado>>(){}.getType();
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + " "+ nome_estado;
	}
	
	@Override
	public List<Tabela> getListValoresIniciais() {
		String[] estados = {"AM","PA"};
		List<Tabela> t = new ArrayList<Tabela>();
		for(int i = 0; i < estados.length; i ++) {
			Estado estado = new Estado();
			estado.setId(i+1);
			estado.setNome_estado(estados[i]);
			t.add(estado);
		}
		return t;
	}
	
	@Override
	public boolean getPrecisaRegistroInicial() {
		
		return true;
	}
	
	@Override
	public boolean usaInsert() {
		return false;
	}
}
