package app.jm.funcional.model.entidades.cadastral.pessoas;


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
    public long getId() {
    	return id;
    }
    
    public Double getComissao(){
        return this.comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}
