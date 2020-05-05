package domain;

import javafx.scene.control.TextArea;
import presentation.App;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreditManager {
    private List<Credit> creditList;
    private String creditProductionID;

    public CreditManager() {
        creditList = new ArrayList<>();
        creditProductionID = null;
    }

    public String getCreditProductionID() {
        return creditProductionID;
    }

    public void setCreditProductionID(String creditProductionID) {
        this.creditProductionID = creditProductionID;
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(String productionId) {
        App.getFileManager().readCredits(productionId, creditList);
    }

    public void setAllCreditList() {
        creditList.clear();
        App.getFileManager().readCredits(creditList);
    }

    public void createCredit(String role, String name){
        creditList.add(new Credit(generateCreditId(), role, name));
    }

    public void createCredit(TextArea taCreditsArea){
        Scanner reader = null;
        try {
            reader = new Scanner(taCreditsArea.getText());
            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] splitLine = line.split(";");
                Credit credit = new Credit(generateCreditId(), splitLine[0], splitLine[1]);
                creditList.add(credit);
            }
        } finally {
            reader.close();
        }
    }

    public List<Credit> readCredit(String searchText) {
        List<Credit> tempCreditList = new ArrayList<>();

        for(int i = 0; i < creditList.size(); i++){
            if(creditList.get(i).toString().toLowerCase().contains(
                    searchText.toLowerCase())){
                tempCreditList.add(creditList.get(i));
            }
        }
        return tempCreditList;
    }

    public void updateCredit(Credit credit, String role, String name) {
        credit.setCreditRole(role);
        credit.setCreditName(name);
    }

    public void deleteCredit(Credit credit) {
        creditList.remove(credit);
    }

    public void saveCredits(String productionId){
        Scanner reader = null;
        try{
            reader = new Scanner(new File("credits.txt"));
            while (reader.hasNext()) {
                String[] split = reader.nextLine().split("\\[");
                String prodId = split[0].replace(";","");
                String credits = split[1];
                if (prodId.contains(productionId)) {
                    App.getFileManager().replaceLineInFile(prodId + ";\\[" + credits, productionId + ";" + creditList);
                    return;
                }
            }
            App.getFileManager().appendToFile("credits.txt", productionId + ";" + creditList);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public String generateCreditId(){
        int index = 1;
        for (int i = 0; i < creditList.size(); i++) {
            index++;
        }
        return "C" + index;
    }
}


