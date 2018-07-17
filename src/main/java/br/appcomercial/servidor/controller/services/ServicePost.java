package br.appcomercial.servidor.controller.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.LeituraJson;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Funcionario;
import app.jm.funcional.model.entidades.estoque.Produto;
import app.jm.funcional.model.entidades.vendas.HorariosCaixa;
import br.appcomercial.servidor.controller.firebase.Firebase;
import br.appcomercial.servidor.dao.DaoJDBC;

@Path("insert")
public class ServicePost {
	
	public  ServicePost() {

		System.out.println("Entrou no servicePost");
		VariaveisControle.tipoSql = FuncoesSql.SQL_SERVER;
	}
	
	@Path("cadastraNovaEmpresa/{token}")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String cadastrarEmpresa(String empresaJson, @PathParam("token") String token) {

		System.out.println("TOKEN RECEBIDO: " + token);
		try { 
			DaoJDBC dao = new DaoJDBC();
			EmpresaCliente empresa = (EmpresaCliente) LeituraJson.jsonParaTabela(empresaJson);
			dao.insert(empresa);

			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setToken(token);
			dispositivo.setEmpresaClienteId((int)empresa.getId());
			dao.insert(dispositivo);
			HorariosCaixa horario = (HorariosCaixa) new HorariosCaixa().getListValoresIniciais().get(0);
			horario.setEmpresaClienteId((int)empresa.getId());
			dao.insert(horario);
			String id = "\"#id\"";
			
			new Firebase().create();
			
			return id.replace("#id",empresa.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@Path("cadastraFuncionario")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String cadastrarFuncionario(String s) {
		
		try {
			DaoJDBC dao = new DaoJDBC();
			Funcionario funcionario = (Funcionario) LeituraJson.jsonParaTabela(s);
			dao.insert(funcionario);
			String idInt = funcionario.getId() +"";
			String id = "\"#id\"";
			System.out.println(id.replace("#id",idInt));
//			Firebase.create();
			return id.replace("#id",idInt);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@Path("cadastraProduto")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String cadastraProduto(String s) {
		
		try {
			DaoJDBC dao = new DaoJDBC();
			Produto produto = (Produto) LeituraJson.jsonParaTabela(s);
			produto.setIdNaEmpresa(dao.countIdNaEmpresa(produto)+1);
			dao.insert(produto);
			String idInt = produto.getIdNaEmpresa() +"";
//			Firebase.create();
			return "\"#id\"".replace("#id",idInt);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@Path("alteraTabela")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public void alteraTabela(String s) {
		
		try {
			DaoJDBC dao = new DaoJDBC();
			Tabela produto = LeituraJson.jsonParaTabela(s);
			produto.setIdNaEmpresa(dao.countIdNaEmpresa(produto)+1);
			dao.insert(produto);
			String idInt = produto.getIdNaEmpresa() +"";
			
//			Firebase.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
