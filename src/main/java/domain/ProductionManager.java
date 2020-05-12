package domain;

import presentation.App;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductionManager {
    private List<Production> productionList;

    public ProductionManager() {
        productionList = new ArrayList<>();
        App.getDatabaseManager().productionResultSet(productionList);
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public void setProductionList() {
        if (!App.getAuthentificationManager().checkPermission()){
            App.getDatabaseManager().productionResultSet(productionList,
                    ((Producer)App.getAuthentificationManager().getCurrentUser()).getProducerId());
            return;
        }
        App.getDatabaseManager().productionResultSet(productionList);
    }

    public void createProduction(String title, String genre, int episodeNumber, int productionYear, String productionCountry, String producedBy) {
        Production production;
        if (!App.getAuthentificationManager().checkPermission()) {
            production = new Production(title, genre, episodeNumber, productionYear, productionCountry, producedBy,
                    ((Producer)App.getAuthentificationManager().getCurrentUser()).getProducerId());
        } else {
            production = new Production(title, genre,
                    episodeNumber, productionYear, productionCountry, producedBy);
        }
        App.getDatabaseManager().insertProduction(production);
        productionList.clear();
        setProductionList();
    }

    public List<Production> readProduction(String searchText) {
        List<Production> tempProductionList = new ArrayList<>();
        for (int i = 0; i < productionList.size(); i++) {
            if (productionList.get(i).toString().toLowerCase().contains(searchText.toLowerCase())) {
                tempProductionList.add(productionList.get(i));
            }
        }
        return tempProductionList;
    }

    public void updateProduction(Production production, String ... productionArgs) {
        production.setTitle(productionArgs[0]);
        production.setGenre(productionArgs[1]);
        production.setEpisodeNumber(Integer.parseInt(productionArgs[2]));
        production.setProductionYear(Integer.parseInt(productionArgs[3]));
        production.setProductionCountry(productionArgs[4]);
        production.setProducedBy(productionArgs[5]);
        App.getDatabaseManager().updateProduction(production);
    }

    public boolean deleteProduction(Production production) {
        boolean canDelete = App.getDatabaseManager().deleteProduction(production);
        productionList.clear();
        setProductionList();
        return canDelete;
    }

    public void saveProduction(File file, Production production){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.append("Produktion \n");
            printWriter.append(production.getTitle());
            printWriter.append(";");
            printWriter.append(production.getGenre());
            printWriter.append(";");
            printWriter.append(String.valueOf(production.getEpisodeNumber()));
            printWriter.append(";");
            printWriter.append(String.valueOf(production.getProductionYear()));
            printWriter.append(";");
            printWriter.append(production.getProductionCountry());
            printWriter.append(";");
            printWriter.append(production.getProducedBy() + "\n");

            App.getCreditManager().getCreditList().clear();
            App.getCreditManager().setCreditList(production.getProductionId());
            printWriter.append("Krediteringer \n");
            for (Credit c : App.getCreditManager().getCreditList()) {
                printWriter.append(c.getRole());
                printWriter.append(";");
                printWriter.append(c.getFirstName());
                printWriter.append(";");
                printWriter.append(c.getLastName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
