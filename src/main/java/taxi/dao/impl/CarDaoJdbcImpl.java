package taxi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxi.dao.CarDao;
import taxi.lib.Dao;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import taxi.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String sqlQuery = "INSERT INTO cars (cars_model, manufacturers_id)"
                + "VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.executeUpdate();
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            while (resultset.next()) {
                car.setId(resultset.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert car " + car, e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String sqlQuery = "SELECT * FROM cars c INNER JOIN manufacturer m "
                + "ON c.manufacturers_id = m.manufacturer_id "
                + "WHERE cars_deleted = false AND cars_id =?";
        Car newCar = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);) {
            preparedStatement.setLong(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                newCar = createCar(resultset);
            }
            if (newCar != null) {
                newCar.setDrivers(getAllCarDrivers(id, connection));
            }
            return Optional.ofNullable(newCar);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get car by id " + id, e);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars c INNER JOIN manufacturer m "
                + "ON c.manufacturers_id = m.manufacturer_id WHERE c.cars_deleted = false;";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                cars.add(createCar(resultset));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't getAll manufacturers ", ex);
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String updateQuery = "UPDATE cars SET cars_model=?,manufacturers_id=? "
                + " WHERE cars_id =? AND cars_deleted = false";
        String deleteQuery = "DELETE FROM car_drivers WHERE car_id = ?";
        String newInsertionCar = "INSERT INTO car_drivers (driver_id, car_id) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                PreparedStatement newInsertionCarStatement = connection
                        .prepareStatement(newInsertionCar)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.setLong(3, car.getId());
            preparedStatement.executeUpdate();

            deleteStatement.setLong(1, car.getId());
            deleteStatement.executeUpdate();

            newInsertionCarStatement.setLong(2, car.getId());
            for (Driver driver : car.getDrivers()) {
                newInsertionCarStatement.setLong(1, driver.getId());
                newInsertionCarStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Can't update car " + car, ex);
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String sqlQuery = "UPDATE cars"
                + " SET cars_deleted = TRUE"
                + " WHERE cars_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            int updatesMade = preparedStatement.executeUpdate();
            return updatesMade > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete car by id " + id, ex);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT * FROM car_drivers cd"
                + "JOIN drivers d ON d.drivers_id=cd.driver_id WHERE d.driver_deleted =false "
                + "JOIN cars c ON c.cars_id=cd.car_id WHERE c.cars_deleted =false"
                + "JOIN manufacturer m ON m.manufacturer_id = c.manufacturers_id";
        List<Car> listCars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCars.add(getCar(resultSet, connection));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listCars;
    }

    private Car createCar(ResultSet resultset) throws SQLException {
        Long id = resultset.getObject("cars_id", Long.class);
        String model = resultset.getObject("cars_model", String.class);
        String manufacturerName = resultset.getObject("name", String.class);
        String manufacturerCountry = resultset.getObject("country", String.class);
        Long manufacturerId = resultset.getObject("manufacturer_id", Long.class);
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerId);
        Car car = new Car(model, manufacturer);
        car.setId(id);
        return car;
    }

    private List<Driver> getAllCarDrivers(Long id, Connection connection) throws SQLException {
        String query = "SELECT * FROM car_drivers cd JOIN drivers d ON cd.driver_id=d.drivers_id "
                + "WHERE car_id=? AND d.driver_deleted =false";
        List<Driver> drivers = new ArrayList<>();
        try (PreparedStatement driversStatement = connection.prepareStatement(query)) {
            driversStatement.setLong(1, id);
            ResultSet resultset = driversStatement.executeQuery();
            while (resultset.next()) {
                drivers.add(getDriverFromResultSet(resultset));
            }
        }
        return drivers;
    }

    private Driver getDriverFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject(1, Long.class);
        String name = resultSet.getString("driver_name");
        String lisenceNumber = resultSet.getString("driver_lisence_number");
        Driver driver = new Driver(name, lisenceNumber);
        driver.setId(id);
        return driver;
    }

    private Car getCar(ResultSet resultSet, Connection connection) throws SQLException {
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(manufacturerId);
        Long id = resultSet.getObject(1, Long.class);
        String model = resultSet.getString("cars_model");
        Car car = new Car(model, manufacturer);
        car.setId(id);
        return car;
    }
}
