package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {

    private static Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainFront.fxml"));

        primaryStage.setTitle("figaro");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main1(String[] args) {
        launch(args);
    }


}
