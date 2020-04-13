package domain;

import java.util.ArrayList;
import java.util.List;

public class UserManagementSystem{
    private List<User> userList;


    public UserManagementSystem() {
        userList = new ArrayList<>();
    }

    public void createUser(String username, String password, String email, String firstName,
                           String lastName, int accesslevel, String employedBy){
        User user = null;
        switch (accesslevel){
            case 1:
                user = new Producer(username, password, email, firstName, lastName, accesslevel, generateUserId(accesslevel), employedBy);
                break;
            case 2:
                user = new Systemadministrator(username, password, email, firstName, lastName, accesslevel, generateUserId(accesslevel));
                break;

        }
        userList.add(user);

    }

    public void readUser(){
        for (int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i));
        }
    }

    public void updateUser(User user, String username, String password, String email, String firstName,
                           String lastName, int accessLevel){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccessLevel(accessLevel);

    }

    public void deleteUser(User user){
        userList.remove(user);

    }


    public String generateUserId(int accessLevel){
        int producerIndex = 1;
        int admindIndex = 1;
        for (int i = 0; i < userList.size(); i++){
            if (accessLevel == 1){
                producerIndex++;
                return "P" + producerIndex;
            }else{
                admindIndex++;
                return "S" + admindIndex;
            }

        }
        return null;

    }

    public static void main(String[] args) {
        UserManagementSystem userManagementSystem = new UserManagementSystem();
            userManagementSystem.createUser("BigdickWT69", "trussekongen123", "WT@hotmail.com",
                    "Wahid", "Winberg", 2, "TV2");

        for (User u: userManagementSystem.getUserList()) {

            if (u.getFirstName().equals("Wahid")){
                userManagementSystem.deleteUser(u);
                break;
            }
        }
        userManagementSystem.readUser();



    }

    public List<User> getUserList() {
        return userList;
    }
}
