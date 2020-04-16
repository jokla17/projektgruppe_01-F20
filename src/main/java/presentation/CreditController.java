package presentation;

import domain.Credit;
import domain.CreditManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditController extends MainController implements Initializable {
    public TextField tfCreditRole;
    public TextField tfCreditName;
    public TextArea taCreditsArea;
    public TableView<Credit> tvCreditTable;
    public TableColumn<Credit, String> tcId;
    public TableColumn<Credit, String> tcRole;
    public TableColumn<Credit, String> tcName;
    public TextField tfProductionID;

    private CreditManager cms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        tcId.setCellValueFactory(new PropertyValueFactory<>("CreditId"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("CreditRole"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("CreditName"));
        cms = new CreditManager();
        tvCreditTable.setItems(FXCollections.observableArrayList(cms.getCreditList()));
    }

    // Create multiple credits handler
    public void createMultipleCredits(ActionEvent actionEvent) {
        cms.createCredit(taCreditsArea);
        tvCreditTable.setItems(FXCollections.observableArrayList(cms.getCreditList()));
        taCreditsArea.clear();
    }

    // Create single credit handler
    public void createSingleCredit(ActionEvent actionEvent) {
        cms.createCredit(tfCreditRole.getText(), tfCreditName.getText());
        tvCreditTable.setItems(FXCollections.observableArrayList(cms.getCreditList()));
    }

    // Update credit handler
    public void updateCredit(ActionEvent actionEvent) {
        cms.updateCredit(tvCreditTable.getSelectionModel().getSelectedItem(), tfCreditRole.getText(), tfCreditName.getText());
        tvCreditTable.refresh();
    }

    // Delete credit handler
    public void deleteCredit(ActionEvent actionEvent) {
        cms.deleteCredit(tvCreditTable.getSelectionModel().getSelectedItem());
        tvCreditTable.setItems(FXCollections.observableArrayList(cms.getCreditList()));
    }

    // Search functionality handler
    public void searchFunctionality(ActionEvent actionEvent) {
        tvCreditTable.setItems(
                FXCollections.observableArrayList(cms.searchCredit(cms.getCreditList(), tfSearch.getText())));
    }

    // Select credit handler
    public void selectCredit(MouseEvent mouseEvent) {
        tfCreditRole.setText(tvCreditTable.getSelectionModel().getSelectedItem().getCreditRole());
        tfCreditName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getCreditName());
    }

    public void Gem(ActionEvent actionEvent) {
        cms.saveCredits(tfProductionID);
    }
}
