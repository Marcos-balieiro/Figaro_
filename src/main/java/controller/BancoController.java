package controller;

import Banco.ConexaoSQLite;
import modelo.*;
import repository.SeleniumService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BancoController {

    ControllerEntidade controllerEntidade;
    private SeleniumService repository = new SeleniumService();

    public Entidade dadosEntidade(String entity) {
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

    public Set<EntidadeMapeada> EntidadesMapeadas() {
        Set<EntidadeMapeada> entidades = new HashSet<EntidadeMapeada>();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sql = "SELECT entidade.nome, entidade.sigla, classe_judicial.classe,'classe', entidade.grupo "
                + "FROM entidade_classe \n"
                + "JOIN classe_judicial ON entidade_classe.classe_id = classe_judicial.id_classe \n"
                + "JOIN entidade ON entidade_classe.entidade_id = entidade.id_entidade \n"
                + "union\n"
                + "SELECT entidade.nome, entidade.sigla, assunto.assunto, 'assunto', entidade.grupo FROM entidade_assunto \n"
                + "JOIN assunto ON entidade_assunto.assunto_id = assunto.id_assunto \n"
                + "JOIN entidade ON entidade_assunto.entidade_id = entidade.id_entidade";

        PreparedStatement statement = conexaoSQLite.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ResultSet resultSet = statement.executeQuery() ;

            while(resultSet.next()){

                EntidadeMapeada entidade = new EntidadeMapeada();

                entidade.setNome(resultSet.getString(1));
                entidade.setSigla(resultSet.getString(2));

                        String colunaDeAtributos = resultSet.getString(3);
                System.out.println(colunaDeAtributos);
                        if(resultSet.getString(4).equals("assunto") && !colunaDeAtributos.equals("todos")  ){

                            entidade.setClasseJudicial("");
                            entidade.setAssunto(resultSet.getString(3));


                        }else if(resultSet.getString(4).equals("classe") && !colunaDeAtributos.equals("todos")){

                            entidade.setClasseJudicial(resultSet.getString(3));
                            entidade.setAssunto("");
                        }else{
                            entidade.setClasseJudicial("");
                            entidade.setAssunto("");
                        }

                entidade.setGrupo(resultSet.getString(5));
                entidades.add(entidade);
            }

            System.out.println("*************************************************************" +
                    "*******************************************************************" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "7777777777777777777777777777777777777777777777777777777777777777777");
            entidades.forEach(entidade->System.out.println(entidade.getNome()+"/"+entidade.getAssunto()+"/"+entidade.getClasseJudicial()+"/"+entidade.getGrupo()));

        }
        catch (Exception e){

        }
        conexaoSQLite.desconectar();
        return entidades;
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
}

