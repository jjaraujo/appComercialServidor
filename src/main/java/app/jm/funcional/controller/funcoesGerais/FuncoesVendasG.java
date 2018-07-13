package app.jm.funcional.controller.funcoesGerais;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.contas.ContaReceber;
import app.jm.funcional.model.entidades.vendas.Venda;
import app.jm.funcional.model.tabelasIntermediarias.TabelaPagamento;
import app.jm.funcional.model.tabelasIntermediarias.TabelaParcelasPagamento;
import app.jm.funcional.controller.VariaveisControle;


public final class FuncoesVendasG {

    public static void finalizaVenda(IConnection dao){
        Venda vendaSelecionada = VariaveisControle.vendaSelecionada;
        for(TabelaPagamento t : vendaSelecionada.getTabelaPagamentos()){
            t.setId(dao.countIdEntidade(t)+1); // ID adicionado aqui para que possa ser inserido da TabelaParcelasPagamento
            for(TabelaParcelasPagamento parcelaPagamento : t.getTabelaParcelasPagamento()){
                parcelaPagamento.setTabelaPagamento(t.getId());
                parcelaPagamento.setTotalParcelas(t.getTabelaParcelasPagamento().size());
                dao.insert(parcelaPagamento);

                if(t.getTipoPagamentos().isAceitaParcela()) {
                    String dataPagamento = FuncoesGerais.calendarToString(Calendar.getInstance(), FuncoesGerais.ddMMyyyy, false);
                    Calendar dataVencimento = parcelaPagamento.getData();
                    String dataVencimentoS =  FuncoesGerais.calendarToString(dataVencimento,FuncoesGerais.ddMMyyyy,false);
                    if ( !dataPagamento.equals(dataVencimentoS)) {
                        FuncoesContas.criaContaReceber(dao, vendaSelecionada, t, parcelaPagamento);
                    }
                }
            }
            dao.insert(t);
        }
        vendaSelecionada.setDataFechamento(Calendar.getInstance());
        dao.update(vendaSelecionada);
    }

    /**Método para criar vendas por cliente ou comanda/mesa*/
    public static void criaVenda(IConnection dao, Venda venda){
        VariaveisControle.vendaSelecionada = venda;
        venda.setTabelaPagamentos(new ArrayList<TabelaPagamento>());
        venda.setDataRegistro(Calendar.getInstance());
        venda.setMapAtributos(venda.getMapAtributos(true));
        dao.insert(venda);
        dao.close();
     //   VariaveisControleAndroid.fragmentVendasAbertas.carregaLista();
      //  VariaveisControleAndroid.fragmentProdutos.carregaLista();
    }
}
