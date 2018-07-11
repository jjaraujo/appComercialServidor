package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;


import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.Entidade;

public class Funcionario  extends AUsuario{

    private double comissao;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get("id_vendedor");
//        pessoa = (Pessoa) map.get("pessoa");
//        comissao = (double) map.get("comissao");
//        usuario = (String) map.get("usuario");
//        senha = (String) map.get("senha");
//    }
    
    @Override
    public long getId() {
    	return id;
    }
    
    public Double getComissao(){
        return this.comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }


	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
}
