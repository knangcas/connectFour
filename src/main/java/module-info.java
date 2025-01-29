module knangcas.connectFour {
    requires javafx.controls;
    requires javafx.fxml;


    opens knangcas.connectFour to javafx.fxml;
    exports knangcas.connectFour;
    exports knangcas.connectFour.model;
    opens knangcas.connectFour.model to javafx.fxml;
    exports knangcas.connectFour.controller;
    opens knangcas.connectFour.controller to javafx.fxml;
}