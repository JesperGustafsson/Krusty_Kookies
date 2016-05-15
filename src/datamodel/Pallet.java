package datamodel;

// Container for the database data
/* --- TODO: Modify as needed --- */

public class Pallet {
	String cookieType;
	String deliveryAddress;

	public Pallet(String cookieType, String deliveryAddress) {
		this.cookieType = cookieType;
		this.deliveryAddress = deliveryAddress;
	}


	public String getType() { return cookieType; }
	
	public String getAddress() { return deliveryAddress; }

}
