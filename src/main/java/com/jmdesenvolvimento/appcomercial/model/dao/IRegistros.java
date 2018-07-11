package com.jmdesenvolvimento.appcomercial.model.dao;

public interface IRegistros {
	
	
	public String getString(String coluna);
	public int getInt(String coluna);
	public double getDouble(String coluna);
	public long getLong(String coluna);
	public String getNomeColuna(int position);
	public int getColumnCount();
	public int getCount();
	public void close();
	public int getTipoSql();
	
}
