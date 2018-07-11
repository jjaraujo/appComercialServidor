package com.jmdesenvolvimento.appcomercial.controller.funcoesGerais;

import com.jmdesenvolvimento.appcomercial.model.dao.IConnection;
import com.jmdesenvolvimento.appcomercial.model.Configuracoes;
import com.jmdesenvolvimento.appcomercial.controller.VariaveisControle;

public class FuncoesConfiguracaoG {

    public static void iniciaConfiguracoes(IConnection con){
        carregaConfiguracoesSimples(con);
    }
    
    public static void carregaConfiguracoesSimples(IConnection con){

        VariaveisControle.configuracoesSimples = (Configuracoes) con.select(new Configuracoes(),"1",null,null,null,null);
        VariaveisControle.configuracoesSimples.setMapAtributos(VariaveisControle.configuracoesSimples.getMapAtributos(false));

    }
}
