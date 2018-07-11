package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;

import com.jmdesenvolvimento.appcomercial.model.Tabela;

public abstract class APessoa extends Tabela{
	
	public Pessoa pessoa;
	
	public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
