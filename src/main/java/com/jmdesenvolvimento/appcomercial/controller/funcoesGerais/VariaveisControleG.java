package com.jmdesenvolvimento.appcomercial.controller.funcoesGerais;

import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.Venda;

import java.sql.Connection;

import com.jmdesenvolvimento.appcomercial.model.Configuracoes;

public final class VariaveisControleG {

    /**Armazena a venda selecionada pelo usuário*/
    public static Venda vendaSelecionada;
    public static Configuracoes configuracoesSimples;
    public static double valorRestante;
	public static Connection connectionSql;
}
