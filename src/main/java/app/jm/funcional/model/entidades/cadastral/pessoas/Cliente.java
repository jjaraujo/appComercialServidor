package app.jm.funcional.model.entidades.cadastral.pessoas;


import app.jm.funcional.model.Tabela;
import java.util.List;

public class Cliente extends APessoa{

    
	
    private double limite;
    private String ultimaVenda;

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
}
