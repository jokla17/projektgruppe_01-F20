package persistence;

import domain.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private File file;

    public FileManager(File file) {
        this.file = file;
    }

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
        List<Credit> tempCredit = new ArrayList<>();
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
                        tempCredit.add(credit);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        creditList.addAll(tempCredit);
    }

    // Write to file
    public void writeToFile(List<Object> list) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file, false));
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
    public void appendToFile(Object object) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file, true));
            writer.append(object.toString() + "\n");

        } catch (IOException e) {
            System.out.println("IOException caught: ");
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    public void replaceLineInFile(String oldCredit, String newCredit) {
        PrintWriter writer = null;
        File file = new File("credits.txt");
        try {
            Scanner reader = new Scanner(file);

            StringBuffer buffer = new StringBuffer();
            while (reader.hasNext()) {
                buffer.append(reader.nextLine() + "\n");
            }
            reader.close();

            String fileContents = buffer.toString();
            String oldLine = oldCredit;
            String newLine = newCredit;

            Scanner reader2 = new Scanner(fileContents);

            while (reader2.hasNext()) {
                System.out.println(reader2.nextLine());
            }

            fileContents = fileContents.replaceAll(oldCredit, newLine);

            writer = new PrintWriter(new FileWriter(file));
            writer.append(fileContents);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
