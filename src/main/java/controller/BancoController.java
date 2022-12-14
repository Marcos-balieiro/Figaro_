package controller;

import Banco.ConexaoSQLite;
import modelo.*;
import repository.SeleniumTRF1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoController {

    ControllerEntidade controllerEntidade;
    private SeleniumTRF1 repository = new SeleniumTRF1();

    public Entidade dadosEntidade(String entity, String TRF) {
        Entidade entidade = new Entidade();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Entidade where nome ="+"'"+entity+"'"+";";
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery() ;
                entidade.setNome(resultSet.getString("nome"));
                entidade.setSigla(resultSet.getString("sigla"));
                entidade.setId_classe_judicial(resultSet.getString("id_classe_judicial"));
                entidade.setId_assunto(resultSet.getString("id_assunto"));

            }
            catch (Exception e){

            }
        conexaoSQLite.desconectar();
        return entidade;
    }
    public Classe_Judicial dadosjudicial(String pesquisaClasse) {
        Classe_Judicial classe = new Classe_Judicial();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Classe_Judicial where id=" + pesquisaClasse;
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery();
            classe.setNome(resultSet.getString("nome"));

        }
        catch (Exception e){

        }
        conexaoSQLite.desconectar();
        return classe;
    }
    public Assunto dadosassunto(String pesquisaassunto) {
        Assunto assunto = new Assunto();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Assunto where id=" + pesquisaassunto;
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery();
            assunto.setNome(resultSet.getString("nome"));

        }
        catch (Exception e){

        }
        conexaoSQLite.desconectar();
        return assunto;
    }

    public List<Entidade> entidadeList() {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Entidade";
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        @SuppressWarnings("unchecked")
                List<Entidade> lista = new ArrayList<Entidade>();

        try {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Entidade entidade = new Entidade();
                entidade.setNome(resultSet.getString("nome"));
                lista.add(entidade);

            }
        } catch (Exception e) {

        }
        conexaoSQLite.desconectar();
        return lista;
    }
    public ResultSet listaentidade() {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Entidade";
        try {
            PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    public Usuario dadosusuario() {
        Usuario usuario = new Usuario();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Usuario where id=1";
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery();
            usuario.setCPF(resultSet.getString("cpf"));
            usuario.setSENHA(resultSet.getString("senha"));

        }
        catch (Exception e){

        }
        conexaoSQLite.desconectar();
        return usuario;
    }
    public PesquisaSave dadospesquisa() {
        PesquisaSave pesquisaSave = new PesquisaSave();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT * FROM Entidadesp where id=1";
        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery();
            pesquisaSave.setEntidadesp(resultSet.getString("SaveEnti"));
        }
        catch (Exception e){

        }
        conexaoSQLite.desconectar();
        return pesquisaSave;
    }
}

