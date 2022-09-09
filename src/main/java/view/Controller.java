package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import controller.BancoController;
import controller.GeralController;

import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class Controller implements Initializable {


    @FXML
    private ComboBox<String> cmbox;


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
    public void nameChange(MouseEvent mouseEvent) {
            try {
               CPF= cpf_digitado.getText();
               senha =  senha_login.getText();
                GeralController login = new GeralController();
                login.figaro(CPF,senha);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
           /* cpf_digitado.textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        try {
                            if (!newValue.equals("")) parseInt(newValue);
                        } catch (Exception e) {
                            cpf_digitado.setText(oldValue);
                        }
                    }
            );
*/        Vector<Integer> idcargo= new Vector<Integer>();
       try{
           ResultSet rs = batman.listaentidade();
           while(rs.next()) {
               idcargo.addElement(rs.getInt(1));
               cmbox.getItems().add(rs.getString(2));
           }
       }catch (Exception e){
           System.out.println("odeio");
       }
        }
    }

