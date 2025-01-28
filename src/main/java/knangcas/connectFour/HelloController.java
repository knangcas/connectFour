package knangcas.connectFour;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class HelloController {


    @FXML
    private GridPane c4board;



    public void initalize() {

    }
    public void spotClick(MouseEvent mouseEvent) {




    }

    public void playerMove(MouseEvent mouseEvent) {

        Pane pane = (Pane) mouseEvent.getSource();
        pane.getId();
        System.out.println(pane.getId());

    }
}