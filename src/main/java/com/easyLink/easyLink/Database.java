package com.easyLink.easyLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	protected Connection conn;

	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root",
					"abcd1234");

			this.conn.setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public LinkObject getLinkObject(String id) {
		try {
			String sql = "SELECT * FROM database_activity.Teacher WHERE id = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				return new LinkObject(rs.getString(1), rs.getString(2));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return new LinkObject(null, null);
	}

	public boolean insertLinkObject(String id, String URL) {
		String sql = " INSERT INTO database_activity.Teacher (firstName, lastName) values (?, ?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, id);
			preparedStatement.setString(2, URL);

			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean insertLinkObject(LinkObject link) {

		String sql = "INSERT INTO database_activity.Teacher (id, firstName) VALUES (?, ?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, link.getId());
			preparedStatement.setString(2, link.getURL());

			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void closeConnecion() {
		// TODO Close connection if and reset it to release connection to the
		// database server
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
