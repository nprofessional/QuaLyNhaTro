package gui;

import database.MySqlDB;
import database.Sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

	/**
	 * Main
	 *
	 * @param __
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] __) throws ClassNotFoundException, SQLException {

		// get connection
		Connection conn = new MySqlDB().getConnection();

		// insert a row into service table
		MySqlDB.executeUpdate(conn, Sql.insertService(), new String[] { "17", "a", "b", "8000" });

		// query all fields code of service table
		ResultSet service = MySqlDB.executeQuery(conn, Sql.selectAllService());
		while (service.next()) {
			System.out.println("code: " + service.getString("code"));
		}

		// close connection
		conn.close();
	}
}
