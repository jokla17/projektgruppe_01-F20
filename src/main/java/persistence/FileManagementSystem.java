package persistence;


import java.io.*;
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

    public void writeToFile(String text){
        //hvad skal vi bruge den her til når vi har appendToFile?
        //vi kunne evt lave den som en searchAndReplace eller editFile på et senere tidspunkt
    }

    public void appendToFile(String text){
        try{
            FileWriter input = new FileWriter(database, true);
            input.write("\n");
            input.write(text);
            input.close();
        } catch (IOException e){
            System.out.println("IOException caught: ");
            e.printStackTrace();
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
