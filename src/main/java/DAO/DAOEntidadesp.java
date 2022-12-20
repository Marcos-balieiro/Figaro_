package DAO;


import Banco.ConexaoSQLite;
import modelo.PesquisaSave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOEntidadesp  extends ConexaoSQLite{
    public boolean salvarentisp(PesquisaSave pesquisaSave) {
        conectar();
        String verific = "SELECT * FROM Entidadesp where id = 1;";
        PreparedStatement preparedStatement1 = criarPreparedStatement(verific, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet rs = preparedStatement1.executeQuery();
            if (rs.next()) {
                String sql = "UPDATE Entidadesp SET SaveEnti = ? where id=1";
                PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    preparedStatement.setString(1, pesquisaSave.getEntidadesp());
                    preparedStatement.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return true;

            } else {
                String sql = "INSERT INTO Entidadesp(" +"id,"+
                        "SaveEnti) "  +
                        "VALUES (?,?)";
                PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    preparedStatement.setString(1, "1");
                    preparedStatement.setString(2, pesquisaSave.getEntidadesp());
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
