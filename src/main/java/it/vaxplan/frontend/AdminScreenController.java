package it.vaxplan.frontend;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminScreenController {
    public void logoutAction(ActionEvent actionEvent) throws IOException {
        LoginScreen.setRoot("welcome");
    }
}
