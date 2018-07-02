package br.com.jmdesenvolvimento.appcomercial.controller.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Pessoa;

import br.com.jmdesenvolvimento.appcomercial.dao.DaoJDBC;

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
	public String cadastrar(String s) {
		
		try {
			DaoJDBC dao = new DaoJDBC();
			LeituraJson json = new LeituraJson();
			EmpresaCliente empresa = (EmpresaCliente) json.deJsonEntidade(s);
			dao.insert(empresa);
			String idInt = dao.getIdEmpresaCliente(empresa.getPessoa().getCpfCNPJ());
			String id = "\"#id\"";
			System.out.println(id.replace("#id",idInt+""));
			return idInt == null ? null : id.replace("#id",idInt+"");
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@Path("insertNovaEmpresa")
	@POST
	@Consumes("application/json; charset=UTF-8")
	public String cadastrarNovaEmpresa(String s) {
		System.out.println("Leu S");
		try {
			DaoJDBC dao = new DaoJDBC();
			LeituraJson json = new LeituraJson();
			List<Tabela> listCliente = json.deJsonListEntidade(s);
			System.out.println(listCliente.isEmpty() + ". vai salvar");
		//dao.insert();
			
			return "Salvou";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
        	/**
	 * Esse mÃ©todo lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("listClientes")
	public String TodasPessoas(){
		System.out.println("Vai listar as pessoas");
		DaoJDBC dao = new DaoJDBC();
		LeituraJson json = new LeituraJson();
		List<Tabela> listaEntidade =   (List<Tabela>) dao.selectAll(new Pessoa(), null, false);
		System.out.println("*****************" +listaEntidade.isEmpty());
		System.out.println(json.paraJsonListTabela(listaEntidade,new Pessoa()));
		return  json.paraJsonListTabela(listaEntidade,new Pessoa()) ;
	}
}
