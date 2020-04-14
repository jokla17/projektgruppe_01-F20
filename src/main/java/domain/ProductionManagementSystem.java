package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductionManagementSystem - Create, read, update, and delete production(s).
 */
public class ProductionManagementSystem {
    private List<Production> productionList;

    public ProductionManagementSystem() {
        productionList = new ArrayList<>();
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

    /**
     * Add a production to the productionList.
     * @param productionArgs - String[] array parameter, with production related arguments from presentation layer.
     */
    public void createProduction(String[] productionArgs){
        productionList.add(new Production(
                generateProductionId(),
                productionArgs[0],
                productionArgs[1],
                Integer.parseInt(productionArgs[2]),
                Integer.parseInt(productionArgs[3]),
                productionArgs[4],
                productionArgs[5]));
    }

    /**
     * Reads one or multiple productions, based on a specific search result - in this case, only the production title.
     * How it works: A temporary list is initialized in the method, in order to contain the searched productions.
     * A for-each loop iterates through the original productionList, where an if-statement checks if the searchText given
     * as argument, matches any production in the original productionList. If it does, add to temporary list and return.
     * @param productionList - parameter needed to get the original productionList.
     * @param searchText - specific search parameter from user.
     * @return - returns temporary productionList, which is used in the presentation layer.
     */
    public List<Production> readProduction(List<Production> productionList, String searchText) {
        List<Production> tempProductionList = new ArrayList<>();
        for (Production p: productionList) {
            if (p.getTitle().equalsIgnoreCase(searchText)) {
                tempProductionList.add(p);
            }
        }
        return tempProductionList;
    }

    /**
     * Updates a specific selected production from the production view in the presentation layer, by
     * using setter methods from the Production class.
     * @param production - selected production as an argument, for updating.
     * @param productionArgs - production arguments needs for setter methods.
     */
    public void updateProduction(Production production, String[] productionArgs){
        production.setTitle(productionArgs[0]);
        production.setGenre(productionArgs[1]);
        production.setEpisodeNumber(Integer.parseInt(productionArgs[2]));
        production.setProductionYear(Integer.parseInt(productionArgs[3]));
        production.setProductionCountry(productionArgs[4]);
        production.setProducedBy(productionArgs[5]);
    }

    /**
     * Deletes a specific selected production from the product view in the presentation layer.
     * @param production - specified parameter to get production argument that needs removal.
     */
    public void deleteProduction(Production production){
        productionList.remove(production);
    }

    /**
     * Automatically generates an ID for a created Production class object.
     * It iterates through the production list. Index variable counts each iteration, when
     * a production is added to the productionList. - Not read file friendly yet.
     * @return Production ID (example: P1, P2, P3)
     */
    public String generateProductionId() {
        int index = 1;
        for (int i = 0; i < productionList.size(); i++) {
            index++;
        }
        return "P" + index;
    }
}
