package persistence;

import domain.Systemadministrator;

import java.sql.*;
import java.util.ArrayList;

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

    public DatabaseManager() {
        initializePostgresqlDatabase();
    }

    public static DatabaseManager getInstance() {
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

    public void insertAdmin(Systemadministrator sysadmin) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO systemadministrators "
                    + "(username, password, email, first_name, last_name, access_level, admin_id)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, sysadmin.getUsername());
            insertStatement.setString(2, sysadmin.getPassword());
            insertStatement.setString(3, sysadmin.getEmail());
            insertStatement.setString(4, sysadmin.getFirstName());
            insertStatement.setString(5, sysadmin.getLastName());
            insertStatement.setInt(6, sysadmin.getAccessLevel());
            insertStatement.setString(7, sysadmin.getAdminId());
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAdmin(String adminID) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM systemadministrators WHERE admin_id = ?");
            deleteStatement.setString(1, adminID);
            deleteStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateAdmin(String username, String password, String email, String firstName, String lastName, int accessLevel, String adminId) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE systemadministrators"
                    + " SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, access_level = ? "
                    + "WHERE admin_id = ?");
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, email);
            updateStatement.setString(4, firstName);
            updateStatement.setString(5, lastName);
            updateStatement.setInt(6, accessLevel);
            updateStatement.setString(7, adminId);
            updateStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Systemadministrator readAdmin(String adminID) {

        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM systemadministrators WHERE admin_id = ?");
            queryStatement.setString(1, adminID);
            ResultSet queryResultSet = queryStatement.executeQuery();
            return new Systemadministrator(
                    queryResultSet.getString(1),
                    queryResultSet.getString(2),
                    queryResultSet.getString(3),
                    queryResultSet.getString(4),
                    queryResultSet.getString(5),
                    queryResultSet.getInt(6),
                    queryResultSet.getString(7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Systemadministrator> getAdminList(){
        ArrayList<Systemadministrator> admins = new ArrayList<>();
        try {
            PreparedStatement getAllAdminStatement = connection.prepareStatement("SELECT * FROM systemadministrators");
            ResultSet result = getAllAdminStatement.executeQuery();
            while(result.next()){
                admins.add(new Systemadministrator(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return admins;
    }
}
