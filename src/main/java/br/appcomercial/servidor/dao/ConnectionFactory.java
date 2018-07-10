package br.appcomercial.servidor.dao;


import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  public Connection getConnection()
  {
    try {
      DriverManager.registerDriver(new SQLServerDriver());
      String usuario = "sa";
      String senha = "123spn";
      return DriverManager.getConnection(
        "jdbc:sqlserver://localhost:1433;database=aplicacaocomercial;", 
        usuario, senha);
    }
    catch (SQLException e)
    {
      System.out.println("Erro na conexao");
      e.printStackTrace();
    }
    return null;
  }
}