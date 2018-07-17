package app.jm.funcional.model.entidades.cadastral;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.entidades.Entidade;
import app.jm.funcional.model.entidades.estoque.Grupo;

public class Municipio extends Entidade implements Serializable {

	private String nome_municipio;
	private Estado estado;
	
	@Override
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome_municipio;
	}
	public void setNome(String nome) {
		this.nome_municipio = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public boolean usaInsert() {
		return false;
	}
	
	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Municipio>>(){}.getType();
	}
}
