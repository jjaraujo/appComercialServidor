package app.jm.funcional.model.entidades.estoque;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.Entidade;

public class Csons extends Entidade implements Serializable {
    private String nome_csons;

    public String getNome_csons() {
        return nome_csons;
    }

    public void setNome_csons(String nome_csons) {
        this.nome_csons = nome_csons;
    }

    
	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Csons>>(){}.getType();
	}
    
}
