package view;

import controller.BancoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class main extends Application {


    private static Stage stage;

    private static Scene mainScene;
    private static Scene buscaPersonalizadaScene;
    private static Scene teste;
    private static Vector teste9;
    private static BancoController batman = new BancoController();


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setTitle("figaro");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/fxml/MainFront.fxml"));
        mainScene = new Scene(fxmlMain);

        Parent fxmlBuscaPersonalizada = FXMLLoader.load(getClass().getResource("/fxml/BuscaPersonalizada.fxml"));
        buscaPersonalizadaScene = new Scene(fxmlBuscaPersonalizada);

        Parent fxmlteste = FXMLLoader.load(getClass().getResource("/fxml/teste.fxml"));
        teste = new Scene(fxmlteste);


        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public static void changeScreen(String src) {
        switch (src) {
            case "main":
                stage.setScene(mainScene);
                break;
            case "buscaPersonalizada":
                stage.setScene(buscaPersonalizadaScene);
            case "teste":
                stage.setScene(teste);


        }
    }

    public static Vector teste12(Vector src) throws SQLException {
        Vector teste1 = new Vector<>();
        System.out.println(src.get(0));
        ResultSet rs = batman.listaentidade();
        while (rs.next()) {

            teste1.add(rs.getString(2));
        }
            if (teste1.contains(src.get(0))) {
                Vector teste52 = src;
                teste9 = teste52;
                return teste9;
            } else {
                System.out.println(teste9);
                return teste9;
            }

        }


    }

