package com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias;

import java.util.HashMap;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.TipoPagamento;

public class TabelaPagamento extends TabelaIntermediaria {
    private long venda;
    private TipoPagamento tipoPagamento;
    private double valor;
    private List<TabelaParcelasPagamento> tabelaParcelasPagamento;

    public TabelaPagamento(long venda){
        this.venda = venda;
    }
    public TabelaPagamento(){
    }

    @Override
    public void setMapAtributos(HashMap<String, Object> map) {
        id = (int) map.get(getIdNome());
        tipoPagamento = (TipoPagamento) map.get("tipoPagamentos");//+ FuncoesGerais.prefixoChaveEstrangeira());
        venda = (int) map.get("venda");
        valor = (double) map.get("valor");
        tabelaParcelasPagamento = (List<TabelaParcelasPagamento>) map.get("tabelaParcelasPagamento");
    }

    public long getVenda() {
        return venda;
    }

    public void setVenda(long venda) {
        this.venda = venda;
    }

    public TipoPagamento getTipoPagamentos() {
        return tipoPagamento;
    }

    public void setTipoPagamentos(TipoPagamento tipoPagamentos) {
        this.tipoPagamento = tipoPagamentos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<TabelaParcelasPagamento> getTabelaParcelasPagamento() {
        return tabelaParcelasPagamento;
    }

    public void seTabelaParcelasPagamento(List<TabelaParcelasPagamento>  tabelaParcelasPagamento) {
        this.tabelaParcelasPagamento = tabelaParcelasPagamento;
    }
}
