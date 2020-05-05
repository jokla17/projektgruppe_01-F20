package persistence;

import domain.Systemadministrator;

import java.sql.*;
import java.util.ArrayList;
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
            PreparedStatement ps = connection.prepareStatement("UPDATE productions SET title = ?, genre = ?, episode_number = ?, production_year = ?, production_country = ?, produced_by = ? WHERE id = ?");
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
