package app.jm.funcional.model.entidades.cadastral.pessoas;

import app.jm.funcional.model.Tabela;

public abstract class APessoa extends Tabela{
	
	public Pessoa pessoa;
	
	public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
