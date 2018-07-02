package com.jmdesenvolvimento.appcomercial.controller.funcoesGerais;

import java.util.Calendar;

import com.jmdesenvolvimento.appcomercial.model.dao.IConnection;
import com.jmdesenvolvimento.appcomercial.model.entidades.contas.ContaReceber;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.Venda;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaParcelasPagamento;

public class FuncoesContas {

    public static void criaContaReceber(IConnection dao, Venda venda, TabelaPagamento tabelaPagamento, TabelaParcelasPagamento parcela){
        ContaReceber contaReceber = new ContaReceber();
        contaReceber.setTipoPagamento(tabelaPagamento.getTipoPagamentos());
        contaReceber.setParcelasPagamento(parcela);
        contaReceber.setCliente(venda.getCliente());
        contaReceber.setDataVencimento(parcela.getData());
        dao.insert(contaReceber);
        dao.close();
    }

}
