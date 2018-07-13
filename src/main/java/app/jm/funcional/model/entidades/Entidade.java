package app.jm.funcional.model.entidades;


import java.util.HashMap;
import java.util.List;

import app.jm.funcional.model.Tabela;

public abstract class Entidade extends Tabela{
  //  protected HashMap<String, Object> map = new HashMap<>();

  
    @Override
    public String toString() {
        return this.getId() + "";
    }

    @Override
    public List<Tabela> getListValoresIniciais() {
        return null;
    }

    public String getNomeChaveEstrangeira(){
     return this.getNomeTabela(false);//) + FuncoesGerais.prefixoChaveEstrangeira();
    }
}
