package server.system;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import common.check.FillVehicle;
import common.exceptions.*;
import common.datamodule.*;
import server.psql.TabManager;

public class CollectionManager {

	private Connection connection;
	private ArrayList<Vehicle> vehicles;
	private TabManager tabManager;

	public CollectionManager(){}

	public CollectionManager(Connection connection, TabManager tabManager) {
		this.connection = connection;
		this.tabManager = tabManager;
	}

	public void setList(ArrayList<Vehicle> vehicles){
		this.vehicles = vehicles;
	}
	
	public ArrayList<Vehicle> getList() {
        return vehicles;
    }
	
	public String returnDate() {
		return LocalDate.now().toString();
	}
	
	/**
     * Info collection
     */
	
	public String Info() {
		return "Коллекция: машина"
				+ "\nДата инициализации: " + vehicles.stream().min(Comparator.comparing(Vehicle::getID)).get().getCreationDate() 
				+ "\nколичество элементов: " + vehicles.size();
    }
	
	/**
     * Add object to collection
     */
	
	public String Add(Vehicle vehicle, User user) throws InvalidInput, IOException, IllegaValue {
		tabManager.Add(vehicle, user);
		System.out.println("Object added: " + vehicle);
		return "Элемент добавлен";
	}

	/**
     * removes all elements from the collection
     */
	
	public String Clear(User user) {
		tabManager.clearAllOwned(user.getName());
		return "Коллекция очищена.";
	}
	
	/**
     * Show collection
     */
	
	public String Show() throws SQLException {
		this.vehicles = tabManager.show();
        if (vehicles.size() == 0){
        	return "Коллекция пуста.";
        }
        return vehicles.toString();
	}
	
	/**
     * Update an object in a collection
     */
	
	public String Update(String id, Vehicle vehicleNew, User user) throws IOException, IllegaValue, InvalidInput {
		if(FillVehicle.checkID(vehicles, id)) {
			Iterator<Vehicle> iterator = vehicles.iterator();
			while(iterator.hasNext()) {
				Vehicle vehicle = iterator.next();
				String uid = String.valueOf(vehicle.getID());
			    if(uid.equals(id)) {
			    	tabManager.Update(id, vehicleNew, user);
			    	System.out.println("Object update:" + vehicle);
			    	break;
			    }
			}
			return "Объект обновлён.";
		}else {
			return "в коллекции не существует такой id или колекция пустой";
		}
	}

	
	/**
     * Delete coordinates according to index
     */
	
	public String RemoveAt(String num) {
		if(FillVehicle.isNumeric(num) & vehicles.size() != 0) {
			int index = Integer.parseInt(num);
			try{
				vehicles.remove(index);
				return "Объект удален.";
			}catch(IndexOutOfBoundsException e) {
				return "в коллекции не существует такой индекс или колекция пустой";
			}
		}else {
			return "в коллекции не существует такой индекс или колекция пустой";
		}
	}
	
	/**
     * Delete coordinates according to id
     */
	
	public String RemoveByID(String id) {
		if(FillVehicle.checkID(vehicles, id)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicles WHERE id=?");
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
			System.out.println("Объект удален.");
		}else {
			System.out.println("в коллекции не существует такой id или колекция пустой");
		}
		return "";
	}

	/**
     * Print the collection in reverse order
     */

	public String PrintDes() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		Collections.reverse(vehicles);
		return vehicles.toString();

	}

	/**
     * Shuffle collection
     */

	public String Shuffle() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		Collections.shuffle(vehicles);
		return vehicles.toString();
	}

	/**
     * Reorder the collection
     */

	public String Sort() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		vehicles.sort(Comparator.comparingLong(Vehicle::getID));
		return vehicles.toString();
	}

	/**
     * Print the collection in reverse order
     */

	public String PrintDesFule() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		vehicles.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle s1, Vehicle s2) {
                return s1.getFuelType().compareTo(s2.getFuelType());
            }
        });
		return vehicles.toString();
	}

	/**
     * Classification objects according to fuel types
     */

	public String GroupCountingByFuleType() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		Map<FuelType, Long> GroupByFuleType = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getFuelType,Collectors.counting()));
		return GroupByFuleType.toString();
	}


	public String GroupByFuleType() {
		if(vehicles.size() == 0) {
			return "колекция пустой";
		}
		Map<FuelType, List<String>> GroupByFuleType = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getFuelType, Collectors.mapping(Vehicle::getName, Collectors.toList())));
		return GroupByFuleType.toString();
	}
		
	
	@Override
	public String toString() {
        return "Элементы коллекции: \n" + vehicles;
	}
}
