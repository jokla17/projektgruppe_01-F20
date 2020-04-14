package presentation;

import domain.AuthentificationManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public Button btnLogin;
    public TextField tfUsername;
    public TextField tfPassword;
    AuthentificationManager AFM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    AFM = new AuthentificationManager();
    }

    public void Login(ActionEvent actionEvent) throws IOException {
    AFM.login(tfUsername.getText(), tfPassword.getText());
    if (AFM.login(tfUsername.getText(), tfPassword.getText()) == true){
        App.setRoot("production");
    }

    }
}
