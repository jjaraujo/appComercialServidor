package app.jm.funcional.model.entidades.cadastral.pessoas;


@SuppressWarnings("serial")
public class Usuario extends APessoa {
	
    public String login;
    public String senha;
    private int tipoUsuario;
 //  public EmpresaCliente empresaCliente;
   
    
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
    
    
   

    
	
}
