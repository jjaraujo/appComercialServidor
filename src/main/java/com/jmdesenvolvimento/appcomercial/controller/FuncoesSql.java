package com.jmdesenvolvimento.appcomercial.controller;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.jmdesenvolvimento.appcomercial.controller.funcoesGerais.FuncoesGerais;
import com.jmdesenvolvimento.appcomercial.controller.funcoesGerais.VerificaTipos;
import com.jmdesenvolvimento.appcomercial.model.Configuracoes;
import com.jmdesenvolvimento.appcomercial.model.Tabela;
import com.jmdesenvolvimento.appcomercial.model.TabelasMapeadas;
import com.jmdesenvolvimento.appcomercial.model.dao.IConnection;
import com.jmdesenvolvimento.appcomercial.model.dao.IRegistros;
import com.jmdesenvolvimento.appcomercial.model.entidades.Entidade;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.Estado;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.Municipio;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Cliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.EmpresaCliente;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Fornecedor;
import com.jmdesenvolvimento.appcomercial.model.entidades.cadastral.pessoas.Pessoa;
import com.jmdesenvolvimento.appcomercial.model.entidades.contas.ContaReceber;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Cfop;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Csons;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Cst;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Grupo;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Ncm;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Produto;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.TipoItem;
import com.jmdesenvolvimento.appcomercial.model.entidades.estoque.Unidade;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.TipoPagamento;
import com.jmdesenvolvimento.appcomercial.model.entidades.vendas.Venda;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaParcelasPagamento;
import com.jmdesenvolvimento.appcomercial.model.tabelasIntermediarias.TabelaProdutosVenda;

public abstract class FuncoesSql {
	
	public static final int SQL_SERVER = 1;
	public static final int SQLITE = 2;

			
	public static String caminhoBanco() {
		return nomeDatabase() + ".dbo";
	}
	
	public static String nomeDatabase() {
		return "aplicacaocomercial";
	}
	
	
	
	/**Cria tabelas no banco. Criará somente as tabelas informada no field tabelas.
	 * @param db - Informar o IConnection de acordo com a plataforma de desenvolvimento
	 * @param tipoSql - Informar o tipo de sql, já definidos como fields static*/
	public static void createTables(IConnection db, int tipoSql) {
		Tabela[] tabelas = TabelasMapeadas.tabelas;
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(0);
		for (int i = 0; i < tabelas.length; i++) {
			Tabela tabela = tabelas[i];
			createTable(db, tabela, tipoSql);
		}
	}
	
	public static void createTable(IConnection db, Tabela tabela, int tipoSql) {
		String ifExists = tipoSql == SQL_SERVER ?
				"IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = '"+tabela.getNomeTabela(false)+ "') "
						+ " BEGIN "
						+ " CREATE TABLE "	: 
						" CREATE TABLE IF NOT EXISTS ";
		String sql = ifExists + tabela.getCaminhoTabelaBanco(tipoSql) + "(";

		String[] nomeAtributos = tabela.getNomesAtributos();
		// verificar a possibilidade de pegar esses nomes direto do map
		for (int j = 0; j < nomeAtributos.length; j++) {
			String nome = nomeAtributos[j];
			Object atributo = FuncoesGerais.getValorFieldDeTabela(nome, tabela);
			if (nome == null) {
				continue;
			}
			sql +=  substituiTiposVariaveisCreateTable(nomeAtributos[j], tabela, atributo, tipoSql,j);
		}
		sql = sql.replace("(,", "(");
		sql = sql + ");";
		sql += tipoSql == SQL_SERVER ? " END;" : "";
		System.out.println(sql);
		db.execSQL(sql);
		insereRegistrosIniciais(db, tabela, tipoSql);
	
	}
	
	
	private String altersTable(Tabela tabela) {
		List<String> list = tabela.alterTableAposCriacao();
		
		if(list == null)
			return "";
		
		String s = "";
		for(String alter : list) {
			s += alter;
		}
		
		return s;
	}
	
	
	
