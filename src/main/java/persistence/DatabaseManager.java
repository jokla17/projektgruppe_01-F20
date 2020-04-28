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
    private String password = "";
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

    public void insert(){
        //not implemented yet
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
