package app.jm.funcional.model.tabelasIntermediarias;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.vendas.TipoPagamento;

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
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<TabelaPagamento>>(){}.getType();
	}
}
