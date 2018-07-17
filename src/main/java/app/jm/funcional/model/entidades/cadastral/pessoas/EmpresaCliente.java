package app.jm.funcional.model.entidades.cadastral.pessoas;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.dao.IConnection;

public class EmpresaCliente extends Usuario {

    private Calendar dataCadastro;
    private int plano;
    private Calendar dataVencimento;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get(getIdNome());
//        pessoa = (Pessoa) map.get("pessoa");
//        dataCadastro = (Calendar) map.get("dataCadastro");
//        plano = (int) map.get("plano");
//        dataVencimento = (Calendar) map.get("dataVencimento");
//    }
    
    @Override
    public void geraId(IConnection con) {
    	id = con.countIdEntidade(this);
    }
    
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getPlano() {
		return plano;
	}

	public void setPlano(int plano) {
		this.plano = plano;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}
