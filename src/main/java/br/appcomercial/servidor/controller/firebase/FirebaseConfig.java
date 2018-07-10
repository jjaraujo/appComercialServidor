package br.appcomercial.servidor.controller.firebase;

import java.util.List;

import com.jmdesenvolvimento.appcomercial.model.Tabela;

public class FirebaseConfig extends Tabela {

	private String URL;
	private String apikey;

	public FirebaseConfig() {
		this.URL = "https://appcomercial-dd7e5.firebaseio.com";
		this.apikey = 
				 "dSVkxcl_b54:APA91bE1ziWhlZ6I6cIwduEe-YHaDnC1e5ob8yJ1Q_y1866bidd5b2T2wCiyktfWNCtlLDJshmEMcrVFa-hmgzecg1Js_Qv2Bbs30A5A3pRx0ijesHYevGdx7f-mgmXqdXMLL4pyliFHAIzb-8HXwjTmLSKeDimajw";

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
