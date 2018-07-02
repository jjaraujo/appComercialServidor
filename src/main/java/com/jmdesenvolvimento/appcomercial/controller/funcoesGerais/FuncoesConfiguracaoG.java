package com.jmdesenvolvimento.appcomercial.controller.funcoesGerais;

import com.jmdesenvolvimento.appcomercial.model.dao.IConnection;
import com.jmdesenvolvimento.appcomercial.model.Configuracoes;

public class FuncoesConfiguracaoG {

    public static void iniciaConfiguracoes(IConnection con){
        carregaConfiguracoesSimples(con);
    }
    
    public static void carregaConfiguracoesSimples(IConnection con){

        VariaveisControleG.configuracoesSimples = (Configuracoes) con.select(new Configuracoes(),"1",null,null,null,null);
        VariaveisControleG.configuracoesSimples.setMapAtributos(VariaveisControleG.configuracoesSimples.getMapAtributos(false));

    }
}
