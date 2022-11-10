package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {

    private static Stage stage;

    private static Scene mainScene;
    private static Scene buscaPersonalizadaScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setTitle("figaro");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/fxml/MainFront.fxml"));
        mainScene = new Scene(fxmlMain);

        Parent fxmlBuscaPersonalizada = FXMLLoader.load(getClass().getResource("/fxml/BuscaPersonalizada.fxml"));
        buscaPersonalizadaScene = new Scene(fxmlBuscaPersonalizada);



        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public static void changeScreen(String src) {
        switch (src){
            case "main":
                stage.setScene(mainScene);
                break;
            case "buscaPersonalizada":
                stage.setScene(buscaPersonalizadaScene);

        }
    }

    public static void main1(String[] args) {
        launch(args);
    }


}
