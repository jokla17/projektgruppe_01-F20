module presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens presentation to javafx.fxml;
    opens domain to javafx.base;
    exports presentation;
}