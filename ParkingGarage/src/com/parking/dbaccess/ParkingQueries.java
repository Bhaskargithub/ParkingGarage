package com.parking.dbaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.parking.entities.DataObject;

public class ParkingQueries {

	private Statement stmt = null;
	private Statement stmt1 = null;
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement2 = null;

	//Get available parking lots for both car and bikes
	public DataObject getNumberOfParkingLots() {
		DbConnection conn = new DbConnection();
		connection = conn.getDBConnection();
		DataObject dobj = new DataObject();

		try {
			stmt = connection.createStatement();
			rs = stmt
					.executeQuery("select count(*) from PARKING_LOT where VEHICLE_TYPE = 'CAR' and LICENCE_PLATE is null; ");
			stmt1 = connection.createStatement();
			rs2 = stmt1
					.executeQuery("select count(*) from PARKING_LOT where VEHICLE_TYPE = 'BIKE' and LICENCE_PLATE is null; ");
			rs.first();
			dobj.setNumberOfCarParkingLot(rs.getInt(1));
			rs2.first();
			dobj.setNumberOfBikeParkingLot(rs2.getInt(1));
			return dobj;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//Check if vehicle already exist - use AJAX CALL
	public DataObject checkVehicle(String licencePlate) {
		DbConnection conn = new DbConnection();
		connection = conn.getDBConnection();
		ResultSet rs = null;
		DataObject dobj = new DataObject();

		try {

			preparedStatement = connection
					.prepareStatement("select PARKING_ID,LEVEL_ID,LOCATION_ID from PARKING_LOT where LICENCE_PLATE = ? ; ");
			preparedStatement.setString(1, licencePlate);
			rs = preparedStatement.executeQuery();
			// System.out.println(rs.next());
			/*
			 * rs.first(); System.out.println("the park id is "+rs.getInt(1) +
			 * rs.next()); rs.beforeFirst();
			 */
			if (rs.next()) {
				dobj.setParkLevel(rs.getInt("LEVEL_ID"));
				dobj.setParkLocation(rs.getInt("LOCATION_ID"));
				dobj.setParkingId(rs.getInt("PARKING_ID"));
				System.out.println("inside true");
				return dobj;

			} else {
				System.out.println("inside false");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Remove vehicle from PARKING_LOT table on exit
	public String removeVehicle(String licencePlate) {
		DbConnection conn = new DbConnection();
		connection = conn.getDBConnection();
		try {
			preparedStatement = connection
					.prepareStatement("update PARKING_LOT set LICENCE_PLATE= NULL where LICENCE_PLATE = ? ; ");
			preparedStatement.setString(1, licencePlate);
			preparedStatement.executeUpdate();
			return "SUCCESS - BYE BYE";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "FAILURE to EXIT";
	}

}
