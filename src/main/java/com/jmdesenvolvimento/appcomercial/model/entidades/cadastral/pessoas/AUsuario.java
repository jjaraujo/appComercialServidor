package com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas;

public abstract class AUsuario extends APessoa {
	

    public String emailOuUsuario;
    public String senha;
   
    
    public String getEmailOuUsuario() {
        return emailOuUsuario;
    }

    public void setEmailOuUsuario(String emailOuUsuario) {
        this.emailOuUsuario = emailOuUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
	
}
