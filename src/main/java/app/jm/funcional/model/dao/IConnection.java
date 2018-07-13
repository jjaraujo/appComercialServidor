package app.jm.funcional.model.dao;

import java.util.Calendar;
import java.util.List;

import app.jm.funcional.controller.funcoesGerais.FuncoesGerais;
import app.jm.funcional.model.Tabela;
import app.jm.funcional.model.entidades.cadastral.pessoas.EmpresaCliente;
import app.jm.funcional.model.entidades.cadastral.pessoas.Usuario;

public interface IConnection {
    int countIdEntidade(Tabela t);
    void insert(Tabela t);
    void insert(List<?> t);
    void close();
    void execSQL(String sql);
    List<?> selectAll(Tabela tabela,String where, boolean pegaExcluidos);
    List<?>  selectAll(Tabela tabela, String where, boolean pegaExcluidos,String[] selectionArgs,String groupBy,String orderBy,String limit);
    Tabela select(Tabela tabela, String id, String where,String groupBy,String orderBy, String limit);
    int countIdEntidadeCriacao(Tabela tabela);
    
    public default EmpresaCliente selectEmpresaCliente() {
    	return (EmpresaCliente) select(new EmpresaCliente(), null, null, null, null, null);
    }
    
    public default Usuario selectUsuario() {
    	return (Usuario) select(new Usuario(), null, null, null, null, null);
    }
    

    public default void update(Tabela t) {
    	deleteLogico(t);
    	t.setIdAnterior(t.getId());
    	t.setId(0);
    	insert(t);
    }
    
    public default void deleteLogico(Tabela tabela) {
        String sql = "UPDATE " + tabela.getNomeTabela(false)
                + " SET " + tabela.getDataExclusaoNome() + " = " + FuncoesGerais.calendarToString(Calendar.getInstance(), FuncoesGerais.yyyyMMdd_HHMMSS, true)
                + " WHERE " + tabela.getIdNome() + " = " + tabela.getId();
        
        execSQL(sql);
        close();
    }
}
