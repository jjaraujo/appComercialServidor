package app.jm.funcional.controller.funcoesGerais;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.contas.ContaReceber;
import app.jm.funcional.model.entidades.vendas.Caixa;
import app.jm.funcional.model.entidades.vendas.Venda;
import app.jm.funcional.model.tabelasIntermediarias.TabelaPagamento;
import app.jm.funcional.model.tabelasIntermediarias.TabelaParcelasPagamento;
import app.jm.funcional.controller.VariaveisControle;

public final class FuncoesVendasG {

	public static void finalizaVenda(IConnection dao) {
		Caixa caixa = new Caixa();
		Venda vendaSelecionada = VariaveisControle.vendaSelecionada;
		for (TabelaPagamento tabelaPag : vendaSelecionada.getTabelaPagamentos()) {
			tabelaPag.geraId(dao); // ID adicionado aqui para que possa ser inserido da TabelaParcelasPagamento

			if (tabelaPag.getTipoPagamentos().isAceitaParcela()) {

				for (TabelaParcelasPagamento parcelaPagamento : tabelaPag.getTabelaParcelasPagamento()) {

					parcelaPagamento.setTabelaPagamento(tabelaPag.getId());
					parcelaPagamento.setTotalParcelas(tabelaPag.getTabelaParcelasPagamento().size());
					dao.insert(parcelaPagamento);
					String dataPagamento = FuncoesGerais.calendarToString(Calendar.getInstance(),
							FuncoesGerais.ddMMyyyy, false);
					Calendar dataVencimento = parcelaPagamento.getData();
					String dataVencimentoS = FuncoesGerais.calendarToString(dataVencimento, FuncoesGerais.ddMMyyyy,
							false);
					if (!dataPagamento.equals(dataVencimentoS)) {
						FuncoesContas.criaContaReceber(dao, vendaSelecionada, tabelaPag, parcelaPagamento);
					}
				}
				vendaSelecionada.setValorTotalAPrazo(vendaSelecionada.getValorTotalAPrazo() + tabelaPag.getValor());
			} else {
				vendaSelecionada.setValorTotalAvista(vendaSelecionada.getValorTotalAvista() + tabelaPag.getValor());
			}
			dao.insert(tabelaPag);
			vendaSelecionada.setTotal(vendaSelecionada.getTotal() + tabelaPag.getValor());
		}
		caixa.setValorTotal(vendaSelecionada.getTotal());
		caixa.setValorTotalAPrazo(vendaSelecionada.getValorTotalAPrazo());
		caixa.setValorTotalAVista(vendaSelecionada.getValorTotalAvista());

		vendaSelecionada.setDataFechamento(Calendar.getInstance());
		dao.update(vendaSelecionada);
		dao.updateCaixa(caixa);
	}

	/** Método para criar vendas por cliente ou comanda/mesa */
	public static void criaVenda(IConnection dao, Venda venda) {
		VariaveisControle.vendaSelecionada = venda;
		venda.setTabelaPagamentos(new ArrayList<TabelaPagamento>());
		venda.setDataRegistro(Calendar.getInstance());
		venda.setMapAtributos(venda.getMapAtributos(true));
		dao.insert(venda);
		dao.close();
		// VariaveisControleAndroid.fragmentVendasAbertas.carregaLista();
		// VariaveisControleAndroid.fragmentProdutos.carregaLista();
	}
}
