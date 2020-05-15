module presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires postgresql;
    requires junit;

    opens presentation to javafx.fxml;
    opens domain to javafx.base;
    opens test to junit;
    exports presentation;
}