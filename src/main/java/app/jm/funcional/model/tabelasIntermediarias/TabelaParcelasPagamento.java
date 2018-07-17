package app.jm.funcional.model.tabelasIntermediarias;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;

public class TabelaParcelasPagamento extends TabelaIntermediaria {

    private int numeroParcela;
    private double valor;
    private Calendar data;
    private long tabelaPagamento;
    private int totalParcelas;

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public long getTabelaPagamento() {
        return tabelaPagamento;
    }

    public void setTabelaPagamento(long tabelaPagamento) {
        this.tabelaPagamento = tabelaPagamento;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(int totalParcelas) {
        this.totalParcelas = totalParcelas;
    }
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<TabelaParcelasPagamento>>(){}.getType();
	}

}
