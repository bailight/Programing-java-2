package com.datamodule;

import java.io.Serializable;

public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id; 
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Float enginePower;
    private double capacity;
    private float distanceTravelled;
    private FuelType fuelType;
    /**
     * builds new object and generate id and current date as date of creation
     *
     * @param id				id of the Vehicle(must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically)
     * @param name              name of the Vehicle (cannot be null, String cannot be empty)
     * @param coordinates       coordinates of the Vehicle (Field cannot be null)
     * @param creationDate		Vehicle creation date (cannot be null, the value of this field must be generated automatically)
     * @param enginePower       Vehicle engine power(can be null, Field value must be greater than 0)
     * @param capacity        	Fuel tank capacity(must be greater than 0)
     * @param distanceTravelled The furthest distance a vehicle can travel with a full tank of fuel(The field value must be greater than 0)
     * @param fuelType          Types of gasoline needed for cars (can be null)
     */
    
    public Vehicle(){}
    
    public Vehicle(Long id, String name, Coordinates coordinates, java.time.LocalDate creationDate, Float enginePower, double capacity, float distanceTravelled, FuelType fuelType){
    	this.id = id;
    	this.name = name;
    	this.coordinates = coordinates;
    	this.creationDate = creationDate;
    	this.enginePower = enginePower;
    	this.capacity = capacity;
    	this.distanceTravelled = distanceTravelled;
    	this.fuelType = fuelType;
    }
    
    public void setID(Long id) {
    	this.id = id;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
    	this.coordinates = coordinates;
    }
    public void setCreationDate(java.time.LocalDate creationDate) {
    	this.creationDate = creationDate;
    }
    public void setEnginePower(Float enginePower) {
    	this.enginePower = enginePower;
    }
    public void setCapacity(double capacity) {
    	this.capacity = capacity;
    }
    public void setDistanceTravelled(Float distanceTravelled) {
    	this.distanceTravelled = distanceTravelled;
    }
    public void setFuelType(FuelType fuelType) {
    	this.fuelType = fuelType;
    }
    
    public long getID() {
    	return id;
    };
    public String getName() {
    	return name;
    };
    public int getCoordinatesX() {
    	return coordinates.getX();
    };
    public Long getCoordinatesY() {
    	return coordinates.getY();
    };
    public java.time.LocalDate getCreationDate() {
    	return creationDate;
    };
    public Float getEnginePower() {
    	return enginePower;
    };
    public double  getCapacity() {
    	return capacity;
    };
    public float getDistanceTravelled() {
    	return distanceTravelled;
    };
    public FuelType getFuelType() {
    	return fuelType;
    };
    
    
    @Override
    public String toString() {
        return  "\nid: " + id +
				"\nname: " + name +
				"\ncoordinates: " + coordinates +
				"\ncreationDate: " + creationDate +
				"\nenginePower: " + enginePower +
				"\ncapacity: " + capacity +
				"\ndistanceTravelled: " + distanceTravelled +
				"\nfuelType: " + fuelType;
    }
}

