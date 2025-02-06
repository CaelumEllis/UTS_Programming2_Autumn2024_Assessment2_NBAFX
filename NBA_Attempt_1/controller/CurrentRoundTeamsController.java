package controller;

import javafx.fxml.FXML;
import model.Game;
import model.Season;
import au.edu.uts.ap.javafx.Controller;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

public class CurrentRoundTeamsController extends Controller<Season>
{
    @FXML 
    private Button closeButton;
    @FXML 
    private TableView<Game> seasonTv;
    @FXML 
    private TableColumn<Game, String> team1TableColumn;
    @FXML 
    private TableColumn<Game, String> vsTableColumn;
    @FXML 
    private TableColumn<Game, String> team2TableColumn;
    @FXML 
    private Label roundLbl;

    private Season getSeason()
    {
        return this.model;
    }

    @FXML 
    private void initialize()
    {
        roundLbl.setText("Round: " + (getSeason().round() + 1));
        seasonTv.setItems(getSeason().getCurrentSchedule());
        
        team1TableColumn.setCellValueFactory(cellData -> cellData.getValue().team1());
        vsTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty("VS"));
        team2TableColumn.setCellValueFactory(cellData -> cellData.getValue().team2());

        vsTableColumn.setPrefWidth(90);
        team1TableColumn.setPrefWidth(90);
        team2TableColumn.setPrefWidth(90);
    }

    @FXML 
    private void close()
    {
        stage.close();
    }
}








