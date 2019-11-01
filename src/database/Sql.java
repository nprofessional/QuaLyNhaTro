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
	public static String insertAccount() {
		String sql = "insert into nnp_account (user_cd, user_nm) values (?, ?)";
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
	public static String updateStatusOfRoom() {
		String sql = "update nnp_room set status = ? where code = ?";
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

	/**
	 * 
	 * @return sql
	 */
	public static String selectRoomCode() {
		String sql = "select code, name from nnp_room order by code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String selectAllRoomPrice() {
		String sql = "select * from nnp_room_price order by room_code asc";
		return sql;
	}

	/**
	 * 
	 * @return sql
	 */
	public static String insertRoomPrice() {
		String sql = "insert into nnp_room_price values(?, ?, ?, ?, ?)";
		return sql;
	}
	

	/**
	 * 
	 * @return sql
	 */
	public static String deleteRoomPrice() {
		String sql = "delete from nnp_room_price where room_code = ? and `from_date` = ? and `to_date` = ?";
		return sql;
	}

	
	 /**
     * 
     * @return sql
     */
    public static String selectCustomer() {
        String sql = "select * from nnp_customer where code = ? ";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String selectAvaliableRoom() {
        String sql ="select nnp_room.code, nnp_room.name, nnp_room.status, nnp_room.type, nnp_room.floor, nnp_room.remark, nnp_room_status.name as status_name, nnp_room_type.name as type_name from nnp_room left join nnp_room_status on nnp_room_status.code = nnp_room.status left join nnp_room_type on nnp_room_type.code = nnp_room.type where nnp_room.status = 'TT003' order by code asc";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String insertContract() {
        String sql ="INSERT INTO nnp_contract (Contract_No, Room_Code, Emp_Id, Cus_Id, Cus_Type, From_Date) VALUES (? , ? , ? , ? , ? , ?)";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String selectAllContract() {
        String sql ="Select\r\n" + 
                "  nnp_contract.Cus_Id\r\n" + 
                "  , nnp_customer.FullName\r\n" + 
                "  , nnp_contract.Cus_Type\r\n" + 
                "  , nnp_contract.Room_Code\r\n" + 
                "  , nnp_room.Name\r\n" + 
                "  , nnp_contract.From_Date \r\n" + 
                "  , nnp_contract.contract_no\r\n"+
                "from\r\n" + 
                "  nnp_contract \r\n" + 
                "  left join nnp_customer \r\n" + 
                "    on nnp_customer.Code = nnp_contract.Cus_Id \r\n" + 
                "  left join nnp_room \r\n" + 
                "    on nnp_room.Code = nnp_contract.Room_Code\r\n" + 
                "";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String selectContract() {
        String sql ="Select\r\n" + 
                "  nnp_contract.Cus_Id\r\n" + 
                "  , nnp_customer.FullName\r\n" + 
                "  , nnp_contract.Cus_Type\r\n" + 
                "  , nnp_contract.Room_Code\r\n" + 
                "  , nnp_room.Name\r\n" + 
                "  , nnp_contract.From_Date \r\n" + 
                "  , nnp_contract.contract_no\r\n"+
                "from\r\n" + 
                "  nnp_contract \r\n" + 
                "  left join nnp_customer \r\n" + 
                "    on nnp_customer.Code = nnp_contract.Cus_Id \r\n" + 
                "  left join nnp_room \r\n" + 
                "    on nnp_room.Code = nnp_contract.Room_Code \r\n" + 
                "where\r\n" + 
                "  nnp_contract.Room_Code = ?";
        return sql;
    }
     
    /**
     * 
     * @return sql
     */
    public static String selectServiceDetail() {
    	String sql = "select nnp_service_detail.contract_no, nnp_service_detail.service_code, nnp_service_detail.emp_code, nnp_service_detail.quantity, nnp_service.name, nnp_service_detail.created_at from nnp_service_detail left join nnp_service on nnp_service_detail.service_code = nnp_service.code where nnp_service_detail.contract_no = ?";
    	return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String insertServiceDetail() {
		String sql = "insert into nnp_service_detail values(?, ?, ?, ?, ?)";
		return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String deleteServiceDetail() {
    	String sql = "delete from nnp_service_detail where contract_no = ?";
    	return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String createInvoice() {
        String sql = "SELECT\r\n" + 
                "  nnp_room.Name\r\n" + 
                "  , nnp_customer.FullName\r\n" + 
                "  , nnp_contract.From_Date \r\n" + 
                "FROM\r\n" + 
                "  nnp_contract \r\n" + 
                "  LEFT JOIN nnp_room \r\n" + 
                "    ON nnp_room.Code = nnp_contract.Room_Code \r\n" + 
                "  LEFT JOIN nnp_customer \r\n" + 
                "    ON nnp_customer.Code = nnp_contract.Cus_Id \r\n" + 
                "WHERE\r\n" + 
                "  nnp_contract.Contract_No = ?";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String selectRoomPrice() {
        String sql = "SELECT\r\n" + 
                "  * \r\n" + 
                "FROM\r\n" + 
                "  nnp_room_price \r\n" + 
                "WHERE\r\n" + 
                "  nnp_room_price.Room_Code = ?";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String getServiceOfContract() {
        String sql = "SELECT\r\n" + 
                "  SUM(nnp_service_detail.Quantity) AS Quantity\r\n" + 
                "  , nnp_service_detail.Service_Code\r\n" + 
                "  , nnp_service.Name\r\n" + 
                "  , nnp_service.Price \r\n" + 
                "FROM\r\n" + 
                "  nnp_service_detail \r\n" + 
                "  LEFT JOIN nnp_service \r\n" + 
                "    ON nnp_service.Code = nnp_service_detail.Service_Code \r\n" + 
                "WHERE\r\n" + 
                "  nnp_service_detail.Contract_No = ? \r\n" + 
                "GROUP BY\r\n" + 
                "  nnp_service_detail.Service_Code\r\n" + 
                "";
        return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String insertInvoice() {
    	String sql = "insert into nnp_invoice(invoice_no, contract_no, amount, totalamount, emp_code) values (?, ?, ?, ?, ?)";
    	return sql;
    }
    
    /**
     * 
     * @return sql
     */
    public static String updateCheckOutForContract() {
    	String sql = "update nnp_contract set To_Date = ? where Contract_No = ?";
    	return sql;
    }
    
}
