package gui;

import java.util.ArrayList;

import datamodel.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class dateSearchTab {

	
	@FXML
	private TextField dateStart;
	@FXML
	private TextField dateEnd;
	
	@FXML
	private Text status;
	
	private Database db;
	private ArrayList cookieList;
	
	
	public void initialize() {
		System.out.println("Initializing dateSearchTab");
	}
	
	public void searchButtonAction() {
		String dS = dateStart.getText();
		String dE = dateEnd.getText();
		System.out.println("dateStart: " + dS);
		System.out.println("dateEnd: " + dE);
		cookieList = db.getNbrOfPallets(dS, dE);

		for (int i = 0; i < cookieList.size(); i++) {
//			System.out.print(cookieList.get(i) + " :: ");
//			System.out.println(cookieList.get(++i));

			status.setText(status.getText() + cookieList.get(i) + ": " + cookieList.get(++i) + " pallets\n");
			
		}
		

	}
	
	public void setDatabase(Database db) {
		this.db = db;
	}
}
