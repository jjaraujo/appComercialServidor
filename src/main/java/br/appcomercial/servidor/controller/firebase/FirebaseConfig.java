package br.appcomercial.servidor.controller.firebase;

import java.util.List;

import app.jm.funcional.model.Tabela;

public class FirebaseConfig extends Tabela {

	private String URL;
	private String apikey;

	public FirebaseConfig() {
		this.URL = "https://appcomercial-dd7e5.firebaseio.com";
		this.apikey = 
				 "cUcxFORARc0:APA91bHi1MyRSHYrTGmoItKgo87nxNdueyWVnmhA6sRvcY7jJMOABnQUHxxG74s7XSd9pT_JUtomJWC3zenlzxQHODmqFD8rT24SWITbJ0LslQ7tPPbKeLN2ll2STSB2gKTGqFJDIZZGS2_gxZhSJ2FttwSfmyW_OA";
	}

	public String getURL() {
		return URL;
	}

	public String getApikey() {
		return apikey;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}

}
