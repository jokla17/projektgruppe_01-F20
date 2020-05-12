package presentation;

import domain.Credit;
import domain.Production;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    public RadioButton rbProductions;
    public ToggleGroup searchSelections;
    public RadioButton rbCredits;
    public ListView<Credit> lvCreditResults;
    public ListView<Production> lvProductionsResults;
    public TextField tfStartSearchfield;
    public Button btnStartSearch;
    public HBox vbStartHeader;
    public Button btnLogin;
    public StackPane spSearchResultsPopUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeProductionListViewCells(lvProductionsResults);
        changeCreditsListViewCells(lvCreditResults);
    }

    public void forwardToLogin(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
        App.getCreditManager().getCreditList().clear();
    }

    public void search(ActionEvent actionEvent) {
        spSearchResultsPopUp.setVisible(true);

        if (rbProductions.isSelected()) {
            lvCreditResults.setVisible(false);
            lvProductionsResults.setVisible(true);
            lvProductionsResults.setItems(FXCollections.observableArrayList(
                    App.getProductionManager().readProduction(tfStartSearchfield.getText())));
        } else if (rbCredits.isSelected()) {
            lvProductionsResults.setVisible(false);
            lvCreditResults.setVisible(true);
            App.getCreditManager().getCreditList().clear();
            App.getCreditManager().setCreditList();
            lvCreditResults.setItems(FXCollections.observableArrayList(
                    App.getCreditManager().readCredit(tfStartSearchfield.getText())));
        }
    }

    public void closeSearchResults(ActionEvent actionEvent) {
        lvCreditResults.getItems().clear();
        lvProductionsResults.getItems().clear();
        spSearchResultsPopUp.setVisible(false);
    }

    public void changeCreditsListViewCells(ListView lv) {
        lv.setCellFactory(new Callback<ListView<Credit>, ListCell<Credit>>() {
            public ListCell<Credit> call(ListView<Credit> list) {
                ListCell<Credit> cell = new ListCell<Credit>() {
                    public void updateItem(Credit item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty == true) {
                            setText(null);
                            return;
                        }
                        setText("Fornavn: " + item.getFirstName()
                                + "\nEfternavn: "
                                + item.getLastName()
                                + "\nRolle: "
                                + item.getRole());
                    }
                };
                return cell;
            }
        });
    }

    public void changeProductionListViewCells(ListView lv) {
        lv.setCellFactory(new Callback<ListView<Production>, ListCell<Production>>() {
            public ListCell<Production> call(ListView<Production> list) {
                ListCell<Production> cell = new ListCell<Production>() {
                    public void updateItem(Production item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty == true) {
                            setText(null);
                            return;
                        }
                        setText("Produktions id: " + item.getProductionId()
                                + "\nTitel: " + item.getTitle()
                                + "\nGenre: " + item.getGenre()
                                + "\nEpisode nummer: " + item.getEpisodeNumber()
                                + "\nProduktions år: " + item.getProductionYear()
                                + "\nProduktions land: " + item.getProductionCountry()
                                + "\nProduceret af: " + item.getProducedBy());
                    }
                };
                return cell;
            }
        });
    }
}
