package Server.system;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



import common.exceptions.*;
import common.Fill;
import common.datamodule.*;

public class CollectionManager {
	private String filePath;
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private final InputData inputData = new InputData();
	private final OutputData outputData = new OutputData();
	
	public CollectionManager() {}
		
	
	public CollectionManager(String filePath) {
		this.filePath = filePath;
		this.vehicles = inputData.readxml(filePath);
	}
	
	public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
	
	
	public String getfilePath() {
        return filePath;
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
	 * @return 
     */
	
	public String Add(Vehicle vehicle) throws InvalidInput, IOException, IllegaValue {
		Long id = 1L;
		if (vehicles.size() != 0) {
			id = vehicles.stream().max(Comparator.comparing(Vehicle::getID)).get().getID() + 1L;
			vehicle.setID(id);
			vehicles.add(vehicle);
		}else {
			vehicle.setID(1L);
			vehicles.add(vehicle);
		}
		System.out.println("Object added: " + vehicle);
		return "Элемент добавлен";
	}
	
	/**
     * removes all elements from the collection
     */
	
	public String Clear() {
		vehicles.clear();
		return "Коллекция очищена.";
	}
	
	/**
     * Show collection
     */
	
	public String Show() {
        if (vehicles.size() == 0){
        	return "Коллекция пуста.";
        }
        return vehicles.toString();
	}
	
	/**
     * Save collection
     */
	
	public String Save() {
		OutputData.checkPermissions(filePath);
		outputData.save(vehicles, filePath);
		return "Коллекция сохранена.";
	}
	
	/**
     * Update an object in a collection
     */
	
	public String Update(String id, Vehicle vehicleNew) throws IOException, IllegaValue, InvalidInput {
		if(Fill.checkID(vehicles, id)) {
			Iterator<Vehicle> iterator = vehicles.iterator();
			while(iterator.hasNext()) {
				Vehicle vehicle = iterator.next();
				String uid = String.valueOf(vehicle.getID());
				System.out.print(uid);
			    if(uid.equals(id)) {
			    	vehicle.setName(vehicleNew.getName());
			    	Coordinates coor = new Coordinates(vehicleNew.getCoordinatesX(), vehicleNew.getCoordinatesY());
			    	vehicle.setCoordinates(coor);
			    	vehicle.setEnginePower(vehicleNew.getEnginePower());
			    	vehicle.setCapacity(vehicleNew.getCapacity());
			    	vehicle.setDistanceTravelled(vehicleNew.getDistanceTravelled());
			    	vehicle.setFuelType(vehicleNew.getFuelType());
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
		vehicles.sort(Comparator.comparingLong(Vehicle::getID));
		Collections.sort(vehicles, new Comparator<Vehicle>() {
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
	
	/**
     * Delete coordinates according to index
	 * @return 
     */
	
	public String RemoveAt(String num) {
		if(Fill.isNumeric(num) & vehicles.size() != 0) {
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
	 * @return 
     */
	
	public String RemoveByID(String id) {
		if(Fill.checkID(vehicles, id)) {
			Iterator<Vehicle> iterator = vehicles.iterator();
			while(iterator.hasNext()) {
				Vehicle vehicle = iterator.next();
				String uid = String.valueOf(vehicle.getID());
			    if(uid.equals(id)) {
			        iterator.remove();
			    }
			}
			System.out.println("Объект удален.");
		}else {
			System.out.println("в коллекции не существует такой id или колекция пустой");
		}
		return "";
	}
		
	
	@Override
	public String toString() {
        return "Элементы коллекции: \n" + vehicles;
	}
}
