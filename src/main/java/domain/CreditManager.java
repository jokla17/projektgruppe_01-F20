package domain;

import presentation.App;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditManager {
    private List<Credit> creditList;
    private int creditProductionID;

    public CreditManager() {
        creditList = new ArrayList<>();
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(int productionId) {
        App.getDatabaseManager().creditResultSet(creditList, productionId);
    }

    public void setCreditList() {
        App.getDatabaseManager().creditResultSet(creditList);
    }

    public int getCreditProductionID() {
        return creditProductionID;
    }

    public void setCreditProductionID(int creditProductionID) {
        this.creditProductionID = creditProductionID;
    }

    public void createCredit(int productionId, String role, String firstName, String lastName) throws SQLException {
        Credit credit = new Credit(role, firstName, lastName);
        App.getDatabaseManager().insertCredit(credit, productionId);
        creditList.clear();
        setCreditList(productionId);
    }

    public List<Credit> readCredit(String searchText) {
        List<Credit> tempCreditList = new ArrayList<>();
        for (int i = 0; i < creditList.size(); i++) {
            if (creditList.get(i).toString().toLowerCase().contains(
                    searchText.toLowerCase())) {
                tempCreditList.add(creditList.get(i));
            }
        }
        return tempCreditList;
    }

    public void updateCredit(Credit credit, String role, String firstName, String lastName) {
        credit.setRole(role);
        credit.setFirstName(firstName);
        credit.setLastName(lastName);
        App.getDatabaseManager().updateCredit(credit);
        creditList.clear();
        App.getDatabaseManager().creditResultSet(creditList);
    }

    public void deleteCredit(Credit credit, int productionId) {
        App.getDatabaseManager().deleteCredit(credit, productionId);
        creditList.clear();
        App.getDatabaseManager().creditResultSet(creditList, productionId);
    }
}


