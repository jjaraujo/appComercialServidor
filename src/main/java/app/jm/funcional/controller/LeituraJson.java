package app.jm.funcional.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import app.jm.funcional.controller.funcoesGerais.FuncoesGerais;
import app.jm.funcional.controller.funcoesGerais.VerificaTipos;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.TabelasMapeadas;

public final class LeituraJson {

	
	public static Tabela jsonParaTabela(String s) {
		s = s.replace("\\\"", "\"");
		s = s.replace("'", "\"");
		s = s.substring(s.length()-1, s.length()).equals("\"") ? s.substring(0, s.length()-1) : s;//remove aspas do final, caso tenha
		s = s.substring(0, 2).equals("\"\"") ? s.substring(1) : s;
		s = s.substring(s.length() - 1).equals("\"") ? s.substring(0, s.length() - 1) : s;
		
		Gson gson = new GsonBuilder().setLenient().create();
		String nome = s.replace("\"", "").split(":")[0];
		
		System.out.println("JSON RECEBIDO " + s);
		try {
			Class c = TabelasMapeadas.getTabelaForNome(nome, true).getClass();
			int tamanhoNomeTabela = s.split(":")[0].length() + 1; // soma a posicao do :
			s = s.substring(tamanhoNomeTabela);
			Tabela tabela = (Tabela) gson.fromJson(s, c);
			return tabela;
		} catch (JsonSyntaxException e) {
			
			return null;
		}
	}	
	
	
	public static String tabelaParaJson(Tabela tabela){
        Gson gson = new Gson();
        tabela.anulaMapAtributo();
        
        try{
            anulaMapDeTabelaFields(tabela);
            String json = gson.toJson(tabela);
        
        return  "\"" +  tabela.getNomeTabela(true) + "':" + json.replace("[","").replace("]","").replace("\"", "'") + "\"";
        
        } catch(JsonSyntaxException e){
        		e.printStackTrace();
        }
        return null;
    }
	
	
	public static List<Tabela> jsonParaList(String s){
        Gson gson = new Gson();
        try{
        Type collectionType = new TypeToken<List<Tabela>>(){}.getType();
        List<Tabela> listEntidade = gson.fromJson(s, collectionType);
        return  listEntidade;
        } catch(JsonSyntaxException e){

            return null;
        }
    }
	
	public static String listParaJson(List<Tabela> list){
        Gson gson = new Gson();
        try{
        	
        	Type type = new TypeToken<List<Tabela>>(){}.getType();
        	if(list.isEmpty()) {
        		return "null";
        	}
    return  ("\""+list.get(0).getNomeTabela(true)+"\":" + gson.toJson(list,type)).replace("[","").replace("]","");
        } catch(JsonSyntaxException e){
            return null;
        }
    }
	
	/**Verifica dodos os fields da tabela, inclusive os herdados, para anular o map dos que forem tabela*/
	 private static void anulaMapDeTabelaFields(Tabela tabela) {
	      analisaFields(tabela, tabela.getClass().getDeclaredFields());
	      analisaFields(tabela, tabela.getClass().getFields());
	              
	    }

	private static void analisaFields(Tabela tabela, Field[] fields) {
		try {
		 for (Field f : fields) {
             f.setAccessible(true);
             if (VerificaTipos.isTabela(f, tabela)) {
                 Tabela t = (Tabela) f.get(tabela);
                 if(t != null) {
                     t.anulaMapAtributo();
                     anulaMapDeTabelaFields(t);
                 } else{
                     Tabela tabelaField = FuncoesGerais.getNovaInstanciaTabela(f);
                     f.set(tabela,tabelaField);
                 }
             }
         }
		} catch (IllegalAccessException e) {
            e.printStackTrace();
        }
	}
	
}
