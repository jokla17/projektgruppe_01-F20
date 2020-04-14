package domain;

import persistence.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList;
    private FileManager fms;

    public UserManager() {
        userList = new ArrayList<>();
        fms = new FileManager(new File("users.txt"));
        fms.readUsers(userList);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void createUser(String username, String password, String email, String firstName,
                           String lastName, int accessLevel, String ... other) {
        User user = null;
        switch (accessLevel) {
            case 1:
                user = new Producer(username, password, email, firstName, lastName,
                        accessLevel, generateUserId(accessLevel), other[0]);
                break;
            case 2:
                user = new Systemadministrator(username, password, email, firstName, lastName,
                        accessLevel, generateUserId(accessLevel));
                break;
        }
        userList.add(user);
        fms.appendToFile(user);
    }

    public void readUser() {
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }
    }

    public void updateUser(User user, String username, String password, String email, String firstName, String lastName, int accessLevel, String ... other) {
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

    public void deleteUser(User user) {
        userList.remove(user);
        List<Object> tempUsersList = new ArrayList<>(userList);
        fms.writeToFile(tempUsersList);
    }

    public String generateUserId(int accessLevel) {
        int producerIndex = 0;
        int adminIndex = 0;

        if (accessLevel == 1) {
            producerIndex++;
            return "P" + producerIndex;
        } else {
            adminIndex++;
            return "S" + adminIndex;
        }
    }
}
