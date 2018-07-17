package br.appcomercial.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.controller.funcoesGerais.FuncoesGerais;
import app.jm.funcional.controller.funcoesGerais.VerificaTipos;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.dao.IConnection;
import app.jm.funcional.model.entidades.cadastral.pessoas.Pessoa;

public class DaoJDBC implements IConnection {
	Connection con;

	public DaoJDBC() {
		getConnection();
	}

	@Override
	public int getTipoSql() {
		// TODO Auto-generated method stub
		return FuncoesSql.SQL_SERVER;
	}
	
	private void getConnection() {
		try {

			VariaveisControle.connectionSql = VariaveisControle.connectionSql == null
					|| VariaveisControle.connectionSql.isClosed() ? new ConnectionFactory().getConnection()
							: VariaveisControle.connectionSql;
			this.con = VariaveisControle.connectionSql;
			// FuncoesSql.createTables(this, FuncoesSql.SQL_SERVER);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(Tabela tabela) {
		try {
			String nomeEntidade = tabela.getNomeTabela(false);
			if (tabela.getId() == 0) {
				tabela.geraId(this);
			}
			HashMap<String, Object> map = tabela.getMapAtributos(true);
			int i = 0;
			insertTabelasFields(tabela);
			String sql = FuncoesSql.montaSqlInsert(tabela, 1);
			PreparedStatement stmt = con.prepareStatement(sql);
			executeUpdate(stmt);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(List<?> tabelas) {
		for (int i = 0; i < tabelas.size(); i++) {
			Tabela t = (Tabela) tabelas.get(i);
			insert(t);
		}
	}

	private void insertTabelasFields(Tabela tabela) {
		HashMap<String, Object> map = tabela.getMapAtributos(false);
		map.keySet().forEach((s) -> {
			Object o = map.get(s);
			if (VerificaTipos.isTabela(o) && ((Tabela) o).getId() > 0) {
				insert((Tabela) o);
			}
		});
	}

	public void update(Tabela tabela, boolean ignorarValoresVazios) {
		String sql = FuncoesSql.montaSqlUpdate(tabela, ignorarValoresVazios);
		PreparedStatement stmt = getPreparedStatement(sql);
		executeUpdate(stmt);
		close(stmt);
	}

	public void deleteLogico(Tabela tabela) {
		String sql = "UPDATE " + tabela.getNomeTabela(false) + " SET " + tabela.getDataExclusaoNome() + " = "
				+ FuncoesGerais.calendarToString(Calendar.getInstance(), "yyyy-MM-dd hh:mm:ss", true) + " WHERE "
				+ tabela.getIdNome() + " = " + tabela.getId();
		PreparedStatement stmt = getPreparedStatement(sql);
		executeUpdate(stmt);
	}

	public Tabela select(Tabela tabela, String id, String where, String groupBy, String orderBy, String limit) {
				
		return select(tabela, id, null, where, groupBy, orderBy, limit);
	}

	public List<?> selectAll(Tabela tabela, String where, boolean pegaExcluidos) {
		return selectAll(tabela, where, pegaExcluidos, null, null, null, null);
	}

	public List<?> selectAll(Tabela tabela, String where, boolean pegaExcluidos, String[] selectionArgs, String groupBy,
			String orderBy, String limit) {
		if (!pegaExcluidos) {
			if (where != null) {
				where = where + " AND " + tabela.getDataExclusaoNome() + " IS NULL";
			} else {
				where = tabela.getDataExclusaoNome() + " IS NULL";
			}
		}
		long i = countIdEntidade(tabela);
		try {
			String sql = sqlSelect(tabela, null, where, groupBy, orderBy, limit);
			PreparedStatement stmt = getPreparedStatement(sql);
			ResultSet set = getResultSet(stmt);
			RegistrosResultSet rSet = new RegistrosResultSet(set);
			HashMap<String, Object> map = tabela.getMapAtributos(false);

			List<Tabela> listEntidades = new ArrayList();
			while (set.next()) {
				Tabela e = FuncoesSql.percorreColunasSqlEAdicionaNoMap(this, rSet, tabela);

				listEntidades.add(e);
			}
			rSet.close();

			return listEntidades;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getIdPorCpf(Tabela tabela, String cpfCnpj) {
		try {

			String sql = "SELECT e." + tabela.getIdNome() + " FROM  "
					+ tabela.getCaminhoTabelaBanco(FuncoesSql.SQL_SERVER) + " e JOIN "
					+ new Pessoa().getCaminhoTabelaBanco(FuncoesSql.SQL_SERVER)
					+ " p ON p.id = pessoa WHERE cpfCnpj like '" + cpfCnpj + "'";
			PreparedStatement stmt = getPreparedStatement(sql);
			System.out.println(sql);
			ResultSet set = stmt.executeQuery();
			String id = "";
			while (set.next()) {
				id = set.getString(tabela.getIdNome());
			}
			System.out.println("id: " + id);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void executeUpdate(PreparedStatement stmt) {
		try {
			stmt.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
	}

	private String sqlSelect(Tabela tabela, String id, String where, String groupBy, String orderBy, String limit) {
		String sql = "SELECT * FROM " + tabela.getNomeTabela(false);

		id = (id == null ? " 1=1 " : tabela.getIdNome() + " = " + id);
		where = " WHERE " + (where == null ? " 1=1 " : where) + " AND " + id;
		groupBy = groupBy == null ? " " : " GROUP BY " + groupBy;
		orderBy = orderBy == null ? " " : " ORDER BY " + orderBy;
		sql = sql + where + groupBy + orderBy;
		return sql;
	}

	public void close(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countIdEntidadeCriacao(Tabela tabela) {
		String sql = "SELECT COUNT(*) AS COUNT FROM " + tabela.getCaminhoTabelaBanco(FuncoesSql.SQL_SERVER);

		PreparedStatement stmt = getPreparedStatement(sql);
		ResultSet set = getResultSet(stmt);
		RegistrosResultSet iSet = new RegistrosResultSet(set);
		int count = iSet.getCount();
		return count;
	}

	public void close() {
	}

	public void execSQL(String sql) {
		PreparedStatement stmt = getPreparedStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement getPreparedStatement(String sql) {
		try {
			return this.con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private ResultSet getResultSet(PreparedStatement preparedStatement) {
		try {
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tabela select(Tabela tabela, String id, String[] colunasParaPesquisar, String where, String groupBy,
			String orderBy, String limit) {
		String[] s = tabela.getNomesAtributos();
		HashMap<String, Object> map = tabela.getMapAtributos(false);
		String filtraExclusao = " dataExclusao IS NULL ";
		where = where == null ? filtraExclusao : where + " AND " + filtraExclusao;
		if (id != null) {
			where += " AND " + tabela.getIdNome() + " = " + id;
		}
		String nomeTabela = tabela.getNomeTabela(false);
		String sql = sqlSelect(tabela, id, where, groupBy, orderBy, limit);
		try {
			PreparedStatement stmt = getPreparedStatement(sql);
			ResultSet set = stmt.executeQuery();
			RegistrosResultSet iSet = new RegistrosResultSet(set);
			while (set.next()) {
				tabela = FuncoesSql.percorreColunasSqlEAdicionaNoMap(this, iSet, tabela);
				return tabela;
			}
			stmt.close();
			set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
