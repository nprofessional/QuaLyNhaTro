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
}
