package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BuscaController  {

    @FXML
    protected void trocaTela(ActionEvent e) {

        main.changeScreen("main");
    }
    @FXML
    protected void confirmaBuscaPersonalizada(ActionEvent e) {

        main.changeScreen("main");
    }

}
