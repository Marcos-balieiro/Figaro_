package controller;

import Banco.ConexaoSQLite;
import modelo.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerUsuario {
    public Usuario usuario(){
        Usuario usuario = new Usuario();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql= "SELECT * FROM Usuario";
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery() ;
            usuario.setCPF(resultSet.getString("cpf"));
            usuario.setSENHA(resultSet.getString("senha"));

        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return usuario;
    }
}

