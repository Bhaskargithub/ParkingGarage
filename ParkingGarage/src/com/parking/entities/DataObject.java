package com.parking.entities;

import javax.xml.bind.annotation.XmlRootElement;


// Data object created only for data transfer process over rest calls

@XmlRootElement
public class DataObject {

	private int parkingId;
	
	private String parkingLot;
	
	private int carParkingSpace;
	
	private int totalArea;
	
	private int bikeParkingSpace;
	
	private int numberOfCarParkingLot;
	
	private int numberOfBikeParkingLot;
	
	private int parkLevel;
	
	private int parkLocation;

	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	public int getCarParkingSpace() {
		return carParkingSpace;
	}

	public void setCarParkingSpace(int carParkingSpace) {
		this.carParkingSpace = carParkingSpace;
	}

	public int getBikeParkingSpace() {
		return bikeParkingSpace;
	}

	public void setBikeParkingSpace(int bikeParkingSpace) {
		this.bikeParkingSpace = bikeParkingSpace;
	}

	public int getParkLevel() {
		return parkLevel;
	}

	public void setParkLevel(int parkLevel) {
		this.parkLevel = parkLevel;
	}

	public int getParkLocation() {
		return parkLocation;
	}

	public void setParkLocation(int parkLocation) {
		this.parkLocation = parkLocation;
	}

	public int getNumberOfCarParkingLot() {
		return numberOfCarParkingLot;
	}

	public void setNumberOfCarParkingLot(int numberOfCarParkingLot) {
		this.numberOfCarParkingLot = numberOfCarParkingLot;
	}

	public int getNumberOfBikeParkingLot() {
		return numberOfBikeParkingLot;
	}

	public void setNumberOfBikeParkingLot(int numberOfBikeParkingLot) {
		this.numberOfBikeParkingLot = numberOfBikeParkingLot;
	}
	
	public String getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	public int getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(int totalArea) {
		this.totalArea = totalArea;
	}
	
}
