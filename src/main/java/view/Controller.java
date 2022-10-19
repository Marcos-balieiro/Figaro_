package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import controller.BancoController;
import controller.GeralController;
import DAO.DAOUsuario;
import modelo.Usuario;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Vector;

public class Controller implements Initializable {


    @FXML
    private ComboBox<String> cmbox;
    @FXML
    private CheckBox CREDEN;
    @FXML
    private TextField cpf_digitado;

    @FXML
    private PasswordField senha_login;
    @FXML
    private DatePicker data_inicio;

    @FXML
    private DatePicker data_fim;

    String CPF;
    String senha;
    LocalDate dataInicio = null;
    private BancoController batman = new BancoController();

    @FXML
    public String nameChange() {
        if (CREDEN.isSelected()) {
            CPF = cpf_digitado.getText();
            senha = senha_login.getText();
            Usuario usu = new Usuario();
            usu.setCPF(CPF);
            usu.setSENHA(senha);
            DAOUsuario user = new DAOUsuario();
            user.salvarUsuario(usu);
        }
        try {
            CPF = cpf_digitado.getText();
            senha = senha_login.getText();
            String Entity = cmbox.getSelectionModel().getSelectedItem();
            GeralController login = new GeralController();
            if(data_inicio.getValue() == null){
                String sasha= null;
                String xuxa = null;
                login.figaro(CPF, senha, Entity,sasha,xuxa);
            }
            dataInicio = data_inicio.getValue();
            LocalDate data_fin = data_fim.getValue();
            DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String xuxa= dataInicio.format(formatadorDeData);
            String sasha= data_fin.format(formatadorDeData);

            login.figaro(CPF, senha, Entity,sasha,xuxa);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Usuario USUARIO = batman.dadosusuario();
        cpf_digitado.setText(USUARIO.getCPF());
        senha_login.setText(USUARIO.getSENHA());

        Vector<Integer> idcargo = new Vector<>();
        try {
            ResultSet rs = batman.listaentidade();
            while (rs.next()) {
                idcargo.addElement(rs.getInt(1));
                cmbox.getItems().add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("odeio");
        }

    }

    public void CREDENCI(javafx.event.ActionEvent event) {

    }
    public void LOPPING(javafx.event.ActionEvent event) {
        if (CREDEN.isSelected()) {
            CPF = cpf_digitado.getText();
            senha = senha_login.getText();
            Usuario usu = new Usuario();
            usu.setCPF(CPF);
            usu.setSENHA(senha);
            DAOUsuario user = new DAOUsuario();
            user.salvarUsuario(usu);
        }
        GeralController login = new GeralController();
        Vector<String> idcargo = new Vector<String>();
        try {
            ResultSet rs = batman.listaentidade();
            while (rs.next()) {

                idcargo.add(rs.getString(2));

            }
            CPF = cpf_digitado.getText();
            senha = senha_login.getText();
            if(data_inicio.getValue() == null){
                String sasha= null;
                String xuxa = null;
                login.figarotodos(CPF, senha,idcargo,sasha,xuxa);
            }
            dataInicio = data_inicio.getValue();
            LocalDate data_fin = data_fim.getValue();
            DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String xuxa = dataInicio.format(formatadorDeData);
            String sasha = data_fin.format(formatadorDeData);
            login.figarotodos(CPF, senha, idcargo,sasha,xuxa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        throw  new Error("morra moraa");
    }
    public void abrirpasta(javafx.event.ActionEvent abrir) throws IOException {
        Desktop.getDesktop().open(new File("C:\\Figaro\\Processos"));
    }
}

