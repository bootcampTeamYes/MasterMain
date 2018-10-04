package com.easyLink.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.easyLink.links.URL;
import com.easyLink.registration.Registration;

@Service
public class RegistrationDatabaseManager {

	URL link = new URL();
	PreparedStatement ps;
	ResultSet rs;
	String sql = null;
	protected Connection conn;

	private static final String USERNAME = "root";
	private static final String PASSWORD = "abcd1234";
	private static final String CONN_STRING = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false";

	private void CreateConnection() throws ClassNotFoundException, SQLException {

		if (conn == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

			conn.setAutoCommit(false);
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	// Printē visus linkus
	public List<Registration> getAllRegistrations() throws ClassNotFoundException, SQLException {

		CreateConnection();

		List<Registration> liste = new ArrayList<Registration>();
		String sql = "SELECT * FROM easylink.Registration";

		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {

			Registration link = new Registration(rs.getString(1), rs.getString(2), rs.getString(3));
			liste.add(link);
		}

		return liste;
	}

	// Atrod linku pēc id(saīsinātā nosaukuma), ja neatrod, tad atgriež tukšu linku
	public Registration getRegistration(String username) throws ClassNotFoundException, SQLException {

		CreateConnection();

		String sql = "SELECT * FROM easylink.Registration WHERE username=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();

		if (rs.next() == true) {

			return new Registration(rs.getString(1), rs.getString(2), rs.getString(3));
		}

		return null;
	}
	
	public Set<URL> getRegistrationLinks(String username) throws ClassNotFoundException, SQLException {

		CreateConnection();
		Set<URL> liste = new HashSet<>();
		
		String sql = "SELECT * FROM easylink.easylink WHERE username=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();

		while (rs.next()) {
			String id = rs.getString(1);
	        String url = rs.getString(2);
			 liste.add(new URL(id, url));
		}

		return liste;
	}
	
	public String getRegistrationLink(String id) throws ClassNotFoundException, SQLException {

		CreateConnection();
		
		String sql = "SELECT * FROM easylink.easylink WHERE id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();

		while (rs.next()) {

	       	return rs.getString(2);
		}

		return "Link not found";
	}

	// Ieliek datubāzē jauno linku.
	public boolean insertRegistration(String username, String password, String email) throws ClassNotFoundException {

		sql = "INSERT INTO easylink.Registration VALUES(?,?,?)";

		try {
			CreateConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);

			int res = ps.executeUpdate();

			if (res == 0) {
				return false;
			}
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Ja atrod duplikatu, tad izmet false.
	public boolean checkDuplicateID(String id) {
		sql = "SELECT id FROM easylink.Registration WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteRegistration(String id) {
		// TODO #7 Write an sql statement that deletes teacher from database. 
		boolean status = false;
		sql = "DELETE FROM easylink.easylink WHERE id = ?";

		int result = 0;
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			result = ps.executeUpdate();
			if (result == 0) {
				return false;
			}
			conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			if (result != 0) {
				status = true;
			}
		}
		return status;
	}
}
