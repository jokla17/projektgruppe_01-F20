package domain;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import persistence.FileManager;
import presentation.ProductionController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//CreditManagementSystem - Create, read, update and delete credits within the system
public class CreditManager {
    private static List<Credit> creditList;

    FileManager fm;

    public CreditManager() {
        creditList = new ArrayList<>();
        fm = new FileManager(new File("credits.txt"));
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    /**
     * Add a single credit to the creditList.
     * @param role - Parameter for role arg.
     * @param name - Parameter for name arg.
     */
    public void createCredit(String role, String name){
        creditList.add(new Credit(generateCreditId(), role, name));
    }

    public void saveCredits(TextField tfProductionID){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter(new File("credits.txt"),true));
            pw.append(tfProductionID.getText() + ";" + creditList + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * OVERLOADED - Add multiple credits to the creditList at once.
     * How it works: A scanner reads through the TextArea text line by line, by using nextLine().
     * Then it splits the line where the symbol/regex is ( ; ), into a String[] array. The elements
     * in the array will then be selected into a new Credit object, and added to creditList.
     * @param taCreditsArea - Parameter for textarea arg.
     */
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

    /**
     * Reads one or multiple credits, based on a specific search result - in this case, only the credit name.
     * How it works: A temporary list is initialized in the method, in order to contain the searched credits.
     * A for-each loop iterates through the original creditList, where an if-statement checks if the searchText given
     * as argument, matches any credit in the original creditList. If it does, add to temporary list and return.
     * @param creditList - parameter needed to get the original creditList.
     * @param searchText - specific search parameter from user.
     * @return - returns temporary creditList, which is used in the presentation layer.
     */
    public List<Credit> searchCredit (List<Credit> creditList, String searchText){
        List<Credit> tempCreditList = new ArrayList<>();
        for (Credit c: creditList) {
            if (c.getCreditName().equalsIgnoreCase(searchText)) {
                tempCreditList.add(c);
            }
        }
        return tempCreditList;
    }

    /**
     * Updates a specific selected production from the production view in the presentation layer, by
     * using setter methods from the Production class.
     * @param credit - selected credit as an argument, for updating.
     * @param role - role parameter needed for arg, in order to mutate the credit role.
     * @param name - name parameter needed for arg, in order to mutate the name role.
     */
    public void updateCredit(Credit credit, String role, String name) {
        credit.setCreditRole(role);
        credit.setCreditName(name);
    }

    /**
     * Deletes a specific selected credit from the credits view in the presentation layer.
     * @param credit - specified parameter to get credit argument that needs removal.
     */
    public void deleteCredit(Credit credit) {
        creditList.remove(credit);

    }




    /**
     * Automatically generates an ID for a created Credit class object.
     * It iterates through the credit list. Index variable counts each iteration, when
     * a credit is added to the creditList. - Not read file friendly yet.
     * @return Credit ID (example: C1, C2, C3)
     *
     */

    public String generateCreditId(){
        int index = 1;
        for (int i = 0; i < creditList.size(); i++) {
            index++;
        }
        return "C" + index;
    }
}


