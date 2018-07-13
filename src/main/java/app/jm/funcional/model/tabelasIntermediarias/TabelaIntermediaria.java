package app.jm.funcional.model.tabelasIntermediarias;

import java.util.HashMap;
import java.util.List;

import app.jm.funcional.model.Tabela;

public abstract class TabelaIntermediaria extends Tabela {

    public abstract  void setMapAtributos(HashMap<String, Object> map);

    @Override
    public List<Tabela> getListValoresIniciais() {
        return null;
    }
}
