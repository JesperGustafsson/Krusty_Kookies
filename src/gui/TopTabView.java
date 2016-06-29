package gui;

import javafx.scene.Parent;
import datamodel.Database;
import javafx.fxml.FXML;

public class TopTabView {
	@FXML private Parent aProductionTab;
	@FXML private ProductionTab aProductionTabController;

	@FXML private Parent aSearchTab;
	@FXML private SearchTab aSearchTabController;
	
	@FXML private Parent aDateSearchTab;
	@FXML private dateSearchTab aDateSearchTabController;
	
	
	public void initialize() {
		System.out.println("Initializing TopTabView");
		
		// send the booking controller reference to the login controller
		// in order to pass data between the two
		aProductionTabController.setSearchTab(aSearchTabController);
	}
	
	public void setDatabase(Database db) {
		aProductionTabController.setDatabase(db);
		aSearchTabController.setDatabase(db);
		aDateSearchTabController.setDatabase(db);
		
	}
}
