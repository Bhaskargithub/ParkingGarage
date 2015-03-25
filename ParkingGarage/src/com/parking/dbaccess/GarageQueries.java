package com.parking.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.parking.entities.DataObject;

public class GarageQueries {

	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement2 = null;

	// Creates new level in PARKING_LEVEL table
	public String createParkingSpaces(DataObject dObj) {
		DbConnection conn = new DbConnection();
		connection = conn.getDBConnection();
		try {
			deleteLevel(dObj);
			dObj.setParkingLot("VENCE");
			createParkingLevelSpace(dObj);

			preparedStatement2 = connection
					.prepareStatement("INSERT INTO PARKING_LEVEL(`LEVEL_ID`, `PARKING_LOT`, `TOTAL_AREA`, `CAR_AREA`, `BIKE_ARE`) "
							+ "VALUES (?,?,?,?,?)");
			preparedStatement2.setInt(1, dObj.getParkLevel());
			preparedStatement2.setString(2, dObj.getParkingLot());
			preparedStatement2.setInt(3, dObj.getTotalArea());
			preparedStatement2.setInt(4, dObj.getCarParkingSpace());
			preparedStatement2.setInt(5, dObj.getBikeParkingSpace());
			preparedStatement2.executeUpdate();

			createParkingLevelSpace(dObj);

			return "SUCCESS";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAILURE";

	}

	// Cascaded operation - when a level is deleted then the corresponding
	// parking
	// lot in level are deleted in table PARKING_LOT
	public boolean deleteLevel(DataObject dObj) {
		try {
			preparedStatement = connection
					.prepareStatement("DELETE from PARKING_LEVEL where LEVEL_ID=?");
			preparedStatement.setInt(1, dObj.getParkLevel());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	// Creates new parking lots in PARKING_LOT table - separate for car and
	// bikes
	public boolean createParkingLevelSpace(DataObject dObj) {

		// Just to keep the count start after car parking is done
		int bikeParkCount = dObj.getNumberOfBikeParkingLot();
		int count = dObj.getNumberOfCarParkingLot();
		String vechicle = "CAR";
		for (int h = 0; h < 2; h++) {
			if (h == 1) {
				count = bikeParkCount;
				vechicle = "BIKE";
			}
			for (int i = 1; i <= count; i++) {
				try {
					preparedStatement2 = connection
							.prepareStatement("INSERT INTO PARKING_LOT( `LEVEL_ID`, `LOCATION_ID`, `LICENCE_PLATE`, `VEHICLE_TYPE`) "
									+ "VALUES (?,?,?,?)");

					preparedStatement2.setInt(1, dObj.getParkLevel());
					preparedStatement2.setString(3, null);
					preparedStatement2.setInt(2, i);
					preparedStatement2.setString(4, vechicle);
					preparedStatement2.executeUpdate();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return false;

	}

}
