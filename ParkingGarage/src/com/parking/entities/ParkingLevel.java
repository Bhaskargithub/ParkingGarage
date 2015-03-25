package com.parking.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParkingLevel {

	private int ParkingGarageId;
	
	private int parkingLevel;
	
	private int carParkingArea;
	
	private int carParkingLotCount;
	
	private int motorBikeParkingArea;
	
	private int motorBikeParkingLotCount;
	
	public int getCarParkingArea() {
		return carParkingArea;
	}

	public void setCarParkingArea(int carParkingArea) {
		this.carParkingArea = carParkingArea;
	}

	public int getCarParkingLotCount() {
		return carParkingLotCount;
	}

	public void setCarParkingLotCount(int carParkingLotCount) {
		this.carParkingLotCount = carParkingLotCount;
	}

	public int getMotorBikeParkingArea() {
		return motorBikeParkingArea;
	}

	public void setMotorBikeParkingArea(int motorBikeParkingArea) {
		this.motorBikeParkingArea = motorBikeParkingArea;
	}

	public int getMotorBikeParkingLotCount() {
		return motorBikeParkingLotCount;
	}

	public void setMotorBikeParkingLotCount(int motorBikeParkingLotCount) {
		this.motorBikeParkingLotCount = motorBikeParkingLotCount;
	}

	private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getParkingGarageId() {
		return ParkingGarageId;
	}

	public void setParkingGarageId(int parkingGarageId) {
		ParkingGarageId = parkingGarageId;
	}

	public int getParkingLevel() {
		return parkingLevel;
	}

	public void setParkingLevel(int parkingLevel) {
		this.parkingLevel = parkingLevel;
	}

	public float getParkingArea() {
		return parkingArea;
	}

	public void setParkingArea(float parkingArea) {
		this.parkingArea = parkingArea;
	}

	private float parkingArea;
	
	
	
	
}
