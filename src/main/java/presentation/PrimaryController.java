package presentation;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Production;
import domain.ProductionManagementSystem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable {

    public TextField tfTitle;
    public TextField tfGenre;
    public TextField tfEpisodeNumber;
    public TextField tfProductionYear;
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

    ProductionManagementSystem pms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("ProductionId"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("EpisodeNumber"));

        pms = new ProductionManagementSystem();
    }

    public void createProduction(ActionEvent actionEvent) {
        pms.createProduction(new String[]{tfTitle.getText(), tfGenre.getText(),
                tfEpisodeNumber.getText(), tfProductionYear.getText(), tfProductionCountry.getText(), tfProducedBy.getText()});
        tvProductions.setItems(FXCollections.observableArrayList(pms.getProductionList()));
    }

    public void updateProduction(ActionEvent actionEvent) {
        pms.updateProduction(tvProductions.getSelectionModel().getSelectedItem(),
                new String[]{tfTitle.getText(), tfGenre.getText(),
                        tfEpisodeNumber.getText(), tfProductionYear.getText(), tfProductionCountry.getText(), tfProducedBy.getText()});
        tvProductions.refresh();
    }

    public void deleteProduction(ActionEvent actionEvent) {
        pms.deleteProduction(tvProductions.getSelectionModel().getSelectedItem());
        tvProductions.setItems(FXCollections.observableArrayList(pms.getProductionList()));
    }

    public void searchProduction(ActionEvent actionEvent) {
        tvProductions.setItems(
                FXCollections.observableArrayList(pms.readProduction(pms.getProductionList(), tfSearch.getText())));
    }

    public void selectProduction(MouseEvent mouseEvent) {
        tfTitle.setText(tvProductions.getSelectionModel().getSelectedItem().getTitle());
        tfGenre.setText(tvProductions.getSelectionModel().getSelectedItem().getGenre());
        tfEpisodeNumber.setText(String.valueOf(tvProductions.getSelectionModel().getSelectedItem().getEpisodeNumber()));
        tfProductionYear.setText(String.valueOf(tvProductions.getSelectionModel().getSelectedItem().getProductionYear()));
        tfProductionCountry.setText(tvProductions.getSelectionModel().getSelectedItem().getProductionCountry());
        tfProducedBy.setText(tvProductions.getSelectionModel().getSelectedItem().getProducedBy());
    }
}