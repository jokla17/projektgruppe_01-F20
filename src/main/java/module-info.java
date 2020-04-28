module presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires postgresql;

    opens presentation to javafx.fxml;
    opens domain to javafx.base;
    exports presentation;
}