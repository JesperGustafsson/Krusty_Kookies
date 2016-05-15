package datamodel;

import java.util.ArrayList;

public class Order extends ArrayList<Pallet> {

	private String deliveryAddress;
	
	public Order() {
		
	}
	
	
	public boolean add(Pallet pallet) {
		return this.add(pallet);
	}
	
	public void changeDeliveryAddress(String address) {
		deliveryAddress = address;
	}
}