	/**Substitui os tipos de variaveis para a criação da tabela no banco. Ex: se String, criará um varchar(100)
	 * @param nomeColuna - Informar o nome da coluna que será criada (pode ser um field de tabela ou a chave do map) 
	 * @param tabela - Informar a tabela que está sendo criada
	 * @param objetoValorDaColuna - Informar o valor da coluna, com o qual será verificado qual o tipo de variável
	 * @param tipoSql - Informar o tipo de sql, já definidos como fields static
	 * @return Retorna o trecho de SQL referente a coluna. EX: id_tabela_pk VARCHAR(100) PRIMARY KEY NOT NULL*/
	private static String substituiTiposVariaveisCreateTable(String nomeColuna, Tabela tabela, Object objetoValorDaColuna, int tipoSql, int position) {
		
		if(FuncoesGerais.getFieldDeTabela(nomeColuna, tabela).isAnnotationPresent(NaoUsarNaBase.class))
			return "";
		
		String sql;
		String nomeTipo = "";
		if (!nomeColuna.toLowerCase().equals(tabela.getIdNome().toLowerCase())) {
			if (VerificaTipos.isTabela(objetoValorDaColuna)) { // caso a coluna seja uma entidade
				nomeTipo = tipoSql == SQLITE ? " INTEGER " : " INT ";
			} else {
				if (VerificaTipos.isText(objetoValorDaColuna, null)) {
					nomeTipo =  tipoSql == SQLITE ? " TEXT " : " VARCHAR(100) ";
				} else if (VerificaTipos.isDouble(objetoValorDaColuna, null)) {
					nomeTipo =  tipoSql == SQLITE ?  " REAL " : " FLOAT ";
				} else if (VerificaTipos.isInteger(objetoValorDaColuna, null) || VerificaTipos.isLong(objetoValorDaColuna, null)) {
					nomeTipo = tipoSql == SQLITE ? " INTEGER " : "  INT ";
				}
			}
		} else {
			nomeTipo = (tipoSql == SQLITE ? " INTEGER PRIMARY KEY " : " INT PRIMARY KEY NOT NULL IDENTITY ");
		}
		sql = nomeColuna + nomeTipo;
		return position > 0 ? "," + sql : sql;
	}
	
	
	/**
	 * Informa os dados de cada registro, um a um, para o método insereRegistroInicial
	 * @param db - Informar o IConnection de acordo com a plataforma de desenvolvimento
	 * @param tabela - Informar a tabela que está sendo criada
	 * @param tipoSql - Informar o tipo de sql, já definidos como fields static
	 **/
	private static void insereRegistrosIniciais(IConnection db, Tabela tabela, int tipoSql) {

		if (tabela.getPrecisaRegistroInicial()) {
			if (tabela.getListValoresIniciais() == null || tabela.getListValoresIniciais().isEmpty() || !tabela.usaInsert()) {
				return;
			}

			for (Tabela t : tabela.getListValoresIniciais()) {
				insereRegistroInicial(t, db, tipoSql);
			}
		}
	}
	
	
	/**
	 * Algumas tabelas precisam de registros logo após sua criação. Usado principalmente no Android
	 * @param db - Informar o IConnection de acordo com a plataforma de desenvolvimento
	 * @param tabela - Informar a tabela que está sendo criada
	 * @param tipoSql - Informar o tipo de sql, já definidos como fields static
	 **/
	private static void insereRegistroInicial(Tabela tabela, IConnection db, int tipoSql) {
		tabela.setId(db.countIdEntidadeCriacao(tabela) + 1); 
		int i = 1;
		HashMap<String, Object> map = tabela.getMapAtributos(true);

		map.put(tabela.getDataExclusaoNome(), tabela.getDataExclusao());
		String sql = montaSqlInsert(tabela,tipoSql);
		db.execSQL(sql);
	}
	
	
	
