package nnp_common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.MySqlDB;
import database.Sql;

public class initForm {
	static Connection conn = null;

	public static Connection nnp_getConnect() throws ClassNotFoundException, SQLException {
		conn = new MySqlDB().getConnection();
		return conn;
	}

	public static ResultSet nnp_getListMessage() throws ClassNotFoundException, SQLException {
		ResultSet listOfMessage = MySqlDB.executeQuery(nnp_getConnect(), Sql.getListMessage());
		return listOfMessage;
	}

	public static String nnp_showMessage(String msg_id) throws ClassNotFoundException, SQLException {
		String messageContent = null;
		ResultSet listMessage = nnp_getListMessage();
		while (listMessage.next()) {
			if (listMessage.getString("msg_id").equals(msg_id)) {
				messageContent = listMessage.getString("msg_content");
				break;
			}
		}
		return messageContent;
	}

}
