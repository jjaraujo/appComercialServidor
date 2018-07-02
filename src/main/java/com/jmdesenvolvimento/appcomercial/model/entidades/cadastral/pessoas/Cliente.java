package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;


import com.jmdesenvolvimento.appcomercial.model.Tabela;
import java.util.List;

public class Cliente extends Tabela{

    private Pessoa pessoa;
    private double limite;
    private String ultimaVenda;


    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getUltimaVenda() {
        return ultimaVenda;
    }

    public void setUltimaVenda(String ultimaVenda) {
        this.ultimaVenda = ultimaVenda;
    }

    @Override
    public String toString() {
        if(pessoa == null){
            return super.toString() +" Pessoa nula";
        }
        return getPessoa().getNome() + " CPF: " + pessoa.getCpfCNPJ();
    }


	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
}
