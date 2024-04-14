package com.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.*;

import com.datamodule.Vehicle;

public final class OutputData {
   
    public void save(ArrayList<Vehicle> Vehicles, String filepath) {
    	try {
    		XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(filepath));
			
			writer.writeStartDocument();
            writer.writeStartElement("vehicle");
            for (Vehicle vehicle : Vehicles) {
                writer.writeStartElement("class");
                writer.writeAttribute("id", String.valueOf(vehicle.getID()));
                writer.writeAttribute("name", vehicle.getName());
                writer.writeAttribute("x", String.valueOf(vehicle.getCoordinatesX()));
                writer.writeAttribute("y", String.valueOf(vehicle.getCoordinatesY()));
                writer.writeAttribute("creationDate", String.valueOf(vehicle.getCreationDate()));
                writer.writeAttribute("enginePower", String.valueOf(vehicle.getEnginePower()));
                writer.writeAttribute("capacity", String.valueOf(vehicle.getCapacity()));
                writer.writeAttribute("distanceTravelled", String.valueOf(vehicle.getDistanceTravelled()));
                writer.writeAttribute("fuelType", String.valueOf(vehicle.getFuelType()));
                writer.writeEndElement();
            }
            writer.writeEndDocument();

            writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
	
    public static void checkPermissions(String filepath) {
    	File file = new File(filepath);
    	if (!file.canRead()) {
    		file.setReadable(true);
    	}
    	if (!file.canWrite()) {
    		file.setWritable(true);
    	}
    	if (!file.canExecute()) {
    		file.setExecutable(true);
    	}
    }
    
}
