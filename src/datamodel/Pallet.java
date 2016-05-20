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


	public String getType() { return cookieName; }
	
	public String getAddress() { return deliveryAddress; }

}
