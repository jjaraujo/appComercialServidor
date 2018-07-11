package com.jmdesenvolvimento.appcomercial.controller;

import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Funcionario;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.Venda;

import java.sql.Connection;

import com.jmdesenvolvimento.appcomercial.model.Configuracoes;
import com.jmdesenvolvimento.appcomercial.model.dao.IConnection;

public final class VariaveisControle {

    /**Armazena a venda selecionada pelo usuário*/
	public static int tipoSql;
    public static Venda vendaSelecionada;
    public static Configuracoes configuracoesSimples;
    public static double valorRestante;
	public static Connection connectionSql;
	public static EmpresaCliente empresaCliente;
	public static Funcionario funcionarioLogado;
	public static IConnection iConnection;
	
	
	public static final int LOGIN_EMPRESA = 1;
	public static final int LOGIN_FUNCIONARIO = 2;
}
