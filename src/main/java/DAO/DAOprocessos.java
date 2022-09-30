package DAO;


import Banco.ConexaoSQLite;
import modelo.Entidade;
import modelo.Processo;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOprocessos extends ConexaoSQLite {

    public boolean salvarprocessos(Processo processo) {
        conectar();
        try {
            String sql = "INSERT INTO processos("
                    + "Numeroproc, "
                    +  "nome_entidade, "
                    +  "data_encontrada)"
                    + " VALUES (?,?,?)";
            PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try {
                preparedStatement.setString(1,processo.getProcessos());
                preparedStatement.setString(2,processo.getNomeEntidade());
                preparedStatement.setString(3,processo.getDataEncontrada());
                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}




