package app.jm.funcional.model.entidades.contas;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import app.jm.funcional.model.Tabela;

public class ContaPagar extends Tabela {

    private double valor;
    private Calendar vencimento;
    private int tipoConta;


 
    @Override
    public List<Tabela> getListValoresIniciais() {
        return null;
    }
}
