package view;

import javafx.event.ActionEvent;
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

public class MainController implements Initializable {

    @FXML
    private void trocaTela(ActionEvent e){
        System.out.println("botao novo");
    }
    @FXML
    private ComboBox<String> cmbox;
    @FXML
    private ComboBox<String> cmboxTRFs;
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
            String TRF= cmboxTRFs.getSelectionModel().getSelectedItem();

            GeralController login = new GeralController();
            if(data_inicio.getValue() == null){
                String sasha= null;
                String xuxa = null;
                if(Entity== "TODAS AS ENTIDADES")
                {
                    Vector idcargo = LOPPING();
                    login.figarotodos(CPF, senha, idcargo,sasha,xuxa,TRF);
                }
                login.figaro(CPF, senha, Entity,sasha,xuxa,TRF);
            }
            dataInicio = data_inicio.getValue();
            LocalDate data_fin = data_fim.getValue();
            DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String xuxa= dataInicio.format(formatadorDeData);
            String sasha= data_fin.format(formatadorDeData);
            if(Entity== "TODAS AS ENTIDADES")
            {
                Vector idcargo = LOPPING();
                login.figarotodos(CPF, senha, idcargo,sasha,xuxa, TRF);
            }

            login.figaro(CPF, senha, Entity,sasha,xuxa, TRF);
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
        cmboxTRFs.getItems().add("TRF1");
        cmboxTRFs.getItems().add("TRF6");

        Vector<Integer> idcargo = new Vector<>();
        try {
            ResultSet rs = batman.listaentidade();

            cmbox.getItems().add("TODAS AS ENTIDADES");
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
    public Vector<String> LOPPING() {
        Vector<String> idcargo = new Vector<String>();
        try {
            ResultSet rs = batman.listaentidade();
            while (rs.next()) {

                idcargo.add(rs.getString(2));
            }
            return idcargo;
        } catch (SQLException e) {
            System.out.println("me fudi");;
        }
        return idcargo;
    }
        public void abrirpasta(javafx.event.ActionEvent abrir) throws IOException {
        Desktop.getDesktop().open(new File("C:\\Figaro\\Processos"));
    }
}

