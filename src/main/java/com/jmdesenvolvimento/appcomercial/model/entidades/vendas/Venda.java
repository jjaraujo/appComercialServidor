package com.jmdesenvolvimento.appcomercial.model.entidades.vendas;

import java.util.Calendar;
import java.util.List;

import com.jmdesenvolvimento.appcomercial.controller.funcoesGerais.FuncoesGerais;
import com.jmdesenvolvimento.appcomercial.model.entidades.Entidade;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Cliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Funcionario;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaProdutosVenda;

public class Venda extends Entidade {

    private Cliente cliente;
    private Funcionario funcionario;
    private int numeroMesaComanda;
    private List<TabelaProdutosVenda> tabelaProdutosVenda;
    private Calendar dataRegistro;
    private List<TabelaPagamento> tabelaPagamentos;
    private Calendar dataFechamento;
    private Calendar dataCancelamento;
    private String motivoCancelamento;
    private double desconto;

    public Venda (int numeroMesaComanda){
        this.numeroMesaComanda = numeroMesaComanda;
    }

    public Venda(){

    }
    
    @Override
    public long getId() {
 
    	return id == 0 ? FuncoesGerais.getIdUnicoVenda() : id;
    }

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        cliente = (Cliente) map.get("cliente");//+ FuncoesGerais.prefixoChaveEstrangeira());
//        dataRegistro = (Calendar) map.get("dataRegistro");
//        tabelaPagamentos =(List<TabelaPagamento>) map.get("tabelaPagamentos");
//        tabelaProdutosVenda = ( List<TabelaProdutosVenda>) map.get("tabelaProdutosVenda");
//        dataFechamento = (Calendar) map.get("dataFechamento");
//        dataCancelamento = (Calendar) map.get("dataCancelamento");
//        motivoCancelamento = (String) map.get("motivoCancelamento");
//        numeroMesaComanda = (int) map.get("numeroMesaComanda");
//        desconto = (double) map.get("desconto");
//        vendedor = (Vendedor) map.get("vendedor");
//    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<TabelaProdutosVenda> getTabelaProdutosVenda() {
        return tabelaProdutosVenda;
    }

    public void setTabelaProdutosVenda(List<TabelaProdutosVenda> tabelaProdutosVenda) {
        this.tabelaProdutosVenda = tabelaProdutosVenda;
    }

    public int getNumeroMesaComanda() {
        return numeroMesaComanda;
    }

    public void setNumeroMesaComanda(int numeroMesaComanda) {
        this.numeroMesaComanda = numeroMesaComanda;
    }

    public Calendar getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Calendar dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<TabelaPagamento> getTabelaPagamentos() {
        return tabelaPagamentos;
    }

    public void setTabelaPagamentos(List<TabelaPagamento> tabelaPagamentos) {
        this.tabelaPagamentos = tabelaPagamentos;
    }

    public Calendar getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Calendar dataFechamento) {
        this.dataFechamento = dataFechamento;
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

    public void removePagamento(TabelaPagamento tabelaPagamento){
        getTabelaPagamentos().remove(tabelaPagamento);
   //     VariaveisControleG.valorRestante = VariaveisControleG.valorRestante + tabelaPagamento.getValor();
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return getId() + " - EmpresaCliente: " + cliente.getId();
    }
    
    
}
