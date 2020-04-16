package presentation;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
    }
}
