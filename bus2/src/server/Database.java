package server;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Database {
	public Connection connection;

	public Database() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "1234");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	
	public String[] fromList() {
		ResultSet resultSet;
		ArrayList<String> fromSet = new ArrayList<>();
		String[] fromSetArray = null;
		try {
			String query = String.format("SELECT pickup FROM vehicle");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				fromSet.add(resultSet.getString("pickup"));
			}
			fromSetArray = fromSet.toArray(new String[fromSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return fromSetArray;
	}
	
	
	public String[] toList() {
		ResultSet resultSet;
		ArrayList<String> toSet = new ArrayList<>();
		String[] toSetArray = null;
		try {
			String query = String.format("SELECT destination FROM vehicle");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				toSet.add(resultSet.getString("pickup"));
			}
			toSetArray = toSet.toArray(new String[toSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return toSetArray;
	}
	
	public boolean insertEmployee(String args[]) {
		try {
			String query = String.format("insert into employee (driverName,salary,dob) values ('%s',%s,'%s')", args[0],args[1],args[2]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	

	public boolean deleteEmployee(String id) {
		try {
			String query = String.format("delete from employee where driverId=%s", id);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	
	public boolean insertTicket(String args[]) {
		try {
			String query = String.format(
					"insert into ticket(userName,pickup,destination,amount,seatNo,phoneNo,pickupDate) values ('%s','%s','%s',%s,%s,%s,'%s')",
					args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	
	public boolean deleteTicket(int ticketNo) {
		try {
			String query = String.format("delete from ticket where ticketNo=%d", ticketNo);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	
	public ResultSet getCollection(String dob, boolean all) { // if all is true date can be garbage
		ResultSet resultSet;
		try {
			if (all) {
				String query = "select * from ticket";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
			} else {
				String query = String.format("select * from ticket where pickupDate='%s'", dob);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();

			}

			return resultSet;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	
	public ResultSet getVehicle(String regNO, boolean all) { // if all is true date can be garbage
		ResultSet resultSet;
		try {
			if (all) {
				String query = "select * from vehicle";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
			} else {
				String query = String.format("select * from ticket where regNO='%s'", regNO);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();

			}

			return resultSet;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public boolean insertVehicle(String args[]) {
		try {
			String query = String.format("insert into vehicle(regNO,driverId,pickup,destination,timing) values('%s',%s,%s,%s,%s)", args[0],args[1],args[2],args[3],args[4] );
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean deleteVehicle(String regNO) {
		try {
			String query = String.format("delete from vehicle where regNO = '%s'", regNO);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean insertClients(String args[]) {
		try {
			
			String query = String.format(
					"insert into clients(userName,userPassword,state,district,pincode,dob,phoneNo,gender) values ('%s',MD5('%s'),'%s','%s',%s,'%s',%s,'%s')",
					args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public ResultSet getClients(int userId, boolean all) { // if all is true date can be garbage
		ResultSet resultSet;
		try {
			if (all) {
				String query = "select * from clients";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
			} else {
				String query = String.format("select * from clients where userId = %d", userId);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();

			}

			return resultSet;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean checkLogin(String UserName,String Password) {
		try {
			String query = String.format("select userPassword from clients where userName='%s'",UserName);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String p = resultSet.getString("userPassword");
			MessageDigest md = MessageDigest.getInstance("MD5");
			  
            byte[] messageDigest = md.digest(Password.getBytes());
  
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            if(hashtext.equals(p)) {
            	return true;
            }
            else {
            	return false;
            }
		} catch (SQLException e) {
			return false;
		}
		catch (NoSuchAlgorithmException e) {
            return false;
        }
	}
//	public static void main(String[] args) {
//		Database db = new Database();
//		ResultSet tickets;
//		ResultSet passengers;
//		db.insertEmployee(10000, "2001-09-26");
//		db.surpriseEmployee(1);
//		String ticketDetails[] = { "Manoj", "Dandeli", "Bengaluru", "1000", "10", "1234567890", "2021-10-28" };
//		db.insertTicket(ticketDetails);
//		db.deleteTicket(1);
//		tickets = db.getCollection("2021-10-26", false);
//		try {
//			while (tickets.next()) {
//				String userName = tickets.getString("userName");
//				String pickup = tickets.getString("pickup");
//				String destination = tickets.getString("destination");
//				String amount = tickets.getString("amount");
//				String seatNo = tickets.getString("seatNo");
//				String phoneNo = tickets.getString("phoneNo");
//				String dob = tickets.getString("pickupDate");
//				String ticketNo = tickets.getString("ticketNo");
//				System.out.println(ticketNo + " " +userName + " " + pickup + " " + destination + " " + amount + " " + seatNo + " "
//						+ phoneNo + " " + dob);
//				System.out.println();
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}

//		db.insertAVehicle("KA1234", 2);
//		db.deleteVehicle("KA1234");
//		String clientDetails[] = { "Manoj", "1234", "Karnataka", "UK", "581325", "2001-09-26", "1234567890",
//				"alpha male" };
//		db.insertClients(clientDetails);
//		passengers = db.getClients(2, true);
//		try {
//			while (passengers.next()) {
//				String userId = passengers.getString("userId");
//				String userName = passengers.getString("userName");
//				String userPassword = passengers.getString("userPassword");
//				String state = passengers.getString("state");
//				String district = passengers.getString("district");
//				String pincode = passengers.getString("pincode");
//				String dob = passengers.getString("dob");
//				String phoneNo = passengers.getString("phoneNo");
//				String gender = passengers.getString("gender");
//				System.out.println(userId + " " +userName + " " + userPassword + " " + state + " " + district + " " + pincode + " "
//						+ dob + " " + phoneNo + " " + gender);
//				System.out.println();
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	
	

}