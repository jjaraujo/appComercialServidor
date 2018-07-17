package app.jm.funcional.model.entidades.estoque;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.Entidade;

public class Cst extends Entidade{


	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Csons>>(){}.getType();
	}
}
