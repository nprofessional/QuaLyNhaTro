package database;

public class Sql {

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllService() {
		String sql = "select * from nnp_service order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertService() {
		String sql = "insert into nnp_service values (?, ?, ?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateService() {
		String sql = "update nnp_service set name = ?, unit = ?, price = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteService() {
		String sql = "delete from nnp_service where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoomStatus() {
		String sql = "select * from nnp_room_status order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertRoomStatus() {
		String sql = "insert into nnp_room_status values (?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateRoomStatus() {
		String sql = "update nnp_room_status set name = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoomStatus() {
		String sql = "delete from nnp_room_status where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoomType() {
		String sql = "select * from nnp_room_type order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertRoomType() {
		String sql = "insert into nnp_room_type values (?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateRoomType() {
		String sql = "update nnp_room_type set name = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoomType() {
		String sql = "delete from nnp_room_type where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllCustomer() {
		String sql = "select code, fullname, birth, if(gender = 0, 'Nam', 'Nữ') as gender, phone, identity_card, address from nnp_customer order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertCustomer() {
		String sql = "insert into nnp_customer values (?, ?, ?, ?, ?, ?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateCustomer() {
		String sql = "update nnp_customer set fullname = ?, birth = ?, gender = ?, phone = ?, identity_card = ?, address = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteCustomer() {
		String sql = "delete from nnp_customer where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String login() {
		String sql = "select * from nnp_account where user_cd = ? and password = ? and del_flg = 0";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String getListMessage() {
		String sql = "select * from nnp_message where del_flg = 0";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllEmployee() {
		String sql = "select code, fullname, birth, email, if(status = 0, 'Inactive', 'Active') as status, phone, identity_card, address from nnp_employee order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertEmployee() {
		String sql = "insert into nnp_employee values (?, ?, ?, ?, ?, ?, ?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateEmployee() {
		String sql = "update nnp_employee set status = ?, fullname = ?, identity_card = ?, address = ?, phone = ?, email = ?, birth = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteEmployee() {
		String sql = "delete from nnp_employee where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoom() {
		String sql = "select nnp_room.code, nnp_room.name, nnp_room.status, nnp_room.type, nnp_room.floor, nnp_room.remark, nnp_room_status.name as status_name, nnp_room_type.name as type_name from nnp_room left join nnp_room_status on nnp_room_status.code = nnp_room.status left join nnp_room_type on nnp_room_type.code = nnp_room.type order by code asc";
		return sql;
	}
	
	/**
	 * 
	 * @return sql
	 */
	public static String insertRoom() {
		String sql = "insert into nnp_room values (?, ?, ?, ?, ?, ?)";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String updateRoom() {
		String sql = "update nnp_room set name = ?, status = ?, type = ?, floor = ?, remark = ? where code = ?";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoom() {
		String sql = "delete from nnp_room where code = ?";
		return sql;
	}

}
