package presentation;

import domain.AuthentificationManager;
import domain.User;
import domain.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button btnLogin;
    public TextField tfUsername;
    public TextField tfPassword;
    public VBox vbLogin;
    public GridPane gpBackground;
    public ImageView ivLogo;
    private AuthentificationManager am;
    private UserManager um = new UserManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ivLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        am = new AuthentificationManager();
    }

    public void Login(ActionEvent actionEvent) throws IOException {
        am.login(tfUsername.getText(), tfPassword.getText());

        if (am.login(tfUsername.getText(), tfPassword.getText())) {
            ArrayList<User> userList = new ArrayList<>();
            userList.addAll(um.getAdminList());
            userList.addAll(um.getProducerList());
            for (int i = 0; i < userList.size(); i++){
                if (userList.get(i).getUsername().equals(tfUsername.getText())){
                    am.setCurrentUser(userList.get(i));
                }
            }
            App.setRoot("home");
        }
    }
}
