package br.appcomercial.servidor.controller.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jmdesenvolvimento.appcomercial.controller.FuncoesSql;
import com.jmdesenvolvimento.appcomercial.controller.VariaveisControleG;
import com.jmdesenvolvimento.appcomercial.model.Dispositivo;
import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Funcionario;

import br.appcomercial.servidor.controller.firebase.Firebase;
import br.appcomercial.servidor.dao.DaoJDBC;

@Path("tabelas")
public class ServiceController {
	
	public  ServiceController() {
		System.out.println("Entrou no service");
		VariaveisControleG.tipoSql = FuncoesSql.SQL_SERVER;
	}
	
	@Path("cadastraNovaEmpresa/{token}")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String cadastrarEmpresa(String empresaJson, @PathParam("token") String token) {

		System.out.println("TOKEN RECEBIDO: " + token);
		try {
			DaoJDBC dao = new DaoJDBC();
			LeituraJson json = new LeituraJson();
			EmpresaCliente empresa = (EmpresaCliente) json.jsonToTabela(empresaJson);
			dao.insert(empresa);			

			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setToken(token);
			dispositivo.setEmpresaCliente((int)empresa.getId());
			dao.insert(dispositivo);
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
			LeituraJson json = new LeituraJson();
			Funcionario funcionario = (Funcionario) json.jsonToTabela(s);
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
}
