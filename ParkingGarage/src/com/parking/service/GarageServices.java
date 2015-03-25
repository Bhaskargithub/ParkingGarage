package com.parking.service;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.parking.dbaccess.GarageQueries;
import com.parking.entities.DataObject;

@Path(value = "/garageService")
public class GarageServices {

	//Normal area consumed per vehicle parking - includes free area - in meters square
	private static int areaPerCar = 25;
	private static int areaPerBike = 2;
	
	
	@Path(value = "/createLevel/{id}/{id1}/{id2}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getVehicleLocation(@PathParam(value = "id") int levelId,
			@PathParam(value = "id1") int carFloorArea,
			@PathParam(value = "id2") int bikeFloorArea) {
		GarageQueries gqur = new GarageQueries();
		DataObject dObj = new DataObject();
		
		int bikesParkable = bikeFloorArea/areaPerBike;
		int carsParkable = carFloorArea/areaPerCar;
		
		dObj.setBikeParkingSpace(bikeFloorArea);
		dObj.setParkLevel(levelId);
		dObj.setTotalArea(bikeFloorArea+carFloorArea);
		dObj.setCarParkingSpace(carFloorArea);
		dObj.setNumberOfBikeParkingLot(bikesParkable);
		dObj.setNumberOfCarParkingLot(carsParkable);
		gqur.createParkingSpaces(dObj);
		return "SUCCESS";
	}
	
	
}
