package br.appcomercial.servidor.controller.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import app.jm.funcional.controller.LeituraJson;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Funcionario;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;

import br.appcomercial.servidor.dao.DaoJDBC;

@Path("login")
public class ServiceLogin {

	@Path("{login}/{senha}")
	@GET
	@Produces("application/json; charset=UTF-8")
	public String validaLogin( @PathParam("login") String login, @PathParam("senha") String senha ) {
		
		DaoJDBC dao = new DaoJDBC();
		// caso seja empresa, o login será por email
		Usuario usuario = login.contains("@")? new EmpresaCliente() : new Funcionario();
		String usuarioEmailLogin = login;
		int i = 0;
		String senhaLogin = senha;
		String where = " login LIKE '" + usuarioEmailLogin + "' AND senha LIKE '" + senhaLogin + "'";
		
		usuario = (Usuario) dao.select(usuario, null, where, null, null, null);
		
		if(usuario == null) {
			return "null";
		}
		
		usuario.setSenha("");
		
		String json = LeituraJson.tabelaParaJson(usuario);
		System.out.println("JSON: " + json);
		return json;
	}	
	
}
