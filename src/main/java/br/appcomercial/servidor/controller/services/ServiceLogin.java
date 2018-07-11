package br.appcomercial.servidor.controller.services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jmdesenvolvimento.appcomercial.controller.VariaveisControle;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.AUsuario;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Funcionario;

import br.appcomercial.servidor.dao.DaoJDBC;

@Path("login")
public class ServiceLogin {

	@Path("{tipo}/{login}/{senha}")
	@Produces("application/json; charset=UTF-8")
	public AUsuario validaLogin(@PathParam("tipo") int tipo, @PathParam("login") String login, @PathParam("senha") String senha ) {
		
		DaoJDBC dao = new DaoJDBC();
		AUsuario usuario = tipo == VariaveisControle.LOGIN_EMPRESA ? new EmpresaCliente() : new Funcionario();
		String usuarioEmailLogin = usuario.getEmailOuUsuario();
		String senhaLogin = usuario.getSenha();
		String where = "emailUsuario LIKE '" + usuarioEmailLogin + "' AND senha LIKE '" + senhaLogin + "'";
		
		usuario = (AUsuario) dao.select(usuario, null, where, null, null, null);
		return usuario;
	}
	
}
