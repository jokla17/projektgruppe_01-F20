module presentation {
    requires javafx.controls;
    requires javafx.fxml;

    opens presentation to javafx.fxml;
    exports presentation;
}