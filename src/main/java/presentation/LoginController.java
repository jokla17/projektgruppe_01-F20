package presentation;

import domain.Production;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController extends MainController implements Initializable {
    public Button btnLogin;
    public TextField tfUsername;
    public TextField tfPassword;
    public VBox vbLogin;
    public GridPane gpBackground;
    public ImageView ivLogo;
    public Text spNotificationText;
    public StackPane spNotificationBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ivLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
    }

    public void Login(ActionEvent actionEvent) throws IOException {
        if (tfUsername.getText().isEmpty() | tfPassword.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-deleted",
                    Production.class.getSimpleName(), 0, btnLogin);
            return;
        }

        App.getAuthentificationManager().login(tfUsername.getText(), tfPassword.getText());

        if (App.getAuthentificationManager().login(tfUsername.getText(), tfPassword.getText())) {
            ArrayList<User> userList = new ArrayList<>();
            userList.addAll(App.getUserManager().getAdminList());
            userList.addAll(App.getUserManager().getProducerList());

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(tfUsername.getText())) {
                    App.getAuthentificationManager().setCurrentUser(userList.get(i));
                }
            }
            App.setRoot("production");
        }
    }

    public void backToFrontPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("Start");
    }
}
