package br.com.jmdesenvolvimento.servidor.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.jmdesenvolvimento.appcomercial.model.dao.IRegistros;

public class RegistrosResultSet implements IRegistros {
  ResultSet resultSet;
  
  public RegistrosResultSet(ResultSet resultSet)
  {
    this.resultSet = resultSet;
  }
  
  public String getString(String coluna)
  {
    try
    {
      return this.resultSet.getString(coluna);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public int getInt(String coluna)
  {
    try
    {
      return this.resultSet.getInt(coluna);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }
  
  public double getDouble(String coluna)
  {
    try
    {
      return this.resultSet.getDouble(coluna);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0.0D;
  }
  
  public long getLong(String coluna)
  {
    try
    {
      return this.resultSet.getLong(coluna);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0L;
  }
  
  public String get(int position)
  {
    try
    {
      return this.resultSet.getMetaData().getColumnName(position + 1);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public int getColumnCount()
  {
    try
    {
      return this.resultSet.getMetaData().getColumnCount();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }
  
  public void close()
  {
    try
    {
      this.resultSet.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public int getCount()
  {
    try
    {
      int count = 0;
      while (this.resultSet.next()) {
        count = this.resultSet.getInt("COUNT");
      }
      return count;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }
  
  public int getTipoSql()
  {
    return 1;
  }
}
