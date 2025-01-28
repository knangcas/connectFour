module knangcas.connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens knangcas.connectFour to javafx.fxml;
    exports knangcas.connectFour;
    exports knangcas.connectFour.model;
    opens knangcas.connectFour.model to javafx.fxml;
}