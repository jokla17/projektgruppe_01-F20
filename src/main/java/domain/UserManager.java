package domain;

import persistence.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    public FileManager getFm() {
        return fm;
    }

    private List<User> adminList;
    private List<User> producerList;
    private FileManager fm;

    public List<User> getProducerList() {
        return producerList;
    }

    public UserManager() {
        adminList = new ArrayList<>();
        producerList = new ArrayList<>();
        fm = new FileManager(new File("users.txt"));
        fm.readUsers(adminList, producerList);
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
        fm.appendToFile(user);
    }


    public void updateUser(User user, String username, String password, String email, String firstName, String lastName, int accessLevel, String... other) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccessLevel(accessLevel);

        if (accessLevel == 1) {
            Producer producer = (Producer) user;
            producer.setEmployedBy(other[0]);
        }
    }

     public void deleteAdmin(User user) {
        adminList.remove(user);
        List<Object> tempAdminList = new ArrayList<>(adminList);
        fm.writeToFile(tempAdminList);
    }

    public void deleteProducer(User user) {
        producerList.remove(user);
        List<Object> tempProducerList = new ArrayList<>(producerList);
        fm.writeToFile(tempProducerList);
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

        }

