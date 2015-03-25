package com.parking.entities;

public class MotorBike extends Vehicle {


	public String getLicencePlateId() {
		return licencePlateId;
	}

	public void setLicencePlateId(String licencePlateId) {
		this.licencePlateId = licencePlateId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	private String licencePlateId;
	
	private String vehicleType;
}
