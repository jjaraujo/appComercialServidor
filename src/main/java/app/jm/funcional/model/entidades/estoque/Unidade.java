package app.jm.funcional.model.entidades.estoque;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.entidades.Entidade;

public class Unidade extends Entidade{
    private String nome_unidade;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//
//        id = (int) map.get(getIdNome());
//        nome_unidade = (String) map.get("nome_unidade");
//    }
    
	@Override
	public long getId() {
		return id;
	}

    public String getNome_unidade() {
        return nome_unidade;
    }

    public void setNome_unidade(String nome_unidade) {
        this.nome_unidade = nome_unidade;
    }

    
    @Override
	public Type typeParaJson() {
		return new TypeToken<List<Unidade>>(){}.getType();
	}
    
}
