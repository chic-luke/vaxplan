module it.vaxplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.validation;

    opens it.vaxplan to javafx.fxml;
    exports it.vaxplan.frontend;
    opens it.vaxplan.frontend to javafx.fxml;
}
