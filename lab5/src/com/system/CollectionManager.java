package com.system;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.datamodule.*;
import com.system.exceptions.IllegaValue;
import com.system.exceptions.InvalidInput;
import com.datamodule.FuelType;

public class CollectionManager {
	private String filePath;
	private java.time.LocalDate DataSave;
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private final InputData inputData = new InputData();
	private final OutputData outputData = new OutputData();
	private final Fill fill = new Fill();
	
	public CollectionManager() {
		this.DataSave = vehicles.stream().min(Comparator.comparing(Vehicle::getID)).get().getCreationDate();
	}
	
	public CollectionManager(String filePath) {
		this.DataSave = vehicles.stream().min(Comparator.comparing(Vehicle::getID)).get().getCreationDate();
		this.filePath = filePath;
		this.vehicles = inputData.readxml(filePath);
	}
	
	public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
	
	public void setDataSave() {
		this.DataSave = LocalDate.now();
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
	
	public String Info() {
		return "Коллекция: Колесо" 
				+ "\nДата инициализации: " + this.DataSave 
				+ "\nколичество элементов: " + vehicles.size();
    }
	
	public void Add() throws InvalidInput, IOException, IllegaValue {
		this.DataSave = LocalDate.now();
		Long id = 1L;
		if (vehicles.size() != 0) {
			id = vehicles.stream().max(Comparator.comparing(Vehicle::getID)).get().getID() + 1L;
		}
		vehicles.add(fill.fillvehicle(id, DataSave));
	}
	
	public void Clear() {
		vehicles.clear();
		System.out.println("Коллекция очищена.");
	}
	
	public void Show() {
        if (vehicles.size() != 0){
        	System.out.println(vehicles);
        }else{
        	System.out.println("Коллекция пуста.");
        }
	}
	
	public void Save() {
		OutputData.checkPermissions(filePath);
		outputData.save(vehicles, filePath);
		System.out.println("Коллекция сохранена.");
	}
	
	public void Update(String id) throws IOException, IllegaValue, InvalidInput {
		if(Fill.checkID(vehicles, id)) {
			Iterator<Vehicle> iterator = vehicles.iterator();
			while(iterator.hasNext()) {
				Vehicle vehicle = iterator.next();
				String uid = String.valueOf(vehicle.getID());
			    if(uid.equals(id)) {
			    	vehicle.setName(fill.readName());
			    	vehicle.setCoordinates(fill.readCoordinates());
			    	vehicle.setEnginePower(fill.readEnginePower());
			    	vehicle.setCapacity(fill.readCapacity());
			    	vehicle.setDistanceTravelled(fill.readDistanceTravelled());
			    	vehicle.setFuelType(fill.readFuelType());
			    }
			    
			}
			System.out.println("Объект обновлён.");
		}else {
			System.out.println("в коллекции не существует такой id или колекция пустой");
		}
	}
	
	public void PrintDes() {
		if(vehicles.size() != 0) {
			Collections.reverse(vehicles);
			System.out.println(vehicles);
			Collections.reverse(vehicles);
		}else {
			System.out.println("колекция пустой");
		}
	}
	
	public void Shuffle() {
		if(vehicles.size() != 0) {
			Collections.shuffle(vehicles);
			System.out.println(vehicles);
		}else {
			System.out.println("колекция пустой");
		}
	}
	
	public void Sort() {
		if(vehicles.size() != 0) {
			vehicles.sort(Comparator.comparingLong(Vehicle::getID));
			System.out.println(vehicles);
		}else {
			System.out.println("колекция пустой");
		}
	}
	
	public void PrintDesFule() {
		if(vehicles.size() != 0) {
			 Collections.sort(vehicles, new Comparator<Vehicle>() {
		            @Override
		            public int compare(Vehicle s1, Vehicle s2) {
		                return s1.getFuelType().compareTo(s2.getFuelType());
		            }
		        });
			System.out.println(vehicles);
		}else {
			System.out.println("колекция пустой");
		}
	}
	
	public void GroupCountingByFuleType() {
		if(vehicles.size() != 0) {
			Map<FuelType, Long> GroupByFuleType = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getFuelType,Collectors.counting()));
			System.out.println(GroupByFuleType);
			
		}else {
			System.out.println("колекция пустой");
		}
	}
	
	
	public void GroupByFuleType() {
		if(vehicles.size() != 0) {
			Map<FuelType, List<String>> GroupByFuleType = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getFuelType, Collectors.mapping(Vehicle::getName, Collectors.toList())));
			System.out.println(GroupByFuleType);
		}else {
			System.out.println("в коллекции не существует такой индекс или колекция пустой");
		}
	}
	
	public void RemoveAt(String num) {
		if(Fill.isNumeric(num) & vehicles.size() != 0) {
			int index = Integer.parseInt(num);
			try{
				vehicles.remove(index);
				System.out.println("Объект удален.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("в коллекции не существует такой индекс или колекция пустой");
			}
		}else {
			System.out.println("в коллекции не существует такой индекс или колекция пустой");
		}
	}
	
	public void RemoveByID(String id) {
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
	}
		
	
	@Override
	public String toString() {
        return "Элементы коллекции: \n" + vehicles;
	}
}
