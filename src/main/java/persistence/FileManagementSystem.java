package persistence;

import domain.Production;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagementSystem {

    private File database;

    public FileManagementSystem (File database){
        this.database = database;
    }

    public String readFile(){
        StringBuilder s = new StringBuilder();
        try {
            Scanner output = new Scanner(database);
            while (output.hasNext()){
                s.append(output.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified textfile does not exist.");
            return "The specified textfile does not exist";
        }
        return s.toString();
    }

    public void readProductions(List<Production> productionList) {
        Scanner scanner = null;
        List<Production> tempProduction = new ArrayList<>();
        try {
            scanner = new Scanner(new File("productions.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(";");
                Production production = new Production(splitLine[0], splitLine[1], splitLine[2],
                        Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]), splitLine[5], splitLine[6]);
                tempProduction.add(production);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        productionList.addAll(tempProduction);
    }


    public void writeToFile(List<Object> list){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(database, false));
            for (int i = 0; i < list.size(); i++){
                writer.append(list.get(i).toString() + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    public void appendToFile(Object object){
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(new FileWriter(database, true));
            writer.append(object.toString() + "\n");

        } catch (IOException e){
            System.out.println("IOException caught: ");
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    public static void main(String[] args) {
        FileManagementSystem dbProd = new FileManagementSystem(new File("productions.txt"));
        FileManagementSystem dbUsers = new FileManagementSystem(new File("users.txt"));

        System.out.println(dbProd.readFile());
        System.out.println(dbUsers.readFile());

        dbProd.appendToFile("testtesttesttesttest");
        dbUsers.appendToFile("testtesttesttesttest");

        System.out.println(dbProd.readFile());
        System.out.println(dbUsers.readFile());

    }
}
