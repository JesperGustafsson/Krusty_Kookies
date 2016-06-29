package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;


import java.sql.SQLException;



import datamodel.Database;
import datamodel.Pallet;


public class SearchTab {

	// show info references	
	@FXML
	private Label orderNumber;
	@FXML
	private Label cookieName;
	@FXML
	private Label barCode;
	@FXML
	private Label blockStatus;
	@FXML
	private Label currentLocation;
	@FXML
	private TextField searchID;
	@FXML private Text status;
	
	private Pallet currentPallet;

	private Database db;

	public void initialize() {
		System.out.println("Initializing BookingTab");
	}

	@FXML protected void searchButtonAction(ActionEvent event) throws SQLException { // Search button
		String barCode = searchID.getText();
		Pallet searchedPallet = db.getPallet(barCode);
		if (searchedPallet == null) status.setText("A pallet with the barcode " + barCode + " does not exist.");
		else {
			currentPallet = searchedPallet;
			status.setText("Now showing the pallet with the barcode " + currentPallet.getBarCode());
			updateView();
		}
	}
	
	private void updateView() {		
		orderNumber.setText(currentPallet.getOrderNumber());
		cookieName.setText(currentPallet.getCookieName());
		barCode.setText(currentPallet.getBarCode());
		blockStatus.setText(currentPallet.getBlockStatus());
		currentLocation.setText(currentPallet.getLocation());
	}
	
	@FXML protected void blockButtonAction(ActionEvent event) { // Block button

		if (currentPallet == null) {
			status.setText("Search for a pallet before trying to block");
		} else {
		db.blockPallet(currentPallet.getBarCode());
		currentPallet.setBlockStatus(true);
		status.setText("Pallet " + currentPallet.getBarCode() + " has been blocked.");
		updateView();	
		}
	}
	
	public void setDatabase(Database db) {
		this.db = db;
	}


}
