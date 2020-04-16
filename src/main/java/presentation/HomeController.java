package presentation;

import domain.AuthentificationManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends MainController implements Initializable {

    @FXML
    private Label currentUserLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        currentUserLbl.setText(AuthentificationManager.getCurrentUser().getUsername());
    }
}
