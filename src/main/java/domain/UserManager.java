package domain;

import presentation.App;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> adminList;
    private List<User> producerList;
    private static int accessLevel = 2;

    public UserManager() {
        adminList = new ArrayList<>();
        producerList = new ArrayList<>();
        App.getFileManager().readUsers(adminList, producerList);
    }

    public List<User> getProducerList() {
        return producerList;
    }

    public List<User> getAdminList() {
        return adminList;
    }

    public void createUser(String username, String password, String email, String firstName,
                           String lastName, int accessLevel, String... other) {
        User user = null;
        switch (accessLevel) {
            case 1:
                user = new Producer(username, password, email, firstName, lastName,
                        accessLevel, generateProducerId(), other[0]);
                producerList.add(user);
                break;
            case 2:
                user = new Systemadministrator(username, password, email, firstName, lastName,
                        accessLevel, generateAdminId());
                adminList.add(user);
                break;
        }
        App.getFileManager().appendToFile("users.txt", user);
    }

    public void updateUser(User user, String username, String password, String email, String firstName, String lastName, int accessLevel, String... other) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccessLevel(accessLevel);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);
    }

     public void deleteAdmin(User user) {
        adminList.remove(user);
        List<Object> tempAdminList = new ArrayList<>(adminList);
        App.getFileManager().writeToFile("users.txt", tempAdminList);
    }

    public void deleteProducer(User user) {
        producerList.remove(user);
        List<Object> tempProducerList = new ArrayList<>(producerList);
        App.getFileManager().writeToFile("users.txt", tempProducerList);
    }

    public String generateAdminId(){
        int index = 1;
        for (int i = 0; i < adminList.size(); i++) {
            index++;
        }
        return "S" + index;
    }

    public String generateProducerId(){
        int index = 1;
        for (int i = 0; i < producerList.size(); i++) {
            index++;
        }
        return "P" + index;
    }

    public static int getAccessLevel() {
        return accessLevel;
    }
}


