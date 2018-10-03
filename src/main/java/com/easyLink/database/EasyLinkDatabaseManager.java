package com.easyLink.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.easyLink.links.URL;

/**
 * This class create connection with databse and provide methods to work with it.
 * In fields USERNAME, PASSWORD and CONN_STRING put corresponding information about database maintenance tool you are working with.
 * And if connection with database established,
 *  - method getAllLinks() return all records from database
 *  - method getLink(*id*) return full url address
 *  - method
 *
 * @author Kristaps, Raivis, Martins, Arturs
 */

@Service
public class EasyLinkDatabaseManager {
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
	public List<URL> getAllLinks() throws ClassNotFoundException, SQLException {

		CreateConnection();

		List<URL> liste = new ArrayList<URL>();
		String sql = "SELECT * FROM easylink.easylink";

		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {

			link = new URL(rs.getString(1), rs.getString(2));
			liste.add(link);
		}

		return liste;
	}

	// Atrod linku pēc id(saīsinātā nosaukuma), ja neatrod, tad atgriež tukšu linku
	public String getLink(String id) throws ClassNotFoundException, SQLException {

		CreateConnection();

		String sql = "SELECT * FROM easylink.easylink WHERE id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();

		if (rs.next() == true) {

			return rs.getString(2);
		}

		return "You`re not lucky. No link found for such Id :(";
	}

	// Ieliek datubāzē jauno linku.
	public boolean insertLink(String id, String full_url) throws ClassNotFoundException {

		sql = "INSERT INTO easylink.easylink VALUES(?,?,?)";

		try {
			CreateConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, full_url);
			ps.setString(3,  null);

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
	
	public boolean insertLink(String id, String full_url, String username) throws ClassNotFoundException {

		sql = "INSERT INTO easylink.easylink VALUES(?,?,?)";

		try {
			CreateConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, full_url);
			ps.setString(3, username);

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
		sql = "SELECT id FROM easylink.easylink WHERE id=?";
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

	public boolean deleteLink(String id) {
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