package br.com.jmdesenvolvimento.appcomercial.dao;


import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  public Connection getConnection()
  {
    try
    {
      DriverManager.registerDriver(new SQLServerDriver());
      String usuario = "jjaraujo";
      String senha = "J040M4r14";
      return DriverManager.getConnection(
        "jdbc:sqlserver://aplicacaocomercial.c58bevvh3ij6.us-east-2.rds.amazonaws.com;database=aplicacaocomercial;", 
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