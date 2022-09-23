package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import controller.BancoController;
import controller.GeralController;
import javafx.scene.control.CheckBox;
import DAO.DAOUsuario;
import modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class Controller implements Initializable {


    @FXML
    private ComboBox<String> cmbox;

    @FXML
    private Button TODOS;

    @FXML
    private CheckBox CREDEN;
    @FXML
    private TextField cpf_digitado;

    @FXML
    private PasswordField senha_login;

    @FXML
    private Text texto;

    @FXML
    private Text texto1;
    String CPF;
    String senha;

    private BancoController batman = new BancoController();

    @FXML
    public String nameChange() throws SQLException {
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
            login.figaro(CPF, senha, Entity);
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

        Vector<Integer> idcargo = new Vector<Integer>();
        try {
            ResultSet rs = batman.listaentidade();
            while (rs.next()) {
                idcargo.addElement(rs.getInt(1));
                cmbox.getItems().add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("odeio");
        }

        File file = new File("C:\\Utility\\Downloads\\"+"Petição inicial.pdf");
        File file2 = new File("C:\\Utility\\Downloads\\"+ "1245788.pdf");

        file.renameTo(file2);
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
                login.figarotodos(CPF, senha, idcargo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            throw  new Error("morra moraa");
        }
        }

