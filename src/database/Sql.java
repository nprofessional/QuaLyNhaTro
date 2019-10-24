package database;

public class Sql {

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllService() {
		String sql = "select * from service order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertService() {
		String sql = "insert into service values (?, ?, ?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateService() {
		String sql = "update service set name = ?, unit = ?, price = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteService() {
		String sql = "delete from service where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoomStatus() {
		String sql = "select * from room_status order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertRoomStatus() {
		String sql = "insert into room_status values (?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateRoomStatus() {
		String sql = "update room_status set name = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoomStatus() {
		String sql = "delete from room_status where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoomType() {
		String sql = "select * from room_type order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertRoomType() {
		String sql = "insert into room_type values (?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateRoomType() {
		String sql = "update room_type set name = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoomType() {
		String sql = "delete from room_type where code = ?";
		return sql;
	}

}
