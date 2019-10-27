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
        String sql = "update nnp_customer set fullname = ?, birth = ?, gender = ?, phone = ?,  identity_card = ?, address = ? where code = ?";
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
}
