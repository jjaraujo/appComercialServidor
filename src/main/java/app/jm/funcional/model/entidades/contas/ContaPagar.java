package app.jm.funcional.model.entidades.contas;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.estoque.Grupo;

public class ContaPagar extends Tabela {

    private double valor;
    private Calendar vencimento;
    private int tipoConta;
    public static final String[] TIPOS_CONTA = {""};


 
    @Override
	public Type typeParaJson() {
		return new TypeToken<List<ContaPagar>>(){}.getType();
	}
}
