package persistence;

import domain.Production;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Ajate_db";
    private String username = "postgres";
    private String password = "student123";
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

    public void insertProduction(Production production) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO productions " +
                    "(id, title, genre, episode_number, production_year, production_country, produced_by) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, production.getProductionId());
            ps.setString(2, production.getTitle());
            ps.setString(3, production.getGenre());
            ps.setInt(4, production.getEpisodeNumber());
            ps.setInt(5, production.getProductionYear());
            ps.setString(6, production.getProductionCountry());
            ps.setString(7, production.getProducedBy());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProduction(Production production){
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM productions WHERE id = ?");
            ps.setString(1, production.getProductionId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProduction(Production production){
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE productions SET title = ?, genre = ?, episode_number = ?, production_year = ?, production_country = ?, produced_by = ? WHERE username = ?");
            ps.setString(1, production.getTitle());
            ps.setString(2, production.getGenre());
            ps.setInt(3, production.getEpisodeNumber());
            ps.setInt(4, production.getProductionYear());
            ps.setString(5, production.getProductionCountry());
            ps.setString(6, production.getProducedBy());
            ps.setString(7, production.getProductionId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void productionResultSet(List<Production> productionList){
        List<Production> tempProductionList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM productions");
            ResultSet sqlReturnValues = ps.executeQuery();
            while (sqlReturnValues.next()) {
                String productionId = sqlReturnValues.getString(1);
                String title = sqlReturnValues.getString(2);
                String genre = sqlReturnValues.getString(3);
                int episodeNumber = sqlReturnValues.getInt(4);
                int productionYear = sqlReturnValues.getInt(5);
                String productionCountry = sqlReturnValues.getString(6);
                String producedBy = sqlReturnValues.getString(7);
                tempProductionList.add(new Production(
                        productionId, title, genre, episodeNumber, productionYear, productionCountry, producedBy));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        productionList.addAll(tempProductionList);
    }
}
