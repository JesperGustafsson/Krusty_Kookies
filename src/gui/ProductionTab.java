package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import datamodel.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;


// controller for both the top tabs and login tab!

public class ProductionTab {
	@FXML private TextField cookieName;
	@FXML private TextField amountOfPallets;
	@FXML private TextField deliveryAddress;
	@FXML private TextField companyName;
	@FXML private TextField deliveryDate;
	@FXML private Text status;


	private SearchTab searchTabCtrl;
	private Database db;
	private ArrayList<String[]> fullOrder = new ArrayList<String[]>();

	@FXML protected void orderButtonAction(ActionEvent event) { // Orderknappen
		try {
			db.orderPallets(fullOrder);
			
			fullOrder.clear();
			status.setText("Order sent");

			cookieName.clear();
			amountOfPallets.clear();
			deliveryAddress.clear();
			companyName.clear();
			deliveryDate.clear();
			
		} catch (SQLException e) {
			status.setText(e.getMessage());
//			e.printStackTrace();
		}		
	}
	
	@FXML protected void addToCartAction(ActionEvent event) {
	
		ArrayList<String[]> tempFullOrder = new ArrayList<String[]>();
		String[] tempOrder = new String[5];
		
		for (int i = 0; i < fullOrder.size(); i++) {
			for (int j = 0; j < fullOrder.get(i).length; j++) {
				tempOrder[j] = fullOrder.get(i)[j];				
			}
			tempFullOrder.add(tempOrder);
			System.out.println("TEST: " + tempFullOrder.get(i)[0]);
		}
		
		String[] order = new String[5];
		order[0] = cookieName.getText();
		order[1] = amountOfPallets.getText();
		order[2] = deliveryAddress.getText();
		order[3] = companyName.getText();
		order[4] = deliveryDate.getText();
		
		try {
			tempFullOrder.add(order);
			System.out.println(tempFullOrder.get(tempFullOrder.size()-1)[0]);
			db.checkInput(order[0], order[3]);
			db.checkIngredients(tempFullOrder);	
			fullOrder.add(order);
			status.setText(status.getText() + "\n" + amountOfPallets.getText() + " pallet(s) of " + cookieName.getText() + " added to order.");
			cookieName.setText("");
			amountOfPallets.clear();

		} catch (SQLException e) {
			status.setText(status.getText() + "\n" + e.getMessage());
		}

		
		

	}

	public void initialize() {
		System.out.println("Initializing LoginTab.");    	
	}

	// helpers
	// use this pattern to send data down to controllers at initialization
	public void setSearchTab(SearchTab searchTabCtrl) {
		this.searchTabCtrl = searchTabCtrl; 	
	}

	public void setDatabase(Database db) {
		this.db = db;
	}

}