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
	@FXML private TextField cType;
	@FXML private TextField pAmount;
	@FXML private TextField dAddress;


	private BookingTab bookingTabCtrl;
	private Database db;

	@FXML protected void orderButtonAction(ActionEvent event) { // Orderknappen

		if(!db.isConnected()) {
			// inform the user that there is no check against the database
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login fail");
			alert.setHeaderText(null);
			alert.setContentText("No database connection! Cannot check user credentials.");
			alert.showAndWait();      	
		} else {
			String cookieType = cType.getText();
			String palletAmount = pAmount.getText();
			String deliveryAddress = dAddress.getText();
			System.out.println(cookieType + "  " + palletAmount + "  " + deliveryAddress);

			
			db.orderPallet(cookieType, palletAmount, deliveryAddress);

		}
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