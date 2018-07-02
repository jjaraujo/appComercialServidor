package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;

import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;

public class Funcionario  extends Tabela{

    private Pessoa pessoa;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        pessoa = (Pessoa) map.get(pessoa.getIdNome());
//    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
}
