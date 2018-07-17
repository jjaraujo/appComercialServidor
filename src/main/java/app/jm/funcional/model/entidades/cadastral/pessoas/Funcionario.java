package app.jm.funcional.model.entidades.cadastral.pessoas;

import app.jm.funcional.model.dao.IConnection;

public class Funcionario  extends Usuario {

    private double comissao;

//    @Override
//    public void setMapAtributos(HashMap<String, Object> map) {
//        id = (int) map.get("id_vendedor");
//        pessoa = (Pessoa) map.get("pessoa");
//        comissao = (double) map.get("comissao");
//        usuario = (String) map.get("usuario");
//        senha = (String) map.get("senha");
//    }

    
    @Override
    public void geraId(IConnection con) {
    	id = con.countIdEntidade(this) + 1;
    }
    
    public Double getComissao(){
        return this.comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}
