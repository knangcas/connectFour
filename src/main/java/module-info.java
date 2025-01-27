module knangcas.connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens knangcas.connect4 to javafx.fxml;
    exports knangcas.connect4;
}