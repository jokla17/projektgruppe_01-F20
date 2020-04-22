package persistence;

import domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public FileManager() {}

    // Read productions
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

    // Read users
    public void readUsers(List<User> adminList, List<User> producerList) {
        Scanner scanner = null;
        List<User> tempAdmin = new ArrayList<>();
        List<User> tempProducer = new ArrayList<>();
        try {
            scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(";");
                User user = null;
                if (Integer.parseInt(splitLine[5]) == 2) {
                    user = new Systemadministrator(splitLine[0], splitLine[1],
                            splitLine[2], splitLine[3], splitLine[4], Integer.parseInt(splitLine[5]), splitLine[6]);
                    tempAdmin.add(user);
                } else {
                    user = new Producer(splitLine[0], splitLine[1],
                            splitLine[2], splitLine[3], splitLine[4], Integer.parseInt(splitLine[5]), splitLine[6], splitLine[7]);
                    tempProducer.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        adminList.addAll(tempAdmin);
        producerList.addAll(tempProducer);
    }

    // Read credits
    public void readCredits(String productionId, List<Credit> creditList) {
        Scanner scanner = null;
        List<Credit> tempCredits = new ArrayList<>();
        try {
            scanner = new Scanner(new File("credits.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split("\\[");
                String prodId = splitLine[0].replace(";", "");
                String creditsAsString = splitLine[1].replace("]", "");
                String[] creditsToArray = creditsAsString.split(",");

                if (prodId.contains(productionId)) {
                    for (String s : creditsToArray) {
                        String[] splitCredit = s.split(";");
                        Credit credit = new Credit(splitCredit[0].trim(), splitCredit[1].trim(), splitCredit[2].trim());
                        tempCredits.add(credit);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        creditList.addAll(tempCredits);
    }
    public void readCredits(List<Credit> creditList) {
        Scanner scanner = null;
        List<Credit> tempCredits = new ArrayList<>();
        try {
            scanner = new Scanner(new File("credits.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split("\\[");
                String creditsAsString = splitLine[1].replace("]", "");
                String[] creditsToArray = creditsAsString.split(",");


                for (String s : creditsToArray) {
                        String[] splitCredit = s.split(";");
                        Credit credit = new Credit(splitCredit[0].trim(), splitCredit[1].trim(), splitCredit[2].trim());
                        tempCredits.add(credit);
                    }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        creditList.addAll(tempCredits);
    }

    // Write to file
    public void writeToFile(String path, List<Object> list) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(new File(path), false));
            for (int i = 0; i < list.size(); i++) {
                writer.append(list.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    // Append to file
    public void appendToFile(String path, Object object) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(path, true));
            writer.append(object.toString() + "\n");

        } catch (IOException e) {
            System.out.println("IOException caught: ");
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    // Replace a specific line in a file by using a buffer in order to contain the contents of a file
    public void replaceLineInFile(String oldCredits, String newCredits) {
        Scanner reader = null;
        File file = new File("credits.txt");
        try {
            reader = new Scanner(file);

            StringBuffer buffer = new StringBuffer();
            while (reader.hasNext()) {
                buffer.append(reader.nextLine() + "\n");
            }

            String fileContents = buffer.toString();

            fileContents = fileContents.replaceAll(oldCredits, newCredits);

            PrintWriter writer = new PrintWriter(new FileWriter(file));
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
