package server.psql;

import common.datamodule.User;
import common.datamodule.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TabManager {
    private final Connection connection;
    private final TabCreator tabCreator;

    public TabManager(Connection connection, TabCreator tabCreator){
        this.connection = connection;
        this.tabCreator = tabCreator;
    }

    public ArrayList<Vehicle> show() throws SQLException {
        return tabCreator.init();
    }

    public void Add(Vehicle vehicle, User user) {
        try {
            vehicle.setCreationDate(LocalDate.now());
            System.out.println(vehicle);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO vehicles"
                + "(name, x, y, creationDate, enginePower, capacity, distanceTravelled, FuelType, username) "
                + "VALUES (?,?,?,?,?,?,?,?,?) RETURNING id");
			statement.setString(1, vehicle.getName());
			statement.setInt(2, vehicle.getCoordinatesX());
			statement.setLong(3, vehicle.getCoordinatesY());
			statement.setString(4, String.valueOf((vehicle.getCreationDate())));
			statement.setFloat(5, vehicle.getEnginePower());
			statement.setDouble(6, vehicle.getCapacity());
			statement.setFloat(7, vehicle.getDistanceTravelled());
			statement.setString(8, String.valueOf(vehicle.getFuelType()));
			statement.setString(9, user.getName());
			ResultSet result = statement.executeQuery();
			result.next();
        } catch (SQLException e) {
            System.out.println("Элемент не добавлен, ошибка в sql");
        }
	}

    public void Update(String id, Vehicle vehicle, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE vehicles SET "
                    + "name=?, x=?, y=?, creationDate=?, enginePower=?, capacity=?, distanceTravelled=?,"
                    + " FuelType=?, username=? WHERE id=?");
			statement.setString(1, vehicle.getName());
			statement.setInt(2, vehicle.getCoordinatesX());
			statement.setLong(3, vehicle.getCoordinatesY());
			statement.setString(4, String.valueOf((vehicle.getCreationDate())));
			statement.setFloat(5, vehicle.getEnginePower());
			statement.setDouble(6, vehicle.getCapacity());
			statement.setFloat(7, vehicle.getDistanceTravelled());
			statement.setString(8, String.valueOf(vehicle.getFuelType()));
			statement.setString(9, user.getName());
            statement.setInt(10, Integer.parseInt(id));
			ResultSet result = statement.executeQuery();
			result.next();
        } catch (SQLException e) {
            System.out.println("Элемент не обновлен, ошибка в sql");
        }
	}

    public void RemoveByID(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicles WHERE id=?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

    public void clearAllOwned(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicles WHERE username=?");
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
