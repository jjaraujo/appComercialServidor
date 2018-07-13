package app.jm.funcional.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.model.Tabela;

public class Configuracoes extends Tabela{

    private boolean vendaMesaComanda;
    private boolean vendaSemEstoque;
    private String nomeTipoVenda;
    private int numeroDeMesasComandas;
    private String activityInicial;



//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        vendaMesaComanda = (boolean) map.get("vendaMesaComanda");
//        vendaSemEstoque = (boolean) map.get("vendaSemEstoque");
//        dataExclusao = (Calendar) map.get("dataExclusao");
//        nomeTipoVenda = (String) map.get("nomeTipoVenda");
//        numeroDeMesasComandas = (int) map.get("numeroDeMesasComandas");
//    }

    @Override
    public List<Tabela> getListValoresIniciais() {
        this.vendaMesaComanda = true;
        this.vendaSemEstoque = false;
        this.nomeTipoVenda = "Comanda";
        List list = new ArrayList();
        list.add(this);
        return list;
    }

    public void setVendaMesaComanda(boolean vendaMesaComanda) {
        this.vendaMesaComanda = vendaMesaComanda;
    }

    public void setVendaSemEstoque(boolean vendaSemEstoque) {
        this.vendaSemEstoque = vendaSemEstoque;
    }

    public String getNomeTipoVenda() {
        return nomeTipoVenda;
    }

    public void setNomeTipoVenda(String nomeTipoVenda) {
        this.nomeTipoVenda = nomeTipoVenda;
    }

    public boolean isVendaMesaComanda() {
        return vendaMesaComanda;
    }

    public boolean isVendaSemEstoque() {
        return vendaSemEstoque;
    }

    public  boolean getPrecisaRegistroInicial(){
        return true;
    }

    public int getNumeroDeMesasComandas() {
        return numeroDeMesasComandas;
    }

    public void setNumeroDeMesasComandas(int numeroDeMesasComandas) {
        this.numeroDeMesasComandas = numeroDeMesasComandas;
    }
    
    public String getActivityInicial() {
    	return this.activityInicial;
    }
    
    @Override
    public boolean usaInsert() {
    	if(VariaveisControle.tipoSql == FuncoesSql.SQL_SERVER)
    		return false;
    	
    	return true;
    }
}
