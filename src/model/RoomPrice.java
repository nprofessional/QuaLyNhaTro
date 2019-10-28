package model;

public class RoomPrice {
	private String roomCode;
	private long roomPrice;
	private String from;
	private String to;
	private int discountPercent;
	
	/**
	 * 
	 * @param roomCode
	 * @param roomPrice
	 * @param from
	 * @param to
	 * @param discountPercent
	 */
	public RoomPrice(String roomCode, long roomPrice, String from, String to, int discountPercent) {
		super();
		this.roomCode = roomCode;
		this.roomPrice = roomPrice;
		this.from = from;
		this.to = to;
		this.discountPercent = discountPercent;
	}

	/**
	 * 
	 * @return roomCode
	 */
	public String getRoomCode() {
		return roomCode;
	}

	/**
	 * 
	 * @param roomCode
	 */
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	/**
	 * 
	 * @return roomPrice
	 */
	public long getRoomPrice() {
		return roomPrice;
	}

	/**
	 * 
	 * @param roomPrice
	 */
	public void setRoomPrice(long roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * 
	 * @return from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 
	 * @return to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * 
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 
	 * @return discountPercent
	 */
	public int getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * 
	 * @param discountPercent
	 */
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	
}
