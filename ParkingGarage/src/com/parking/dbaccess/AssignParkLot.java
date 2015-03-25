package com.parking.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.parking.entities.DataObject;

public class AssignParkLot {

	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement2 = null;

	/*
	 * Assign the location for car or bike Gets the available nearest parking
	 * lot and assign the location with level and location detail
	 */

	public synchronized String AssignParkingLot(String licenceId,
			String vehicleType) {
		DbConnection conn = new DbConnection();
		connection = conn.getDBConnection();

		try {

			preparedStatement2 = connection
					.prepareStatement("SELECT PARKING_ID,LEVEL_ID,LOCATION_ID FROM PARKING_LOT WHERE LICENCE_PLATE IS NULL AND VEHICLE_TYPE=? ORDER BY 'LEVEL_ID','LOCATION_ID' ASC");
			preparedStatement2.setString(1, vehicleType);
			rs = preparedStatement2.executeQuery();
			if (!rs.next()) {
				return "Sorry no more parking location";
			} else {

				preparedStatement = connection
						.prepareStatement("UPDATE PARKING_LOT SET LICENCE_PLATE=?,VEHICLE_TYPE=? where PARKING_ID=?;");
				preparedStatement.setString(1, licenceId);
				preparedStatement.setString(2, vehicleType);
				preparedStatement.setInt(3, rs.getInt("PARKING_ID"));
				preparedStatement.executeUpdate();
				return "Go ahead and park your " + vehicleType + " in level "
						+ rs.getInt("LEVEL_ID") + " location"
						+ rs.getInt("LOCATION_ID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

}
