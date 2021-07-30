package it.vaxplan.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationSecondScreenHandler implements Initializable {

    @FXML
    public Button backButton;
    @FXML
    public Button confirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void backButtonAction() throws IOException {
        App.setRoot("registrationscreen");
    }

    public void confirmButtonAction() throws  IOException{
        App.setRoot("welcome");
    }

}
