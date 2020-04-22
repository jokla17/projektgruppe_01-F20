package presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import domain.Production;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ProductionController extends MainController implements Initializable {
    public TextField tfTitle;
    public TextField tfGenre;
    public TextField tfEpisodeNumber;
    public TextField tfProductionCountry;
    public TextField tfProducedBy;
    public Button btnCreate;
    public TableView<Production> tvProductions;
    public TableColumn<Production, String> tcID;
    public TableColumn<Production, String> tcTitle;
    public TableColumn<Production, String> tcGenre;
    public TableColumn<Production, Integer> tcEpisodeNumber;
    public Button btnUpdate;
    public Button btnDelete;
    public TextField tfSearch;
    public Button btnSearch;
    public TextField tfProductionYear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        tcID.setCellValueFactory(new PropertyValueFactory<>("ProductionId"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("EpisodeNumber"));
        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
    }

    // Create production handler
    public void createProduction(ActionEvent actionEvent) {
        App.getProductionManager().createProduction(new String[]{tfTitle.getText(), tfGenre.getText(),
                tfEpisodeNumber.getText(), tfProductionYear.getText(), tfProductionCountry.getText(), tfProducedBy.getText()});
        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
    }

    // Update production handler
    public void updateProduction(ActionEvent actionEvent) {
        App.getProductionManager().updateProduction(tvProductions.getSelectionModel().getSelectedItem(),
                new String[]{tfTitle.getText(), tfGenre.getText(),
                        tfEpisodeNumber.getText(), tfProductionYear.getText(), tfProductionCountry.getText(), tfProducedBy.getText()});
        tvProductions.refresh();
    }

    // Delete production handler
    public void deleteProduction(ActionEvent actionEvent) {
        App.getProductionManager().deleteProduction(tvProductions.getSelectionModel().getSelectedItem());
        tvProductions.setItems(FXCollections.observableArrayList(App.getProductionManager().getProductionList()));
    }

    // Search handler
    public void searchFunctionality(ActionEvent actionEvent) {
        ArrayList<Production> searchResult = new ArrayList<>();
        String searchText = tfSearch.getText().toLowerCase();
        for (int i = 0; i < App.getProductionManager().getProductionList().size(); i++){
            if (App.getProductionManager().getProductionList().get(i).toString().toLowerCase().contains(searchText)){
                searchResult.add(App.getProductionManager().getProductionList().get(i));
            }
        }
        tvProductions.setItems((FXCollections.observableArrayList(searchResult)));
    }

    // Select production handler
    public void selectProduction(MouseEvent mouseEvent) throws IOException {
        tfTitle.setText(tvProductions.getSelectionModel().getSelectedItem().getTitle());
        tfGenre.setText(tvProductions.getSelectionModel().getSelectedItem().getGenre());
        tfEpisodeNumber.setText(String.valueOf(tvProductions.getSelectionModel().getSelectedItem().getEpisodeNumber()));
        tfProductionYear.setText(String.valueOf(tvProductions.getSelectionModel().getSelectedItem().getProductionYear()));
        tfProductionCountry.setText(tvProductions.getSelectionModel().getSelectedItem().getProductionCountry());
        tfProducedBy.setText(tvProductions.getSelectionModel().getSelectedItem().getProducedBy());

        if (mouseEvent.getClickCount() == 2) {
            App.getCreditManager().getCreditList().clear();
            App.getCreditManager().setCreditList(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
            App.getCreditManager().setCreditProductionID(tvProductions.getSelectionModel().getSelectedItem().getProductionId());
            App.setRoot("credit");
        }
    }
}