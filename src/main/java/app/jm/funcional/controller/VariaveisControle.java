package app.jm.funcional.controller;

import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Funcionario;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;
import app.jm.funcional.model.entidades.vendas.Venda;

import java.sql.Connection;

import app.jm.funcional.model.Configuracoes;
import app.jm.funcional.model.dao.IConnection;

public final class VariaveisControle {

    /**Armazena a venda selecionada pelo usuário*/
	public static int tipoSql;
    public static Venda vendaSelecionada;
    public static Configuracoes configuracoesSimples;
    public static double valorRestante;
	public static Connection connectionSql;
	public static EmpresaCliente empresaCliente;
	public static Usuario usuarioFuncionarioLogado;
	public static IConnection iConnection;
	
	
	public static final int LOGIN_EMPRESA = 1;
	public static final int LOGIN_FUNCIONARIO = 2;
}
