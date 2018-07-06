package br.com.jmdesenvolvimento.servidor.controller.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Funcionario;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Pessoa;

import br.com.jmdesenvolvimento.servidor.dao.DaoJDBC;

import java.util.List;

import javax.ws.rs.GET;
@Path("tabelas")
public class ServiceController {
	
	public  ServiceController() {
		System.out.println("Entrou no service");
		
	}
	
	@Path("cadastraNovaEmpresa")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String cadastrarEmpresa(String s) {
		
		try {
			DaoJDBC dao = new DaoJDBC();
			LeituraJson json = new LeituraJson();
			EmpresaCliente empresa = (EmpresaCliente) json.jsonToTabela(s);
			dao.insert(empresa);
			String id = "\"#id\"";
			System.out.println(id.replace("#id",empresa.getId()+""));
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
			return id.replace("#id",idInt);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}