	/**Verifica o tipo da coluna e adiciona seu valor no map, com a chave igual ao seu nome.
	 * @param con - Informar o IConnection de acordo com a plataforma de desenvolvimento
	 * @param nomeColuna - Informar o nome da coluna no bancp que será buscado o valor
	 * @param map - informar o map da tabela para adicionar o valor da coluna
	 * @param i - index da coluna no registro da tabela
	 * @param tabela - tabela da coluna
	 * @param cursor - Informar o cursor que contem o registros de acordo com a plataforma de desenvolvimento
	 * @return Retorna o @param map com o valor da coluna
	 */
	public static HashMap<String, Object> addColunaSqlNoMap(IConnection con, String nomeColuna, int i, Tabela tabela, IRegistros cursor) {
		
		HashMap<String, Object> map = tabela.getMapAtributos(false);
		
		try {	
			Type type = map.get(nomeColuna).getClass();
			String nomeTipo = type.toString().replace("class ", "");
			Object o = map.get(nomeColuna);
			
			if (VerificaTipos.isTabela(map.get(nomeColuna))) {
				int idTabela = cursor.getInt(nomeColuna);

				if (idTabela > 0) { // buscará entidade somente se id > 0
					Tabela tabelaNoBanco = con.select(TabelasMapeadas.getTabelaForNome(nomeTipo, false), idTabela + "", null,
							null, null, null);
					map.put(nomeColuna, tabelaNoBanco);

				} else {
					Tabela novaTabela = (Tabela) TabelasMapeadas.getTabelaForNome(nomeTipo, false);
					map.put(nomeColuna, novaTabela);
				}

			}else if (VerificaTipos.isString(o, null)) {
			
				map.put(nomeColuna, FuncoesGerais.converteNuloToVazio(cursor.getString(nomeColuna)));

			} else if (VerificaTipos.isInt(o, null)) {
				map.put(nomeColuna, FuncoesGerais.corrigeValoresCamposInt(cursor.getInt(nomeColuna)));

			} else if (VerificaTipos.isDouble(o, null)) {
				map.put(nomeColuna, FuncoesGerais.corrigeValoresCamposDouble(cursor.getDouble(nomeColuna)));

			} else if (VerificaTipos.isLong(o, null)) {
				map.put(nomeColuna, FuncoesGerais.corrigeValoresCamposLong(cursor.getLong(nomeColuna)));

			} else if (VerificaTipos.isList(o, null)) {
				
				Field listField = FuncoesGerais.getFieldDeTabela(nomeColuna, tabela);
				ParameterizedType listFieldGenericType = (ParameterizedType) listField.getGenericType();
				Class<?> stringListClass = (Class<?>) listFieldGenericType.getActualTypeArguments()[0];
				String nomeClassTabela = stringListClass.getName().replace("class ", "");
				Tabela novaTabela = TabelasMapeadas.getTabelaForNome(nomeClassTabela, false);
				List list = con.selectAll(novaTabela, tabela.getNomeTabela(true) + " = " + tabela.getId(), false);
				map.put(nomeColuna, list);
				
			} else if (VerificaTipos.isCalendar(o, null)) {
				map.put(nomeColuna,
						FuncoesGerais.corrigeValoresCalendar(cursor.getString(nomeColuna), FuncoesGerais.yyyyMMdd_HHMMSS));

			} else if (VerificaTipos.isBoolean(o, null)) {
				map.put(nomeColuna, FuncoesGerais.intToBoolean(cursor.getInt(nomeColuna)));
			}
		} catch (NullPointerException e) {
//			Log.i("Objeto nulo", "O campo " + nomeColuna + " possivelmente não existe no map");
			e.getStackTrace();
		}
		return map;
	}
	
	/**
	 * Verifica o tipo de cada dado e adiciona no map
	 * @param tabela - Informar a tabela que está sendo criada
	 * @param db - Informar o IConnection de acordo com a plataforma de desenvolvimento
	 * @param cursor - Informar o cursor que contem o registros de acordo com a plataforma de desenvolvimento
	 * @param map - informar o map da tabela que está sendo buscada dos registros. 
	 * @return Tabela com os valores já setados no map
	 */
	public static Tabela percorreColunasSqlEAdicionaNoMap(IConnection con, IRegistros cursor, Tabela tabela) {
		HashMap<String, Object> map = tabela.getMapAtributos(false);
		int colunas = cursor.getColumnCount();
		for (int i = 0; i < colunas; i++) {

			String nomeColuna =  cursor.getNomeColuna(i);
			try {
				if (!map.containsKey(nomeColuna)) {// || map.get(nomeColuna) == null) {
					continue;
				}

				FuncoesSql.addColunaSqlNoMap(con, nomeColuna,i, tabela, cursor);

			} catch (NullPointerException e) {
				// Log.i("Coluna nula",nomeColuna);
				e.printStackTrace();
			}
		}
			Tabela novaTabela = FuncoesGerais.getNovaInstanciaTabela(tabela.getNomeCompletoTabela());
			novaTabela.setMapAtributos(map);
			// tabela.setMapAtributos(map);
		//	cursor.close();
			return novaTabela;
			
	}


	// private int countIdEntidadeCriacao( IConnection db) {
	// String nomeTabela = tabela.getNomeTabela(false);
	// String id = tabela.getIdNome();
	// String[] columns = {"COUNT(" + id + ")"};
	// Cursor cursor = db.query(nomeTabela, columns, null, null, id, null, null);
	// int count = cursor.getCount();
	// cursor.close();
	// return count;
	// }
	
