package domain;

import presentation.App;
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

    public void createProduction(String[] productionArgs){
        Production production = new Production(
                productionArgs[0],
                productionArgs[1],
                Integer.parseInt(productionArgs[2]),
                Integer.parseInt(productionArgs[3]),
                productionArgs[4],
                productionArgs[5]);
        App.getDatabaseManager().insertProduction(production);
        productionList.clear();
        App.getDatabaseManager().productionResultSet(productionList);
    }

    public List<Production> readProduction(String searchText) {
        List<Production> tempProductionList = new ArrayList<>();

        for(int i = 0; i < App.getProductionManager().getProductionList().size(); i++){
            if(App.getProductionManager().getProductionList().get(i).toString().toLowerCase().contains(
                    searchText.toLowerCase())){
                tempProductionList.add(App.getProductionManager().getProductionList().get(i));
            }
        }
        return tempProductionList;
    }

    public void updateProduction(Production production, String[] productionArgs){
        production.setTitle(productionArgs[0]);
        production.setGenre(productionArgs[1]);
        production.setEpisodeNumber(Integer.parseInt(productionArgs[2]));
        production.setProductionYear(Integer.parseInt(productionArgs[3]));
        production.setProductionCountry(productionArgs[4]);
        production.setProducedBy(productionArgs[5]);
        App.getDatabaseManager().updateProduction(production);
    }

    public void deleteProduction(Production production){
        productionList.remove(production);
        App.getDatabaseManager().deleteProduction(production);
        productionList.clear();
        App.getDatabaseManager().productionResultSet(productionList);
    }
}
