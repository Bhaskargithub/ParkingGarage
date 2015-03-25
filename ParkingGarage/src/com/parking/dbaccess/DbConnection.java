package com.parking.dbaccess;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

	// public static void main(String[] argv) {
	public Connection getDBConnection() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ParkingGarage", "root",
					"bhaskar");

		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Mysql connected");
			return connection;
		} else {
			System.out.println("Failed connection");
		}
		return null;
	}

}
