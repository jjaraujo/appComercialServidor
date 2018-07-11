package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.Entidade;

public class Estado extends Entidade implements Serializable {
	
	private String nome_estado;

	public String getNome_estado() {
		return nome_estado;
	}
	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}

//	@Override
//	public void setMapAtributos(HashMap<String, Object> map) {
//		id = (Integer) map.get(getIdNome());
//		nome_estado = (String) map.get("nome_estado");
//	}
	
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
