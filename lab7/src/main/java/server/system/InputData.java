package server.system;


import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.stream.*;

import common.datamodule.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputData {
	
	public ArrayList<Vehicle> readxml(String filename){
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(new InputStreamReader(new FileInputStream(filename)));
            Coordinates cool = new Coordinates();
            while (reader.hasNext()) {
            	if (reader.isStartElement() && reader.getLocalName().equals("class")){
                	Vehicle vehicle = new Vehicle();
                	vehicle.setID(Long.parseLong(reader.getAttributeValue(null, "id")));
                	vehicle.setName(reader.getAttributeValue(null, "name"));
                	cool.setX(Integer.parseInt(reader.getAttributeValue(null, "x")));
                 	cool.setY(Long.parseLong(reader.getAttributeValue(null, "y")));
                	vehicle.setCreationDate(LocalDate.parse(reader.getAttributeValue(null, "creationDate")));
                	vehicle.setEnginePower(Float.parseFloat(reader.getAttributeValue(null, "enginePower")));
                	vehicle.setCapacity(Double.parseDouble(reader.getAttributeValue(null, "capacity")));
                	vehicle.setDistanceTravelled(Float.parseFloat(reader.getAttributeValue(null, "distanceTravelled")));
                	vehicle.setFuelType(FuelType.valueOf(reader.getAttributeValue(null, "fuelType")));
                	vehicle.setCoordinates(cool);
                	vehicles.add(vehicle);
            	}
            	reader.next();
	        }
            reader.close();
        }  catch (Exception e) {
        	return vehicles;
        }
		return vehicles;
    }
	

	
	public int readsize(String filename) {
		int CountVehicle = 0;
		try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new InputStreamReader(new FileInputStream(filename)));
            
            
            while (reader.hasNext()) {
                if (reader.isStartElement() && "javaObject".equals(reader.getLocalName())) {
                	CountVehicle++;
                }
                reader.next();
            }
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return CountVehicle;
	}
	
	public ArrayList<String> readScript(String scriptPath) {
		OutputData.checkPermissions(scriptPath);
		File file = new File(scriptPath);
		ArrayList<String> commands = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String icommand;
			while ((icommand= reader.readLine())!= null) {
				commands.add(icommand);
			}
		} catch (IOException e) {
            System.out.println("Файл не найден" + scriptPath);
        }
		return commands;
	}
	
}
