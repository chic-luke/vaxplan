module it.vaxplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.validation;
    requires com.jfoenix;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens it.vaxplan to javafx.fxml;
    exports it.vaxplan.frontend;
    opens it.vaxplan.frontend to javafx.fxml;
    exports it.vaxplan to com.fasterxml.jackson.databind;
    exports it.vaxplan.backend.json to com.fasterxml.jackson.databind;
    exports it.vaxplan.backend to com.fasterxml.jackson.databind;
    exports it.vaxplan.backend.json.pojo to com.fasterxml.jackson.databind;
}