	/**Monta o sql de Insert
	 * @param map - Informar o map com os valores a serem inseridos
	 * @param nomeTabela - informar o nome da tabela. Pode ser adquirido pelo metodo tabela.getNomeTabela()
	 * @param tipoSql - Informar o tipo de sql, já definidos como fields static
	 * @return Retorna o SQL montado pronto para execução*/
	public static String montaSqlInsert(Tabela tabela, int tipoSql) {
		String nomeTabela = tabela.getNomeTabela(false);
		HashMap<String, Object> map = tabela.getMapAtributos(false);
		String sql = tipoSql == SQL_SERVER ? " SET IDENTITY_INSERT "+nomeTabela+" ON; " : "";
		sql += " INSERT INTO " + nomeTabela;
		String colunas = "(";
		String valores = "(";
		for (String s : map.keySet()) {
			Object atributo = map.get(s);
			if (atributo == null || VerificaTipos.isList(atributo, null) || atributo.equals("")
					|| atributo.equals(" ")) {
				continue;
			}
			if (VerificaTipos.isString(atributo, null)) {
				colunas += ", " + s;
				String dps = FuncoesGerais.removeCaracteresEspeciais((String) atributo);
				valores += ", '" + dps + "'";

			} else if (VerificaTipos.isCalendar(atributo, null)) {
				colunas += ", " + s;
				valores += ", "
						+ FuncoesGerais.calendarToString((Calendar) atributo, FuncoesGerais.yyyyMMdd_HHMMSS, true);

			} else if (VerificaTipos.isTabela(atributo)) {
				Tabela e = (Tabela) atributo;
				colunas += ", " + s;
				valores += ", " + e.getId();
				
			} else if (VerificaTipos.isBoolean(atributo, null)) {
				colunas += ", " + s;
				valores += ", " + FuncoesGerais.booleanToint((Boolean) atributo);
			} else if (VerificaTipos.isDouble(atributo, null)) { // caso seja real ou integer
				colunas += ", " + s;
				valores += ", " + atributo;
			} else if (VerificaTipos.isInt(atributo, null) || VerificaTipos.isLong(atributo, null)) { // caso seja real ou integer
				colunas += ", " + s;
				valores += ", " + atributo;
			}
		}
		valores = valores.replace("(,", "(");
		colunas = colunas.replace("(,", "(") + ")";
		sql += colunas + " VALUES " + valores + ");";
		sql += tipoSql == SQL_SERVER ? " SET IDENTITY_INSERT "+nomeTabela+" OFF; " : "";
		return sql;
	}
	
	/**Monta o sql de Update
	 * @param tabela - Informar a tabela que será feito update
	 * @param ignorarValoresVazios - Informar true somente se os valores 0 não forem importantes.
	 * Caso true, não vai adicionar no script valores que forme == null, "", " " e 0.
	 * */
	public static String montaSqlUpdate(Tabela tabela, boolean ignorarValoresVazios) {
		String nomeEntidade = tabela.getNomeTabela(false);
		HashMap<String, Object> map = tabela.getMapAtributos(true);
//		if (tabela.isEntidade()) {
//			Entidade entidade = (Entidade) tabela;
//			map.put(entidade.getIdNome(), entidade.getId());
//		}
		Set<String> set = map.keySet();
		String sql = "UPDATE " + nomeEntidade;
		String colunas = " SET ";
		for (String s : set) {
			Object atributo = map.get(s);
			if ((atributo == null || (atributo + "").equals("0") || (atributo + "").equals("0.0") || atributo.equals("")
					|| atributo.equals(" ")) && ignorarValoresVazios) {
				continue;
			}
			if (atributo.getClass().getSimpleName().contains("List"))
				continue;

			if (atributo.getClass().toString().trim().toLowerCase().contains("string")) {
				String dps = FuncoesGerais.removeCaracteresEspeciais((String) map.get(s));
				colunas += s + " = '" + dps + "',";

			} else if (VerificaTipos.isTabela(map.get(s))) {
				Entidade e = (Entidade) atributo;
				colunas += s + " = " + e.getId() + ",";
			} else if (VerificaTipos.isCalendar(map.get(s), null)) {
				colunas += s + " = "
						+ FuncoesGerais.calendarToString((Calendar) atributo, FuncoesGerais.yyyyMMdd_HHMMSS, true)
						+ ",";
			} else if (VerificaTipos.isBoolean(map.get(s), null)) {
				colunas += s + " = " + FuncoesGerais.booleanToint((Boolean) map.get(s)) + ",";
			} else { // caso seja real ou integer
				colunas += s + " = " + atributo + ",";
			}
		}
		colunas += ",,";
		colunas = colunas.replace(",,,", " ").replace(",,", " ");

		sql += colunas + " WHERE " + tabela.getIdNome() + " = " + tabela.getId() + ";";
		return sql;
	}

}
