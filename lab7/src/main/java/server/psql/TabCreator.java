package server.psql;

import common.datamodule.Coordinates;
import common.datamodule.FuelType;
import common.datamodule.Vehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class TabCreator {
    private Connection connection;

    public TabCreator(Connection connection) {
        this.connection = connection;
    }

    private void createDataTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS vehicles"
                + "(id SERIAL PRIMARY KEY, "
                + " name VARCHAR(50) NOT NULL, "
                + " x INT NOT NULL CHECK(x<787), "
                + " y BIGINT NOT NULL, "
                + " creationDate VARCHAR(50) NOT NULL, "
                + " enginePower BIGINT, "
                + " capacity INT NOT NULL,"
                + " distanceTravelled INT NOT NULL,"
                + " FuelType VARCHAR(50), "
                + " username VARCHAR(100) NOT NULL,"
                + " FOREIGN KEY(username) REFERENCES users (username))");
    }



    public ArrayList<Vehicle> init() throws SQLException {
        createDataTable();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM vehicles");
        while (result.next()) {
            vehicles.add(getVehicle(result));
        }
        return vehicles;
    }

    private Vehicle getVehicle(ResultSet result) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setID(result.getLong("id"));
        vehicle.setName(result.getString("name"));
        vehicle.setCoordinates(new Coordinates(result.getInt("x"), result.getLong("y")));
        vehicle.setCreationDate(LocalDate.parse(result.getString("creationDate")));
        vehicle.setEnginePower(result.getFloat("enginePower"));
        vehicle.setCapacity(result.getDouble("capacity"));
        vehicle.setDistanceTravelled(result.getFloat("distanceTravelled"));
        vehicle.setFuelType(FuelType.valueOf(result.getString("fuelType")));
        vehicle.setUser(result.getString("username"));
        return vehicle;
    }
}
