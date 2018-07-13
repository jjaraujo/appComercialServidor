package app.jm.funcional.controller.funcoesGerais;

import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.Configuracoes;
import app.jm.funcional.controller.VariaveisControle;

public class FuncoesConfiguracaoG {

    public static void iniciaConfiguracoes(IConnection con){
        carregaConfiguracoesSimples(con);
    }
    
    public static void carregaConfiguracoesSimples(IConnection con){

        VariaveisControle.configuracoesSimples = (Configuracoes) con.select(new Configuracoes(),"1",null,null,null,null);
        VariaveisControle.configuracoesSimples.setMapAtributos(VariaveisControle.configuracoesSimples.getMapAtributos(false));

    }
}
