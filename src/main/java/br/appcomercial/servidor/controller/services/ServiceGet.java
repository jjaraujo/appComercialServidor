package br.appcomercial.servidor.controller.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import app.jm.funcional.controller.LeituraJson;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.TabelasMapeadas;
import br.appcomercial.servidor.dao.DaoJDBC;

@Path("select")
public class ServiceGet {

	@Path("{nomeTabela}/{id}")
	@GET
	@Produces("application/json; charset=UTF-8")
	public String getTabela(@PathParam("nomeTabela") String nomeTabela,@PathParam("id") long id) {
		Tabela tabela = TabelasMapeadas.getTabelaForNome(nomeTabela, true);
		if(tabela == null)
			return null;
		
		DaoJDBC dao = new DaoJDBC();		
		tabela = dao.select(tabela, id  + "", null, null, null, null);

		if(tabela == null) {
			return null;
		}
		System.out.println("JSON: " + LeituraJson.tabelaParaJson(tabela));
		return LeituraJson.tabelaParaJson(tabela);
		
	}
	
}
