package persistence;

import domain.Producer;
import domain.Production;
import domain.Systemadministrator;
import domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private File file;

    public FileManager(File file){
        this.file = file;
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

    public void readUsers(List<User> userList) {
        Scanner scanner = null;
        List<User> tempUsers = new ArrayList<>();
        try {
            scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(";");

                User user = null;
                int accessLevel = Integer.parseInt(splitLine[5]);
                if (accessLevel == 2) {
                    user = new Systemadministrator(splitLine[0], splitLine[1],
                            splitLine[2], splitLine[3], splitLine[4], Integer.parseInt(splitLine[5]), splitLine[6]);
                } else {
                    user = new Producer(splitLine[0], splitLine[1],
                            splitLine[2], splitLine[3], splitLine[4], Integer.parseInt(splitLine[5]), splitLine[6], splitLine[7]);
                }
                tempUsers.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        userList.addAll(tempUsers);
    }


    public void writeToFile(List<Object> list){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file, false));
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
            writer = new PrintWriter(new FileWriter(file, true));
            writer.append(object.toString() + "\n");

        } catch (IOException e){
            System.out.println("IOException caught: ");
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
