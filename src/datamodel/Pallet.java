package datamodel;

// Container for the database data
/* --- TODO: Modify as needed --- */

public class Pallet {
	String orderNbr;
	String cookieName;
	String barCode;
	boolean blockStatus;
	String location;
	
	String deliveryAddress;


	public Pallet(String orderNbr, String cookieName, String barCode, boolean blockStatus, String location) {
		this.orderNbr = orderNbr;
		this.cookieName = cookieName;
		this.barCode = barCode;
		this.blockStatus = blockStatus;
		this.location = location;
	}


	public String getOrderNumber() {
		return orderNbr;
	}


	public String getCookieName() {
		return cookieName;
	}


	public String getBarCode() {
		return barCode;
	}


	public String getBlockStatus() {
		return Boolean.toString(blockStatus);
	}


	public String getLocation() {
		return location;
	}

}
