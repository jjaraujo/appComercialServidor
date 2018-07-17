package app.jm.funcional.model.filtrosOrdenacao;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.pessoas.Cliente;

public class FiltroContasReceber extends Tabela {

    private Cliente cliente;
    private Calendar dataInicio;
    private Calendar dataFim;
    private double valorMinimo;
    private double valorMaximo;

    @Override
    public List<Tabela> getListValoresIniciais() {
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
    

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<FiltroContasReceber>>(){}.getType();
	}
}
