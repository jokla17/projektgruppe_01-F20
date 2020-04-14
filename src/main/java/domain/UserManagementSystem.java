package domain;

import java.util.ArrayList;
import java.util.List;

// User management system - create, read, update and delete user (not file compatible yet)
public class UserManagementSystem {
    private List<User> userList;

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
    }

    /**
     * Deletes a single user in the user list.
     * @param user
     */
    public void deleteUser(User user) {
        userList.remove(user);
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

    public static void main(String[] args) {
        UserManagementSystem userManagementSystem = new UserManagementSystem();

        System.out.println("\nCreate users (CLI): ");
        userManagementSystem.createUser("BigdickWT69", "trussekongen123", "james@hotmail.com",
                "James", "Richard", 1, "TV2");
        userManagementSystem.createUser("JohnDoe", "123doe", "doe@hotmail.com",
                "John", "Doe", 2);
        userManagementSystem.readUser();

        System.out.println("\nDelete a user (CLI): ");
        for (User u: userManagementSystem.getUserList()) {
            if (u.getFirstName().contains("James")) {
                userManagementSystem.deleteUser(u);
                break;
            }
        }
        userManagementSystem.readUser();

        System.out.println("\nUpdate a user (CLI): ");
        for (User u: userManagementSystem.getUserList()) {
            if (u.getFirstName().contains("John")) {
                userManagementSystem.updateUser(u, "N/A", "N/A", "N/A", "N/A", "N/A", 2);
            }
        }
        userManagementSystem.readUser();
    }
}
