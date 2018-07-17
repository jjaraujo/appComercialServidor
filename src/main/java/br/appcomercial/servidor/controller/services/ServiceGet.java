package br.appcomercial.servidor.controller.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.LeituraJson;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.TabelasMapeadas;
import br.appcomercial.servidor.dao.DaoJDBC;

@Path("select")
public class ServiceGet {
	
	
	public  ServiceGet() {

		System.out.println("Entrou no serviceGet");
		VariaveisControle.tipoSql = FuncoesSql.SQL_SERVER;
	}
	

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
		System.out.println("JSON select: " + LeituraJson.tabelaParaJson(tabela));
		return LeituraJson.tabelaParaJson(tabela);
		
	}
	
	@Path("todas/{nomeDaTabela}/{idEmpresa}")
	@GET
	@Produces("application/json; charset=UTF-8")
	public String getListTabela(@PathParam("nomeDaTabela") String nomeTabela, @PathParam("idEmpresa") long idEmpresa) {
		DaoJDBC dao = new DaoJDBC();
		Tabela tabela = TabelasMapeadas.getTabelaForNome(nomeTabela, true);
		
		List list = dao.selectAll(tabela, "empresaClienteId = "+ idEmpresa, false);
		String jsonList = LeituraJson.listParaJson(list);
		System.out.println("Enviando  a tabela " + tabela.getNomeTabela(false));
		System.out.println("JSON: " + jsonList);
		return jsonList;
	}
	
}
