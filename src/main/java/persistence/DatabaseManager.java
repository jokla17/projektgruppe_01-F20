package persistence;

import domain.*;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Ajate_db";
    private String username = "postgres";
    private String password = "student123";
    private Connection connection = null;

    public DatabaseManager() {
        initializePostgresqlDatabase();
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
            insertStatement.setInt(7, sysadmin.getAdminId());
            insertStatement.execute();
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
                    queryResultSet.getInt(1),
                    queryResultSet.getString(2),
                    queryResultSet.getString(3),
                    queryResultSet.getString(4),
                    queryResultSet.getString(5),
                    queryResultSet.getString(6),
                    queryResultSet.getInt(7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void updateAdmin(String username, String password, String email, String firstName, String lastName, int accessLevel, int adminId) {
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
            updateStatement.setInt(7, adminId);
            updateStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAdmin(int adminID) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM systemadministrators WHERE admin_id = ?");
            deleteStatement.setInt(1, adminID);
            deleteStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Systemadministrator> getAdminList() {
        ArrayList<Systemadministrator> admins = new ArrayList<>();
        try {
            PreparedStatement getAllAdminStatement = connection.prepareStatement("SELECT * FROM systemadministrators");
            ResultSet result = getAllAdminStatement.executeQuery();
            while (result.next()) {
                admins.add(new Systemadministrator(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getInt(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return admins;
    }

    public void productionResultSet(List<Production> productionList) {
        List<Production> tempProductionList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM productions");
            ResultSet sqlReturnValues = ps.executeQuery();
            while (sqlReturnValues.next()) {
                int productionId = sqlReturnValues.getInt(1);
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

    public void insertProduction(Production production) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO productions " +
                    "(title, genre, episode_number, production_year, production_country, produced_by) VALUES (?,?,?,?,?,?)");
            ps.setString(1, production.getTitle());
            ps.setString(2, production.getGenre());
            ps.setInt(3, production.getEpisodeNumber());
            ps.setInt(4, production.getProductionYear());
            ps.setString(5, production.getProductionCountry());
            ps.setString(6, production.getProducedBy());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProduction(Production production) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE productions SET title = ?, genre = ?, episode_number = ?, production_year = ?, production_country = ?, produced_by = ? WHERE id = ?");
            ps.setString(1, production.getTitle());
            ps.setString(2, production.getGenre());
            ps.setInt(3, production.getEpisodeNumber());
            ps.setInt(4, production.getProductionYear());
            ps.setString(5, production.getProductionCountry());
            ps.setString(6, production.getProducedBy());
            ps.setInt(7, production.getProductionId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProduction(Production production) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM productions WHERE id = ?");
            ps.setInt(1, production.getProductionId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Producer> getProducerList() {
        ArrayList<Producer> producers = new ArrayList<>();
        try {
            PreparedStatement getAllProducerStatement = connection.prepareStatement("SELECT * FROM producers");
            ResultSet result = getAllProducerStatement.executeQuery();
            while (result.next()) {
                producers.add(new Producer(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getString(7),
                        result.getString(8)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return producers;
    }

    public void insertProducer(String username, String password, String email, String firstName, String lastName,
                               int accessLevel, String producerId, String employedBy) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO producers "
                    + "(username, password, email, first_name, last_name, access_level, producer_id, employed_by)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, email);
            insertStatement.setString(4, firstName);
            insertStatement.setString(5, lastName);
            insertStatement.setInt(6, accessLevel);
            insertStatement.setString(7, producerId);
            insertStatement.setString(8, employedBy);
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProducer(String username, String password, String email, String firstName, String lastName,
                               int accessLevel, String producerID, String employedBy) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE producers "
                    + "SET username = ? , password = ?, email = ?, first_name = ?, last_name = ?, access_level = ?, employed_by = ?"
                    + "WHERE producer_id = ?");
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, email);
            updateStatement.setString(4, firstName);
            updateStatement.setString(5, lastName);
            updateStatement.setInt(6, accessLevel);
            updateStatement.setString(7, employedBy);
            updateStatement.setString(8, producerID);
            updateStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteProducer(User user) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM producers WHERE username = ? ");
            deleteStatement.setString(1, user.getUsername());
            deleteStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertCredit(Credit credit, int productionId) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("CALL insert_credit(?, ?, ?, ?)");
            insertStatement.setString(1, credit.getFirstName());
            insertStatement.setString(2, credit.getLastName());
            insertStatement.setString(3, credit.getRole());
            insertStatement.setInt(4, productionId);
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCredit(Credit credit) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE credits "
                    + "SET first_name = ? , last_name = ?, role = ?"
                    + "WHERE id = ?");
            updateStatement.setString(1, credit.getFirstName());
            updateStatement.setString(2, credit.getLastName());
            updateStatement.setString(3, credit.getRole());
            updateStatement.setInt(4, credit.getId());
            updateStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCredit(Credit credit, int productionId) {
        try {
            PreparedStatement ps = connection.prepareStatement("CALL delete_credit(?,?)");
            ps.setInt(1, credit.getId());
            ps.setInt(2, productionId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void creditResultSet(List<Credit> creditList) {
        List<Credit> tempCreditList = new ArrayList<>();
        try {
            PreparedStatement cs = connection.prepareStatement("" +
                    "SELECT * FROM productions INNER JOIN production_credits ON production_credits.production_id = productions.id INNER JOIN credits ON production_credits.credit_id = credits.id");
            ResultSet sqlReturnValues = cs.executeQuery();
            while (sqlReturnValues.next()) {
                int creditId = sqlReturnValues.getInt("credit_id");
                String firstName = sqlReturnValues.getString("first_name");
                String lastName = sqlReturnValues.getString("last_name");
                String role = sqlReturnValues.getString("role");
                tempCreditList.add(new Credit(creditId, role, firstName, lastName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        creditList.addAll(tempCreditList);
    }

    public void creditResultSet(List<Credit> creditList, int productionId) {
        List<Credit> tempCreditList = new ArrayList<>();
        try {
            PreparedStatement cs = connection.prepareStatement("" +
                    "SELECT * FROM productions INNER JOIN production_credits ON production_credits.production_id = productions.id " +
                    "INNER JOIN credits ON production_credits.credit_id = credits.id WHERE productions.id = ?");
            cs.setInt(1, productionId);
            ResultSet sqlReturnValues = cs.executeQuery();
            while (sqlReturnValues.next()) {
                int creditId = sqlReturnValues.getInt("credit_id");
                String firstName = sqlReturnValues.getString("first_name");
                String lastName = sqlReturnValues.getString("last_name");
                String role = sqlReturnValues.getString("role");
                tempCreditList.add(new Credit(creditId, role, firstName, lastName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        creditList.addAll(tempCreditList);
    }
}
