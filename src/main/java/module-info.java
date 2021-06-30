module it.vaxplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires static lombok;

    opens it.vaxplan to javafx.fxml;
    exports it.vaxplan.frontend;
    opens it.vaxplan.frontend to javafx.fxml;
}
