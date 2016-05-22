package datamodel;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database is a class that specifies the interface to the movie database. Uses
 * JDBC and the MySQL Connector/J driver.
 */
public class Database {
	/**
	 * The database connection.
	 */
	private Connection conn;

	/**
	 * Create the database interface object. Connection to the database is
	 * performed later.
	 */
	public Database() {
		conn = null;
	}

	/**
	 * Open a connection to the database, using the specified user name and
	 * password.
	 *
	 * @param userName
	 *            The user name.
	 * @param password
	 *            The user's password.
	 * @return true if the connection succeeded, false if the supplied user name
	 *         and password were not recognized. Returns false also if the JDBC
	 *         driver isn't found.
	 */
	public boolean openConnection(String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://puccini.cs.lth.se/" + userName, userName, password);
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;

		System.err.println("Database connection closed.");
	}

	/**
	 * Check if the connection to the database has been established
	 *
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}


	public Connection getConnection() {
		return conn;
	}

	public void orderPallets(ArrayList<String[]> fullOrder) {
		
		String cookieName;
		int amountOfPallets;
		String deliveryAddress; 
		String companyName;
		String deliveryDate;

		long orderNbr = -3;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO orders VALUES (default, ?, default, ?, ?, default);", Statement.RETURN_GENERATED_KEYS);
			cookieName = fullOrder.get(0)[0];
			amountOfPallets = Integer.parseInt(fullOrder.get(0)[1]);
			deliveryAddress = fullOrder.get(0)[2];
			companyName = fullOrder.get(0)[3];
			deliveryDate = fullOrder.get(0)[4];
			
			ps.setString(1, companyName);
			ps.setString(2, deliveryAddress);
			ps.setString(3, deliveryDate);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while (rs.next()) {
				orderNbr = rs.getLong(1);
				System.out.println("ORDERNBR = " + orderNbr);
			}

			
			for (int i = 0; i < fullOrder.size(); i++) {
				cookieName = fullOrder.get(i)[0];
				amountOfPallets = Integer.parseInt(fullOrder.get(i)[1]);



				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO pallets VALUES (?, ?, default, default, ?);");
				PreparedStatement ps3 = conn.prepareStatement("UPDATE ingredients natural join recipes " +
						"set IngQuantity = ingQuantity - quantity " + 
						"where CookieName = ?;"
					  );


				for (int j = 0; j < amountOfPallets; j++) {
					ps2.setLong(1, orderNbr);
					ps2.setString(2, cookieName);
					ps3.setString(1, cookieName);
					ps2.setString(3, "DEFAULT LOCATION");
					ps2.executeUpdate();
					ps3.executeUpdate();
				}
			}

		} catch(SQLException e) {

			e.printStackTrace();
			System.out.println("Something wrong went wrong Database/orderPallet");
		}

	}

	public Pallet getPallet(String barCode) {
		Pallet pallet = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM pallets WHERE BarCode=?");
			ps.setString(1, barCode);
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()){
				pallet = new Pallet(rs.getString("OrderNbr"), rs.getString("CookieName"), rs.getString("BarCode"), rs.getBoolean("BlockStatus"), rs.getString("Location"));
			}

		} catch(SQLException e) {

			e.printStackTrace();
			System.out.println("Something wrong went wrong Database/getPallet");
		}
		return pallet;
	}

	public void blockPallet(String barCode) {
		String cookieName = "def";
		Date orderDate = null;
		
		try {
			/*
			PreparedStatement ps = conn.prepareStatement("UPDATE pallets SET BlockStatus = true WHERE barCode=?", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, barCode);
			ps.executeUpdate();
			*/
			PreparedStatement ps2 = conn.prepareStatement("SELECT CookieName, OrderDate FROM pallets NATURAL JOIN orders WHERE BarCode = ?;");
			ps2.setLong(1, Long.parseLong(barCode));
			
			ps2.executeQuery();
			
			ResultSet rs2 = ps2.getResultSet();
			
			while (rs2.next()) {
				System.out.println("rs2.next");
				cookieName = rs2.getString(1);
				orderDate = rs2.getDate(2);
			}
			
			PreparedStatement ps4 = conn.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
			ps4.executeUpdate();
		
			System.out.println("KAKNAMN: " + cookieName + "   orderDateS: " + new Date(orderDate.getTime() - 7l*24l*60l*60l*1000l) + "   orderDateF: " + new Date(orderDate.getTime() + 1l*24l*60l*60l*1000l));
			
			PreparedStatement ps3 = conn.prepareStatement(
															"UPDATE pallets SET BlockStatus = TRUE " + 
															"WHERE OrderNbr IN (" + 
															"SELECT OrderNbr FROM (SELECT * FROM orders NATURAL JOIN pallets) " +  
															"AS testTable WHERE date(OrderDate) = ? " +  
															"AND CookieName = ?" + 
															"); ");
			
			ps3.setDate(1, new Date(orderDate.getTime()));
		//	ps3.setDate(2, new Date(orderDate.getTime() + 1l*24l*60l*60l*1000l));
			ps3.setString(2, cookieName);

			ps3.executeUpdate();
			
			PreparedStatement ps5 = conn.prepareStatement("SET SQL_SAFE_UPDATES = 1;");
			ps5.executeUpdate();
			

		} catch(SQLException e) {

			e.printStackTrace();
			System.out.println("Something wrong went wrong Database/blockPallet");
		}
	}
}
