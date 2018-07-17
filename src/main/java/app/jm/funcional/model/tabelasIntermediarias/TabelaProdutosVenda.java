package app.jm.funcional.model.tabelasIntermediarias;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.estoque.Produto;

public class TabelaProdutosVenda extends TabelaIntermediaria {

    private long venda;
    private Produto produto;
    private int qtd;
    private Calendar dataCadastro;
    private Calendar dataCancelamento;
    private String motivoCancelamento;

 

    public long getVenda() {
        return venda;
    }

    public void setVenda(long venda) {
        this.venda = venda;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Calendar getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Calendar dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    @Override
    public String toString() {
        return produto.getId()+ " " + produto.getNome_produto() ;
    }
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<TabelaProdutosVenda>>(){}.getType();
	}
}
