package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class Doctor {
	
	public Connection connect() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctordb","root", "");
			// For testing
			System.out.println("Successfully connected---1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String viewDoctors() {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
//			output = "<table border=\"1\"><tr><th>Doctor ID</th>" 
//					+ "<th>Doctor Name</th><th>Doctor Address</th>"
//					+ "<th>Doctor Specialty</th><th>Doctor Mobile</th>"
//					+ "<th colspan=\"2\">Actions</th>";
			
			output = "<table border='1'><tr><th>Doctor ID</th>" + "<th>Doctor Name</th><th>Doctor Address</th>"+ "<th>Doctor Specialty</th>"
					+ "<th>Doctor Mobile</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from doctors";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String dId = rs.getString("DId");
				String dName = rs.getString("DName");
				String dAddress = rs.getString("DAddress");
				String dSpecialty = rs.getString("DSpecialty");
				String dMobile = rs.getString("DMobile");

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'" + "name='hidItemIDUpdate' type='hidden'" + "value='"
						+ dId + "'>" + dId + "</td>";
				
				//output += "<tr><td>" + dId + "</td>";
				//output += "<tr><td>" + dId + "</td>";
				output += "<td>" + dName + "</td>";
				output += "<td>" + dAddress + "</td>";
				output += "<td>" + dSpecialty + "</td>";
				output += "<td>" + dMobile + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button'" + "value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button'"
						+ "value='Remove'" + "class='btnRemove btn btn-danger' data-itemid='" + dId + "'>"
						+ "</td></tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Doctors Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String addDoctor(String id, String name, String address, String specialty, String mobile) {

		String output = "";
		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO doctors (DId, DName, DAddress, DSpecialty, DMobile) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, specialty);
			preparedStmt.setString(5, mobile);
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			String newDoctor = viewDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
			System.out.println("Add "+output);

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateDoctor(String did, String name, String address, String specialty, String mobile) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctors SET DName=?,DAddress=?,DSpecialty=?,DMobile=? WHERE DId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, specialty);
			preparedStmt.setString(4, mobile);
			preparedStmt.setString(5, did);
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			String newDoctor = viewDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String dId) {
		String output = "";
		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from doctors where DId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, dId);

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			
			String newDoctor = viewDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
//	public Boolean GetSessionStatus(int doctorID) {
//		int user = 0;
//		try {
//			Connection con = connect();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM doctors WHERE DId='"+doctorID+"'");
//			
//			while(rs.next()) {
//				user = rs.getInt("DId");
//				System.out.println("User....."+ user);
//			}
//		}catch(Exception e){
//				System.out.println("Error....."+ e);
//			}
//			if(doctorID == user) {
//				return true;
//			}else {
//				return false;
//			}
//		}
//	
//	public String GetSession(int doctorID) {
//		if(GetSessionStatus(doctorID)) {
//			System.out.println("User.....True");
//			return "True";
//			
//		}else {
//			System.out.println("User.....False" + doctorID);
//			return "False";
//			
//		}
//	}
}
