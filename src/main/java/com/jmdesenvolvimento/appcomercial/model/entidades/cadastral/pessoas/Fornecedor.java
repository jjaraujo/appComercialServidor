package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;

import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;

public class Fornecedor  extends Tabela {

    public Pessoa getPessoa() {
        if(pessoa == null){
            return new Pessoa();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    private Pessoa pessoa;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        pessoa = (Pessoa) map.get("pessoa");//+ FuncoesGerais.prefixoChaveEstrangeira());
//    }

    @Override
    public String toString() {
        if(pessoa == null){
            return "Pessoa não Instanciada";
        }
        return pessoa.getNome();
    }

	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
}
