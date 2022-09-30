package DAO;


import Banco.ConexaoSQLite;
import modelo.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUsuario extends ConexaoSQLite {
    public boolean salvarUsuario(Usuario usuario) {
        conectar();
        String verific = "SELECT * FROM Usuario where cpf;";
        PreparedStatement preparedStatement1 = criarPreparedStatement(verific, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet rs = preparedStatement1.executeQuery();
            if (rs.next()) {
                String sql = "UPDATE Usuario SET cpf = ?, senha = ? where id=1";
                PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    preparedStatement.setString(1, usuario.getCPF());
                    preparedStatement.setString(2, usuario.getSENHA());
                    preparedStatement.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return true;

            } else {
                String sql = "INSERT INTO Usuario(" +
                        "cpf, " +
                        "senha) " +
                        "VALUES (?,?)";
                PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    preparedStatement.setString(1, usuario.getCPF());
                    preparedStatement.setString(2, usuario.getSENHA());
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                desconectar();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}