package com.jmdesenvolvimento.appcomercial.model.entidades.vendas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.Entidade;

public class TipoPagamento extends Entidade{
    private String nome;
    private boolean aceito;
    private int tipoIcone;
    private boolean aceitaParcela;
    public final static int CARTAO = 1;
    public final static int CHEQUE = 2;
    public final static int DINHEIRO = 3;
    public final static int BOLETO = 4;
    public final static int FIADO = 5;

    public TipoPagamento(int id,String nome, int tipoIcone, boolean aceitaParcela){
        this.nome = nome;
        this.tipoIcone = tipoIcone;
        aceito = true;
        this.aceitaParcela = aceitaParcela;
        this.id = id;
    }
    
    public TipoPagamento() {
    	
    }

	@Override
	public long getId() {
		return id;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Integer getIdIcone() {
        return tipoIcone;
    }

    public boolean isAceitaParcela() {
        return aceitaParcela;
    }

    public void setAceitaParcela(boolean aceitaParcela) {
        this.aceitaParcela = aceitaParcela;
    }

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        nome = (String) map.get("nome");
//        aceito = (boolean) map.get("aceito");
//        tipoIcone = (int) map.get("tipoIcone");
//        aceitaParcela = (boolean) map.get("aceitaParcela");
//    }

    @Override
    public boolean getPrecisaRegistroInicial() {
        return true;
    }

    @Override
    public List<Tabela> getListValoresIniciais() {
        //"R.drawable.icone_cartao"
        List<Tabela> list = new ArrayList<>();
        TipoPagamento cartaoCredito = new TipoPagamento(1,"Cartão de Crédito",CARTAO,false);
        list.add(cartaoCredito);
        TipoPagamento cartaoDebito = new TipoPagamento(2,"Cartão de Débito",CARTAO,false);
        list.add(cartaoDebito);
        TipoPagamento dinheiro = new TipoPagamento(3,"Dinheiro",DINHEIRO,false);
        list.add(dinheiro);
        TipoPagamento cheque = new TipoPagamento(4,"Cheque",CHEQUE,true);
        list.add(cheque);
        TipoPagamento boleto = new TipoPagamento(5,"Boleto",BOLETO,true);
        list.add(boleto);
        TipoPagamento alimentacao = new TipoPagamento(6,"Alimentação/Refeição",CARTAO,false);
        list.add(alimentacao);
        TipoPagamento fiado = new TipoPagamento(7,"Fiado",FIADO,true);
        list.add(fiado);
        return list;
    }
}
