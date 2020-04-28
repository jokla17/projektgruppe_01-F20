package persistence;

import java.sql.*;

public class DatabaseManager {
    /**
     * here should be a Connection-type attribute to connect with the database
     */

    private static DatabaseManager instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Ajate_db";
    private String username = "postgres";
    private String password = "Andreas12";
    private Connection connection = null;

    public DatabaseManager(){
        initializePostgresqlDatabase();
    }

    public static DatabaseManager getInstance(){
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    public void insertAdmin(String username, String password, String email, String firstName, String lastName, int accessLevel, String adminID){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO systemadministrators "
                    + "(username, password, email, first_name, last_name, access_level, admin_id)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, email);
            insertStatement.setString(4, firstName);
            insertStatement.setString(5, lastName);
            insertStatement.setInt(6, accessLevel);
            insertStatement.setString(7, adminID);
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(){
        //not implemented yet
    }

    public void update(){
        //not implemented yet
    }

    public void resultSet(){ //should probably return a result set
        //not implemented yet
    }
}
