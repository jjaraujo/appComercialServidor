package com.jmdesenvolvimento.appcomercial.controller;

import com.jmdesenvolvimento.appcomercial.model.Configuracoes;
import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.Estado;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.Municipio;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Cliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Fornecedor;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Pessoa;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Vendedor;
import com.jmdesenvolvimento.appcomercial.model.entidades.contas.ContaReceber;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Cfop;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Csons;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Cst;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Grupo;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Ncm;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Produto;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.TipoItem;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Unidade;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.TipoPagamento;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.Venda;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaParcelasPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaProdutosVenda;

public class TabelasMapeadas {
	
	public static final Tabela[] tabelas = { 
			new Configuracoes(), new TipoPagamento(), new Pessoa(), new Cliente(),
			new Municipio(), new Produto(), new Ncm(), new Grupo(), new Estado(), new Fornecedor(), new Cfop(),
			new Csons(), new TipoItem(), new Vendedor(), new Unidade(), new Vendedor(), new TabelaProdutosVenda(),
			new Venda(), new Cst(), new TabelaPagamento(), new TabelaParcelasPagamento(), new ContaReceber(),
			new EmpresaCliente() 
			};
	
	public static Tabela getTabelaForNome(String nome, boolean nomeSimples) {
		for(Tabela tabela : tabelas) {
			nome = nomeSimples ? nome.substring(0,1).toUpperCase() + nome.substring(1) : nome;
			nome = nome.replace("class ","");
			String nomeTabela = tabela.getClass().getName().replace("class ","");
			if(nomeTabela.contains(nome))
				return tabela;
		}
		return null;
	}
}
