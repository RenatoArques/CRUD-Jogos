package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    //Dados para a conexao com o BD
    private static final String url = "jdbc:postgresql://localhost:5432/jogos";
    private static final String usuario = "postgres";
    private static final String senha = "ctibauru";

    //Metodo para retornar uma conexao ativa com o banco
    //throw SQLException caso ocorra algum erro de conexao
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
