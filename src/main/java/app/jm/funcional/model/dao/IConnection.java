package app.jm.funcional.model.dao;

import java.util.Calendar;
import java.util.List;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.funcoesGerais.FuncoesGerais;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;
import app.jm.funcional.model.entidades.vendas.Caixa;
import app.jm.funcional.model.entidades.vendas.HorariosCaixa;

public interface IConnection {
	
	int getTipoSql();
	
    void insert(Tabela t);
    void insert(List<?> t);
    void close();
    void execSQL(String sql);
    List<?> selectAll(Tabela tabela,String where, boolean pegaExcluidos);
    List<?> selectAll(Tabela tabela, String where, boolean pegaExcluidos, String[] colunasParaPesquisar, String groupBy,String orderBy,String limit);
    Tabela select(Tabela tabela, String id,String[] colunasParaPesquisar, String where,String groupBy,String orderBy, String limit);
    int countIdEntidadeCriacao(Tabela tabela);
    
    public default EmpresaCliente selectEmpresaCliente() {
    	return (EmpresaCliente) select(new EmpresaCliente(), null, null, null, null, null);
    }
    
    public default Usuario selectUsuario() {
    	return (Usuario) select(new Usuario(), null, null, null, null, null);
    }
    
    public default Tabela select(Tabela tabela, String id, String where,String groupBy,String orderBy, String limit) {
    	return select(tabela, id, null, where, groupBy, orderBy, limit);
    }

    
    public default Tabela select(Tabela tabela,String where, String[] colunasAPesquisar) {
    	return select(tabela, null, colunasAPesquisar, where, null,null, null);
    }
    

    public default void update(Tabela t) {
    	deleteLogico(t);
    //	t.anulaMapAtributo();
    	t.setIdAnterior(t.getId());
    	t.setId(0);
    	insert(t);
    }
    
    public default void deleteLogico(Tabela tabela) {
        String sql = getTipoSql() == FuncoesSql.SQL_SERVER ? "UPDATE " + tabela.getNomeTabela(false)
                + " SET " + tabela.getDataExclusaoNome() + " = " + FuncoesGerais.calendarToString(Calendar.getInstance(), FuncoesGerais.yyyyMMdd_HHMMSS, true)
                + " WHERE " + tabela.getIdNome() + " = " + tabela.getId()
                : "DELETE FROM " + tabela.getNomeTabela(false) + " WHERE id = " + tabela.getId();
        
        execSQL(sql);
        close();
    }
    
    public default long countIdEntidade(Tabela tabela) {
    	String[] s = {"COUNT(*) AS id"};
        Tabela tabResultado = select(tabela,null,s,null,null,null,null);

        return tabResultado == null ? 0 : tabResultado.getId();
    }
    
    public default void updateCaixa(Caixa caixa) {
    	String sql = "UPDATE Caixa SET valorTotal = valorTotal + #valorTotal,"
    			+ " valorTotalAVista = valorTotalAVista + #totalAVista,"
    			+ " valorTotalAPrazo = valorTotalAPrazo + #totalAPrazo,"
    			+ " valorDesconto = valorDesconto + #valorDesconto WHERE data = #data;";
    	
    	sql = sql.replace("#valorTotal", String.valueOf(caixa.getValorTotal()))
    		.replace("#totalAVista",String.valueOf(caixa.getValorTotalAVista()))
    		.replace("#totalAPrazo", String.valueOf(caixa.getValorTotalAPrazo()))
    		.replace("#valorDesconto", String.valueOf(caixa.getValorDesconto()))
    		.replace("#data", FuncoesGerais.calendarToString(Calendar.getInstance(), FuncoesGerais.yyyyMMdd_HHMMSS, true));
    	
    	execSQL(sql);
    }
    
    public default long countIdNaEmpresa(Tabela tabela) {
    	String[] col = {"idNaEmpresa"};
    	Tabela t = select(tabela,null,null,"empresaClienteId = " + tabela.getEmpresaClienteId(),null,"idNaEmpresa DESC",null);
    	return t == null ? 1 : t.getIdNaEmpresa() + 1;
    }
    
    public default void updateHorarioCaixa(HorariosCaixa horario) {
    	String sql = "UPDATE HorariosCaixa SET abertura = '#abertura' AND encerramento = '#encerramento' WHERE id = #id";
    	sql = sql.replace("#abertura", FuncoesGerais.timeToString(horario.getAbertura(), FuncoesGerais.hhmmss))
    			.replace("#encerramento", FuncoesGerais.timeToString(horario.getEncerramento(), FuncoesGerais.hhmmss))
    			.replace("#id", horario.getId()+"");
    	execSQL(sql);
    }
}
