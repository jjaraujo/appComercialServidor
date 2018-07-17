package app.jm.funcional.model.entidades.cadastral.pessoas;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.estoque.Grupo;

public class Fornecedor  extends APessoa{

    public Pessoa getPessoa() {
        if(pessoa == null){
            return new Pessoa();
        }
        return pessoa;
    }


//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        pessoa = (Pessoa) map.get("pessoa");//+ FuncoesGerais.prefixoChaveEstrangeira());
//    }

    @Override
    public String toString() {
        if(pessoa == null){
            return "Pessoa não Instanciada";
        }
        return pessoa.getNome();
    }

	@Override
	public List<Tabela> getListValoresIniciais() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Fornecedor>>(){}.getType();
	}
	
	
}
