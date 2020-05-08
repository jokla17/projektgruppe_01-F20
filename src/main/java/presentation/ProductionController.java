package presentation;

import domain.Production;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductionController extends MainController implements Initializable {
    public TextField tfTitle;
    public TextField tfGenre;
    public TextField tfEpisodeNumber;
    public TextField tfProductionCountry;
    public TextField tfProducedBy;
    public TextField tfSearch;
    public TextField tfProductionYear;
    public Label lbCurrentUser;
    public Text spNotificationText;
    public TableView<Production> tvProductions;
    public TableColumn<Production, String> tcID;
    public TableColumn<Production, String> tcTitle;
    public TableColumn<Production, String> tcGenre;
    public TableColumn<Production, Integer> tcEpisodeNumber;
    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnSearch;
    public StackPane spNotificationBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        tcID.setCellValueFactory(new PropertyValueFactory<>("ProductionId"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("EpisodeNumber"));
        lbCurrentUser.setText("Logget på som: " + App.getAuthentificationManager().getCurrentUser().getUsername());

        App.getProductionManager().getProductionList().clear();
        App.getProductionManager().setProductionList();
        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));

        tfProducedBy.setText(App.getAuthentificationManager().getCurrentUser().getFirstName()
                + " " + App.getAuthentificationManager().getCurrentUser().getLastName());
    }

    public void createProduction(ActionEvent actionEvent) {
        if (tfTitle.getText().isEmpty() | tfGenre.getText().isEmpty() | tfEpisodeNumber.getText().isEmpty() | tfProductionCountry.getText().isEmpty() | tfProductionYear.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-deleted", Production.class.getSimpleName(), 0, btnCreate, btnDelete, btnUpdate);
            return;
        }

        App.getProductionManager().createProduction(tfTitle.getText(), tfGenre.getText(),
                Integer.parseInt(tfEpisodeNumber.getText()),
                Integer.parseInt(tfProductionYear.getText()),
                tfProductionCountry.getText(),
                tfProducedBy.getText()
        );

        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-created",
                Production.class.getSimpleName(), 1, btnCreate, btnDelete, btnUpdate);
    }

    public void updateProduction(ActionEvent actionEvent) {
        App.getProductionManager().updateProduction(tvProductions.getSelectionModel().getSelectedItem(),
                tfTitle.getText(), tfGenre.getText(), tfEpisodeNumber.getText(), tfProductionYear.getText(),
                tfProductionCountry.getText(), tfProducedBy.getText());
        tvProductions.refresh();

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-updated",
                Production.class.getSimpleName(), 2, btnCreate, btnDelete, btnUpdate);
    }

    public void deleteProduction(ActionEvent actionEvent) {
        App.getProductionManager().deleteProduction(tvProductions.getSelectionModel().getSelectedItem());
        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-deleted",
                Production.class.getSimpleName(), 3, btnCreate, btnDelete, btnUpdate);
    }

    public void searchFunctionality(ActionEvent actionEvent) {
        List<Production> searchResult = App.getProductionManager().readProduction(tfSearch.getText());
        tvProductions.setItems((FXCollections.observableArrayList(searchResult)));
    }

    public void selectProduction(MouseEvent mouseEvent) throws IOException {
        tfTitle.setText(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getTitle());
        tfGenre.setText(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getGenre());
        tfEpisodeNumber.setText(String.valueOf(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getEpisodeNumber()));
        tfProductionYear.setText(String.valueOf(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getProductionYear()));
        tfProductionCountry.setText(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getProductionCountry());
        tfProducedBy.setText(tvProductions
                .getSelectionModel()
                .getSelectedItem()
                .getProducedBy());

        if (mouseEvent.getClickCount() == 2) {
            App.getCreditManager().getCreditList().clear();
            App.getCreditManager().setCreditList(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
            App.getCreditManager().setCreditProductionID(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
            App.setRoot("credit");
        }
    }
}