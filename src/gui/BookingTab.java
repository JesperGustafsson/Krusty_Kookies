package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import datamodel.CurrentUser;
import datamodel.Database;
import datamodel.Show;

import java.util.ArrayList;

public class BookingTab {
	// top context message
	@FXML
	private Text topContext;
	// bottom message
	@FXML
	private Text bookMsg;

	// table references
//	@FXML
//	private ListView<String> palletList;
//	@FXML
//	private ListView<String> datesList;

	// show info references
	@FXML
	private Label showTitle;
	@FXML
	private Label showDate;
	@FXML
	private Label showVenue;
	@FXML
	private Label showFreeSeats;

	// booking button
	@FXML
	private Button bookTicket;

	private Database db;
	private Connection conn;
	private Show crtShow = new Show();

	public void initialize() {
		System.out.println("Initializing BookingTab");

//		fillNamesList();
//		fillDatesList(null);
//		fillShow(null, null);

		// set up listeners for the movie list selection
/*		palletList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
			// need to update the date list according to the selected movie
			// update also the details on the right panel
			String movie = newV;
//			fillDatesList(newV);
			fillShow(movie, null);
		});

/*		// set up listeners for the date list selection
		datesList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
			// need to update the details according to the selected date
			String movie = palletList.getSelectionModel().getSelectedItem();
			String date = newV;
			fillShow(movie, date);
		});
*/
		// set up booking button listener
		// one can either use this method (setup a handler in initialize)
/*		// or directly give a handler name in the fxml, as in the LoginTab class
		bookTicket.setOnAction((event) -> {
			String movie = palletList.getSelectionModel().getSelectedItem();
	//		String date = datesList.getSelectionModel().getSelectedItem();
			String name = CurrentUser.instance().getCurrentUserId();
*/			/* --- TODO: should attempt to book a ticket via the database --- */
/*
			if (db != null) {
				if (db.bookShow(name, date, movie)) {
					int refNbr = db.getRefNbr(name);
					report("Booked one ticket to " + movie + " on " + date + "\n" + "Reference number: " + refNbr);
					fillShow(movie,date);
					
				} else {
					report("No seats left");
				}
			}
		});

		report("Ready.");
	*/
	}

	// helpers
	// updates user display
	private void fillStatus(String usr) {
		if (usr.isEmpty())
			topContext.setText("You must log in as a known user!");
		else
			topContext.setText("Currently logged in as " + usr);
	}

	private void report(String msg) {
		bookMsg.setText(msg);
	}

	public void setDatabase(Database db) {
		this.db = db;
	}

//		datesList.setItems(FXCollections.observableList(alldates));
		// remove any selection
//		datesList.getSelectionModel().clearSelection();

	// called in case the user logged in changed


}
