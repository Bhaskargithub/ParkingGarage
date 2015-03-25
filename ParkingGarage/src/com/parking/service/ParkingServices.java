package com.parking.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.parking.dbaccess.AssignParkLot;
import com.parking.dbaccess.ParkingQueries;
import com.parking.entities.DataObject;

@Path(value = "/parkService")
public class ParkingServices {

	ParkingQueries pqur = new ParkingQueries();
	
	private static final String LICENCE_PLATE = "licencePlate";

	@Path(value = "/getLocation/{licenceId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DataObject getVehicleLocation(
			@PathParam(value = "licenceId") String licencePlate) {
		DataObject dObj = pqur.checkVehicle(licencePlate);
		return dObj;
	}

	@Path(value = "/checkVehicle/{licenceId}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String checkVehicleExist(
			@PathParam(value = "licenceId") String licencePlate) {
		System.out.println(licencePlate);
		
		System.out.println("value is "+pqur.checkVehicle(licencePlate));
		if(pqur.checkVehicle(licencePlate) != null){
		return "EXISTS";}
		else{
			return "DOESNT EXISTS";}
	}

	@Path(value = "/vehicleExit")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String removeVehicle(@PathParam(value = "licenceId") String licencePlate) {
		
		 ParkingQueries pqur = new ParkingQueries();
		 return pqur.removeVehicle(licencePlate);
	}
	
	
	@Path(value = "/parkingLots")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DataObject numberOfParkingLots() {
		
		 ParkingQueries pqur = new ParkingQueries();
		 return pqur.getNumberOfParkingLots();
	}

	@Path(value = "/assignparkingLots/{licenceId}/{vehicleType}")
	@PUT
	@Produces(MediaType.TEXT_HTML)
	public String assignParkingLocation(@PathParam(value = "licenceId") String licencePlate, @PathParam(value = "vehicleType") String vehicleType) {
		AssignParkLot apl = new AssignParkLot();
		return apl.AssignParkingLot(licencePlate, vehicleType);
	}
	
	
}
