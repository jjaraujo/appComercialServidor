package app.jm.funcional.model.entidades.estoque;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.entidades.Entidade;

public class TipoItem extends Entidade implements Serializable {
    String nome_tipo_item;

    public String getNome_tipo_item() {
        return nome_tipo_item;
    }

    public void setNome_tipo_item(String nome_tipo_item) {
        this.nome_tipo_item = nome_tipo_item;
    }

    @Override
	public Type typeParaJson() {
		return new TypeToken<List<TipoItem>>(){}.getType();
	}
}
