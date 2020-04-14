package domain;

import persistence.FileManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User management system - create, read, update and delete user (not file compatible yet)
public class UserManagementSystem {
    private List<User> userList;
    private FileManagementSystem fms = new FileManagementSystem(new File("users.txt"));

    public UserManagementSystem() {
        userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    /**
     * Create either a producer or a system administrator user. A overloaded createUser() method would
     * had been necessary, because a producer needs more arguments than a system administrator. However,
     * that's why the varargs parameter (other) is implemented, so that you CAN add employedBy if you want producer user.
     * How it works: When calling createUser(), 6 arguments are needed, and you CAN add more. When doing so,
     * the parameter accessLevel, will be used in the switch-case, in order to create either a admin or producer.
     * @param username - needed for username argument.
     * @param password - needed for password argument.
     * @param email - needed for email argument.
     * @param firstName - needed for first name argument.
     * @param lastName - needed for last name argument.
     * @param accessLevel - needed to specify what type of user that's created.
     * @param other - varargs parameter, for extra arguments if needed.
     */
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
    }

    /**
     * Iterates through the list, but isn't quite needed for the GUI version.
     */
    public void readUser() {
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }
    }

    public void readUsers(List<User> userList) {
        Scanner scanner = null;
        List<User> tempUsers = new ArrayList<>();
        try {
            scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(";");
                User user = new User(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4], Integer.parseInt(splitLine[5]));
                tempUsers.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        userList.addAll(tempUsers);
    }

    /**
     * Updates a single user, by using the set mutators in the user class. Not yet compatible to update parameters, in
     * extended classes.
     */
    public void updateUser(User user, String username, String password, String email, String firstName,
                           String lastName, int accessLevel) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccessLevel(accessLevel);
        List<Object> tempUsersList = new ArrayList<>(userList);
        fms.writeToFile(tempUsersList);
    }

    /**
     * Deletes a single user in the user list.
     * @param user
     */
    public void deleteUser(User user) {
        userList.remove(user);
        List<Object> tempUsersList = new ArrayList<>(userList);
        fms.writeToFile(tempUsersList);
    }

    /**
     * Counts either producer or system administrator ids (not yet compatible with files)
     * @param accessLevel
     * @return
     */
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
