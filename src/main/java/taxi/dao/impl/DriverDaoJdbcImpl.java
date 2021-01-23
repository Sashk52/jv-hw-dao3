package taxi.dao.impl;

import taxi.dao.DriverDao;
import taxi.lib.Dao;
import taxi.model.Driver;
import taxi.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String sqlQuery = "INSERT INTO drivers (driver_name, driver_lisence_number," +
                " driver_login, driver_password) VALUES (?,?,?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenceNumber());
            preparedStatement.setString(3, driver.getLogin());
            preparedStatement.setString(4, driver.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            while (resultset.next()) {
                driver.setId(resultset.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert driver " + driver, e);
        }
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        String sqlQuery = "SELECT * FROM drivers d WHERE drivers_id =? "
                + "AND d.driver_deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);) {
            preparedStatement.setLong(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            Driver newDriver = null;
            if (resultset.next()) {
                newDriver = createDriver(resultset);
            }
            return Optional.ofNullable(newDriver);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get driver by id " + id, e);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM drivers d WHERE d.driver_deleted = FALSE;";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                drivers.add(createDriver(resultset));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't getAll drivers ", ex);
        }
        return drivers;
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET driver_name=?, driver_lisence_number=?, driver_login=?"
                + " driver_password=? WHERE drivers_id =? AND driver_deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenceNumber());
            preparedStatement.setString(3, driver.getLogin());
            preparedStatement.setString(4, driver.getPassword());
            preparedStatement.setLong(5, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't update driver " + driver, ex);
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        String sqlQuery = "UPDATE drivers"
                + " SET driver_deleted = TRUE"
                + " WHERE drivers_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            int updatesMade = preparedStatement.executeUpdate();
            return updatesMade > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete driver by id " + id, ex);
        }
    }

    private Driver createDriver(ResultSet resultset) throws SQLException {
        Long id = resultset.getObject("drivers_id", Long.class);
        String name = resultset.getObject("driver_name", String.class);
        String licenseNumber = resultset.getObject("driver_lisence_number", String.class);
        String login = resultset.getObject("driver_login", String.class);
        String password = resultset.getObject("driver_password", String.class);
        Driver driver = new Driver(name, licenseNumber, login, password);
        driver.setId(id);
        return driver;
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        String query = "SELECT * FROM drivers d WHERE d.driver_login=? AND driver_deleted=false;";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, login);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                driver = createDriver(resultset);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get driver from db, by login= "+login,e);
        }
        return Optional.ofNullable(driver);
    }
}
