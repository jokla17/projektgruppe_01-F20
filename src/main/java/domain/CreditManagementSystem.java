package domain;

import java.util.ArrayList;
import java.util.List;

    //CreditManagementSystem - Create, read, update and delete credits within the system

public class CreditManagementSystem {
    private List<Credit> creditList;

    public CreditManagementSystem() {creditList = new ArrayList<>();}

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList) {
        this.creditList = creditList;
    }


    public void createCredit(String[] creditArgs){
        creditList.add(new Credit(
                generateCreditId(),
                creditArgs[0],
                creditArgs[1],
    }

    public void readCredit(List<Credit> creditList, String searchText){
        List<Credit> tempCreditList = new ArrayList<>();
        for (Credit c: creditList) {
            if (c.getCreditId().equalsIgnoreCase(searchText)) {
                tempCreditList.add(c)
            }
        }
    }

    public void updateCredit(Credit credit, String[] creditArgs){
        credit.setCreditRole(creditArgs[0]);
        credit.setCreditName(creditArgs[1]);
    }


    public void deleteCredit(Credit credit) {creditList.remove(credit); }


    public void showCredits(){
        //not implemented yet
    }

    public String generateCreditId(){
        int index = 1;
        for (int i = 0; i < creditList.size(); i++) {
            index++;
        }
        return "c" + index;
    }

}


