package it.vaxplan.frontend.controller;

import it.vaxplan.frontend.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ErrorScreenController {

    @FXML
    public Button backButton;

    public void backButtonAction() throws IOException {
        App.setRoot("welcome");
    }

}
