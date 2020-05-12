package presentation;

import domain.Production;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    public GridPane gpBackground;

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
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 0);
            return;
        }

        try {
            App.getProductionManager().createProduction(tfTitle.getText(), tfGenre.getText(),
                    Integer.parseInt(tfEpisodeNumber.getText()),
                    Integer.parseInt(tfProductionYear.getText()),
                    tfProductionCountry.getText(),
                    tfProducedBy.getText()
            );

            tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 1);

            tfTitle.clear();
            tfGenre.clear();
            tfEpisodeNumber.clear();
            tfProductionCountry.clear();
            tfProductionYear.clear();
        } catch (NumberFormatException ex) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 0);
        }
    }

    public void updateProduction(ActionEvent actionEvent) {
        try {
            App.getProductionManager().updateProduction(tvProductions.getSelectionModel().getSelectedItem(),
                    tfTitle.getText(), tfGenre.getText(), tfEpisodeNumber.getText(), tfProductionYear.getText(),
                    tfProductionCountry.getText(), tfProducedBy.getText());
            tvProductions.refresh();
        }catch (NullPointerException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 6);
            return;
        }
        notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 2);
    }

    public void deleteProduction(ActionEvent actionEvent) {
        try {
            if (!App.getProductionManager().deleteProduction(tvProductions.getSelectionModel().getSelectedItem())){
                notificationAnimationSetter(spNotificationBox, spNotificationText,"Produktion", 7);
            }else {
                tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
                notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 3);

                tfTitle.clear();
                tfGenre.clear();
                tfEpisodeNumber.clear();
                tfProductionCountry.clear();
                tfProductionYear.clear();
                btnCreate.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        }catch (NullPointerException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 5);
        }
    }

    public void searchFunctionality(ActionEvent actionEvent) {
        List<Production> searchResult = App.getProductionManager().readProduction(tfSearch.getText());
        tvProductions.setItems((FXCollections.observableArrayList(searchResult)));
    }

    public void selectProduction(MouseEvent mouseEvent) throws IOException {
        try {
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

            btnCreate.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }catch (NullPointerException e){ }

        if (mouseEvent.getClickCount() == 2) {
            try {
                App.getCreditManager().getCreditList().clear();
                App.getCreditManager().setCreditList(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
                App.getCreditManager().setCreditProductionID(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
                App.setRoot("credit");
            }catch (NullPointerException e){ }
        }
    }

    public void saveFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter firstExtensionFilter = new FileChooser.ExtensionFilter("CSV files (.csv)", ".csv");
        FileChooser.ExtensionFilter secondExtensionFilter = new FileChooser.ExtensionFilter("XML files (.xml)", ".xml");
        fileChooser.getExtensionFilters().add(firstExtensionFilter);
        fileChooser.getExtensionFilters().add(secondExtensionFilter);
        fileChooser.setInitialDirectory(new File("."));
        App.getProductionManager().saveProduction(fileChooser.showSaveDialog(gpBackground.getScene().getWindow()),
                tvProductions.getSelectionModel().getSelectedItem());
        notificationAnimationSetter(spNotificationBox, spNotificationText, "Produktion", 8);
    }
}