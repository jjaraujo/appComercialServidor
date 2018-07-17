package app.jm.funcional.model;

import app.jm.funcional.model.Configuracoes;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.Estado;
import app.jm.funcional.model.entidades.cadastral.Municipio;
import app.jm.funcional.model.entidades.cadastral.pessoas.Cliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Fornecedor;
import app.jm.funcional.model.entidades.cadastral.pessoas.Funcionario;
import app.jm.funcional.model.entidades.cadastral.pessoas.Pessoa;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;
import app.jm.funcional.model.entidades.contas.ContaReceber;
import app.jm.funcional.model.entidades.estoque.Cfop;
import app.jm.funcional.model.entidades.estoque.Csons;
import app.jm.funcional.model.entidades.estoque.Cst;
import app.jm.funcional.model.entidades.estoque.Grupo;
import app.jm.funcional.model.entidades.estoque.Ncm;
import app.jm.funcional.model.entidades.estoque.Produto;
import app.jm.funcional.model.entidades.estoque.TipoItem;
import app.jm.funcional.model.entidades.estoque.Unidade;
import app.jm.funcional.model.entidades.vendas.Caixa;
import app.jm.funcional.model.entidades.vendas.HorariosCaixa;
import app.jm.funcional.model.entidades.vendas.TipoPagamento;
import app.jm.funcional.model.entidades.vendas.Venda;
import app.jm.funcional.model.tabelasIntermediarias.TabelaPagamento;
import app.jm.funcional.model.tabelasIntermediarias.TabelaParcelasPagamento;
import app.jm.funcional.model.tabelasIntermediarias.TabelaProdutosVenda;

public class TabelasMapeadas {
	
	public static final Tabela[] tabelas = { 
			new HorariosCaixa(), new Dispositivo(), new Configuracoes(), new TipoPagamento(), new Pessoa(), new Cliente(),
			new Municipio(), new Produto(), new Ncm(), new Grupo(), new Estado(), new Fornecedor(), new Cfop(),
			new Csons(), new TipoItem(), new Funcionario(), new Unidade(), new TabelaProdutosVenda(),
			new Venda(), new Cst(), new TabelaPagamento(), new TabelaParcelasPagamento(), new ContaReceber(),
			new EmpresaCliente(), new Usuario(), new Caixa(), 
			};
	
	public static Tabela getTabelaForNome(String nome, boolean nomeSimples) {
		for(Tabela tabela : tabelas) {
			nome = nomeSimples ? nome.substring(0,1).toUpperCase() + nome.substring(1) : nome;
			nome = nome.replace("class ","");
			String nomeTabela = tabela.getClass().getName().replace("class ","");
			if(nomeTabela.contains(nome))
				try {
					return tabela.getClass().newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					return null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					return null;
				}
		}
		return null;
	}
}
