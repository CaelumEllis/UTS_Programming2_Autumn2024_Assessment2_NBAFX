package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import model.Season;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Record;


public class RecordController extends Controller<Season>
{
    @FXML 
    private TableView<Record> recordTable;
    @FXML 
    private TableColumn<Record, Integer> roundColumn;
    @FXML 
    private TableColumn<Record, Integer> gameColumn;
    @FXML 
    private TableColumn<Record, String> winningTeamColumn;
    @FXML 
    private TableColumn<Record, String> losingTeamColumn;
    @FXML 
    private Button closeButton;

    private Season getSeason()
    {
        return this.model;
    }

    @FXML 
    private void initialize()
    {
        recordTable.setItems(getSeason().record());

        roundColumn.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asObject());
        gameColumn.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asObject());
        winningTeamColumn.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        losingTeamColumn.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());
        
        roundColumn.setPrefWidth(120);
        gameColumn.setPrefWidth(120);
        winningTeamColumn.setPrefWidth(120);
        losingTeamColumn.setPrefWidth(120);
    }

    @FXML 
    private void close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}





