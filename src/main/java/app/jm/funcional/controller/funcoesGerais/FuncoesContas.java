package app.jm.funcional.controller.funcoesGerais;

import java.util.Calendar;

import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.contas.ContaReceber;
import app.jm.funcional.model.entidades.vendas.Venda;
import app.jm.funcional.model.tabelasIntermediarias.TabelaPagamento;
import app.jm.funcional.model.tabelasIntermediarias.TabelaParcelasPagamento;

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
