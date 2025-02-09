package knangcas.connectFour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import knangcas.connectFour.controller.GameController;

import java.io.IOException;

public class ConnectFourApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApp.class.getResource("connectFourBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GameController controller = fxmlLoader.getController();
        controller.initialize();
        stage.setTitle("ConnectFour");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}