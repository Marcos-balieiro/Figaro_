package view;


import controller.BancoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

public class controlleteste implements Initializable {

        @FXML
        private Button add;

        @FXML
        private ComboBox<String> cmboxze;


        private BancoController batman = new BancoController();

        @FXML
        private TextArea escolhas;
        List<String> list = new ArrayList<>();
        Vector<String> entidades= new Vector<>();

        @FXML
        public void initialize(URL location, ResourceBundle resources) {


                Vector<Integer> idcargo = new Vector<>();
                try {
                        ResultSet rs = batman.listaentidade();

                        cmboxze.getItems().add("TODAS AS ENTIDADES");
                        while (rs.next()) {
                                idcargo.addElement(rs.getInt(1));
                                cmboxze.getItems().add(rs.getString(2));
                        }
                } catch (Exception e) {
                        System.out.println("odeio");
                }

        }
        public void add(javafx.event.ActionEvent actionEvent) {
                try {
                        entidades.addElement(cmboxze.getSelectionModel().getSelectedItem());
                        String vava=escolhas.getText();
                        // List<String> VALORES = new ArrayList<>(Arrays.asList(vava.split(",")));

                                escolhas.setText( vava+"\n" + entidades.lastElement());
                        System.out.println(entidades);

                } catch(Exception e)
                {
                        System.out.println("vai te fude hein");
                        System.out.println(e);
                }
        }
}

