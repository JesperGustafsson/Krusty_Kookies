package gui;

import datamodel.CurrentUser;
import datamodel.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

// controller for both the top tabs and login tab!

public class LoginTab {
	@FXML private Text actiontarget;
	@FXML private TextField cookieName;
	@FXML private TextField amountOfPallets;
	@FXML private TextField deliveryAddress;


	private BookingTab bookingTabCtrl;
	private Database db;

@FXML protected void orderButtonAction(ActionEvent event) { // Orderknappen

	db.orderPallets(cookieName.getText(), amountOfPallets.getText(), deliveryAddress.getText());
	
	}

public void initialize() {
	System.out.println("Initializing LoginTab.");    	
}

// helpers
// use this pattern to send data down to controllers at initialization
public void setBookingTab(BookingTab bookingTabCtrl) {
	System.out.println("LoginTab sets bookingTab:"+bookingTabCtrl);
	this.bookingTabCtrl = bookingTabCtrl; 	
}

public void setDatabase(Database db) {
	this.db = db;
}

}