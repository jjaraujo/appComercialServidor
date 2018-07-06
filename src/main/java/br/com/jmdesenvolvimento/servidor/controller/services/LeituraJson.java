package br.com.jmdesenvolvimento.servidor.controller.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jmdesenvolvimento.appcomercial.controller.TabelasMapeadas;
import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Cliente;

import java.lang.reflect.Type;
import java.util.List;

public class LeituraJson {

	public List<Tabela> deJsonListEntidade(String s) {
		Gson gson = new Gson();
		try {
			Type type = new TypeToken<List<Tabela>>() {
			}.getType();
			List<Tabela> listEntidade = gson.fromJson(s, type);
			return listEntidade;
		} catch (JsonSyntaxException e) {
			System.out
					.println("************Erro em ler JSON.LeituraJson().lerJsonPessoa" + "\n Erro:" + e.getMessage());
			return null;
		}
	}

	public Tabela jsonToTabela(String s) {
		s = s.replace("\\\"", "\"");
		s = s.substring(0, 2).equals("\"\"") ? s.substring(1) : s;
		s = s.substring(s.length() - 1).equals("\"") ? s.substring(0, s.length() - 1) : s;
		Gson gson = new GsonBuilder().setLenient().create();

		String nome = s.replace("\"", "").split(":")[0];
		System.out.println("JSON RECEBIDO" + s);
		try {
			Class c = TabelasMapeadas.getTabelaForNome(nome, true).getClass();
			int tamanhoNomeTabela = s.split(":")[0].length() + 1; // soma a posicao do :
			s = s.substring(tamanhoNomeTabela);
			Tabela tabela = (Tabela) gson.fromJson(s, c);
			return tabela;
		} catch (JsonSyntaxException e) {
			System.out
					.println("************Erro em ler JSON.LeituraJson().lerJsonPessoa" + "\n Erro:" + e.getMessage());
			return null;
		}
	}

	public String paraJsonListTabela(List<Tabela> list, Tabela entidade){
        Gson gson = new Gson();
        try{
        	
        	Type type = new TypeToken<List<Tabela>>(){}.getType();
        	if(list.isEmpty()) {
        		return "null";
        	}
    return  ("\""+entidade.getNomeTabela(true)+"\":" + gson.toJson(list,type)).replace("[","").replace("]","");
        } catch(JsonSyntaxException e){
            return null;
        }
    }
}
