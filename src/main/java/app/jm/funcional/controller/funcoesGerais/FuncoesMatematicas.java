package app.jm.funcional.controller.funcoesGerais;

import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.vendas.Caixa;
import app.jm.funcional.model.entidades.vendas.Venda;
import app.jm.funcional.model.tabelasIntermediarias.TabelaProdutosVenda;

public class FuncoesMatematicas {

    public static String calculaValorTotalProdutoVenda(TabelaProdutosVenda tpv) {
        double valorTotal = 0;
        double valorProduto = tpv.getProduto().getPreco();
        double qtd = tpv.getQtd();
        valorTotal += valorProduto * qtd;
        return formataValoresDouble(valorTotal);
    }

    public static String calculaValorTotalVenda(Venda venda) {

        if(venda == null){
            return  formataValoresDouble(0);
        } // corrigir erro de nullpointerException ao inciar app
        List<TabelaProdutosVenda> list = venda.getTabelaProdutosVenda();
        double valorTotal = 0;
        if (list != null) {
            for (TabelaProdutosVenda tpv : list) {
                double valorProduto = tpv.getProduto().getPreco();
                double qtd = tpv.getQtd();
                valorTotal += valorProduto * qtd;
            }
        }
        return formataValoresDouble(valorTotal);
    }

    public static double calculaValorTotalVendaDouble(Venda venda) {

        if(venda == null){
            return  0;
        } // corrigir erro de nullpointerException ao inciar app
        List<TabelaProdutosVenda> list = venda.getTabelaProdutosVenda();
        double valorTotal = 0;
        if (list != null) {
            for (TabelaProdutosVenda tpv : list) {
                double valorProduto = tpv.getProduto().getPreco();
                double qtd = tpv.getQtd();
                valorTotal += valorProduto * qtd;
            }
        }
        return valorTotal;
    }

    public static String calculaValorTotalVendaComDesconto(Venda venda) {

        double valorTotal = Double.parseDouble(calculaValorTotalVenda(venda).replace(",","."));
        valorTotal = valorTotal - venda.getDesconto();
        return formataValoresDouble(valorTotal);
    }
    
    public static Caixa calculaCaixa(Calendar dataInicio, Calendar dataFim, IConnection con) {
    	String inicio = FuncoesGerais.calendarToString(dataInicio, FuncoesGerais.yyyyMMdd_HHMMSS, true);
    	String fim = FuncoesGerais.calendarToString(dataFim, FuncoesGerais.yyyyMMdd_HHMMSS, true);
    	
    	String where = fim != null ? " data BETWEEN #incio AND #fim" : "data = #inicio";
    	where = where.replace("#inicio", inicio).replace("#fim", String.valueOf(fim));

    	String[] colunas = {"COUNT(valorTotal) AS valorTotal","COUNT(valorTotalAVista) AS valorTotalAVista",
    			"COUNT(valorTotalAPrazo) AS valorTotalAPrazo", "COUNT(valorDesconto) AS valorDesconto"};
    	Caixa caixa = (Caixa) con.select(new Caixa(), where, colunas);
    	
    	return caixa == null ? new Caixa() : caixa;
    	
    }

    public static String formataValoresDouble(double valor){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(valor).replace(".",",");
    }
   
}
