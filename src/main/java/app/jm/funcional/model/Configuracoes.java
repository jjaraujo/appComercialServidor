package app.jm.funcional.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.model.Tabela;

public class Configuracoes extends Tabela{

    private boolean vendaMesaComanda;
    private boolean vendaSemEstoque;
    private boolean backupEmNuvem;
    private String nomeTipoVenda;
    private int numeroDeMesasComandas;
    private String activityInicial;


    @Override
    public List<Tabela> getListValoresIniciais() {
        this.vendaMesaComanda = false;
        this.backupEmNuvem = true;
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
    
	public void setActivityInicial(String activityInicial) {
		this.activityInicial = activityInicial;
	}
	

	public boolean isBackupEmNuvem() {
		return backupEmNuvem;
	}

	public void setBackupEmNuvem(boolean backupEmNuvem) {
		this.backupEmNuvem = backupEmNuvem;
	}
	 
    @Override
    public boolean usaInsert() {
    	if(VariaveisControle.tipoSql == FuncoesSql.SQL_SERVER)
    		return false;
    	
    	return true;
    }
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Configuracoes>>(){}.getType();
	}
}
