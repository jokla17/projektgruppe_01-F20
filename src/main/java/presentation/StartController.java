package presentation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    public TextField tfSearchFIeld;
    public Button btnSearch;
    public RadioButton rbProductions;
    public ToggleGroup searchSelections;
    public RadioButton rbCredits;
    public ListView lvResults;
    public Button btnCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void forwardToLogin(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }


    public void search(ActionEvent actionEvent) {
        if(tfSearchFIeld.getText().isEmpty()){
            if(rbProductions.isSelected()){
                lvResults.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
            }else if(rbCredits.isSelected()){
                App.getCreditManager().setAllCreditList();
                lvResults.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
            }
            return;
        }
        if(rbProductions.isSelected()){
            lvResults.setItems(FXCollections.observableArrayList(
                    App.getProductionManager().readProduction(tfSearchFIeld.getText())));
        }else if(rbCredits.isSelected()){
            App.getCreditManager().setAllCreditList();
            lvResults.setItems(FXCollections.observableArrayList(
                    App.getCreditManager().readCredit(tfSearchFIeld.getText())));
        }

    }
}
