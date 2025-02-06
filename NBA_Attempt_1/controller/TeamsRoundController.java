package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Season;
import model.Team;
import model.Game;

public class TeamsRoundController extends Controller<Season>
{
    @FXML 
    private Button arrangeSeasonButton; 
    @FXML 
    private Button addTeamToSeasonButton;
    @FXML 
    private TableView<Game> seasonTv;
    @FXML 
    private TableColumn<Game, Integer> gameTableColumn;
    @FXML 
    private TableColumn<Game, String> team1TableColumn;
    @FXML 
    private TableColumn<Game, String> team2TableColumn;
    @FXML 
    private ListView<Team> teamListView;
    @FXML 
    private Label roundLbl;

    private Season getSeason()
    {
        return this.model;
    }

    @FXML private void initialize()
    {
        roundLbl.setText("Round: " + (getSeason().round() + 1));

        
        teamListView.setItems(getSeason().getCurrentTeams());
        seasonTv.setItems(getSeason().getCurrentSchedule());

        gameTableColumn.setCellValueFactory(cellData -> cellData.getValue().termProperty().asObject());
        team1TableColumn.setCellValueFactory(cellData -> cellData.getValue().team1());
        team2TableColumn.setCellValueFactory(cellData -> cellData.getValue().team2());

        gameTableColumn.setPrefWidth(80);
        team1TableColumn.setPrefWidth(110);
        team2TableColumn.setPrefWidth(110);

        teamListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teamListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Team>) change -> 
        {
            ObservableList<Team> selectedItems = teamListView.getSelectionModel().getSelectedItems();
            addTeamToSeasonButton.setDisable(selectedItems.size() != 2);
        });

        teamListView.getItems().addListener((ListChangeListener<Team>) change -> 
        {
            boolean isEmpty = teamListView.getItems().isEmpty();
            arrangeSeasonButton.setDisable(!isEmpty);
        });
    }

    @FXML 
    private void addTeamToSeason()
    {
        ObservableList<Team> selectedTeams = teamListView.getSelectionModel().getSelectedItems();
        if(selectedTeams.size() == 2)
        {
            getSeason().addTeams(selectedTeams);
        }
    }

    @FXML 
    private void arrangeSeason()
    {
        stage.close();
    }
}
