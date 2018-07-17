package app.jm.funcional.model.entidades.cadastral.pessoas;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.estoque.Grupo;

@SuppressWarnings("serial")
public class Usuario extends APessoa {

	public String login;
	public String senha;
	private int tipoUsuario;
	// public EmpresaCliente empresaCliente;

	@Override
	public void geraId(IConnection con) {
		con.countIdNaEmpresa(this);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public boolean isbackup() {
		return false;
	}
	
	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Usuario>>(){}.getType();
	}
	
}
