package database;

import helper.Container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDB {

	/**
	 *
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + Container.HOSTNAME + ":3306/" + Container.DATABASE
				+ "?autoReconnect=true&useSSL=false";
		Connection conn = DriverManager.getConnection(url, Container.USERNAME, Container.PASSWORD);
		return conn;
	}

	/**
	 *
	 * @param conn
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(Connection conn, String sql) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}

	/**
	 *
	 * @param conn
	 * @param sql
	 * @param params
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(Connection conn, String sql, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 1; i <= params.length; i++) {
			ps.setObject(i, params[i - 1]);
		}
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	/**
	 *
	 * @param conn
	 * @param sql
	 * @throws SQLException
	 */
	public static void executeUpdate(Connection conn, String sql) throws SQLException {
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
	}

	/**
	 *
	 * @param conn
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public static void executeUpdate(Connection conn, String sql, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 1; i <= params.length; i++) {
			ps.setObject(i, params[i - 1]);
		}
		ps.executeUpdate();
	}
}
