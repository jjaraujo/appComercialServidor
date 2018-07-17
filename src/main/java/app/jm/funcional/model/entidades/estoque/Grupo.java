package app.jm.funcional.model.entidades.estoque;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.Entidade;

public class Grupo extends Entidade{
    private String nome_grupo;

    
	@Override
	public long getId() {
		return id;
	}

    public String getNome_grupo() {
        return nome_grupo;
    }

    public void setNome_grupo(String nome_grupo) {
        this.nome_grupo = nome_grupo;
    }
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Grupo>>(){}.getType();
	}
}
