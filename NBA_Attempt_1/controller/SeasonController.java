package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import model.Season;


public class SeasonController extends Controller<Season>
{

    @FXML
    private Button viewSeasonRoundButton;
    @FXML
    private Button viewRoundTeamsButton;
    @FXML
    private Button GameButton;
    @FXML
    private Button displayResultRecordButton;
    @FXML
    private Button closeButton;

    public Season getSeason()
    {
        return this.model;
    }

    @FXML
    public void close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void viewSeasonRound()
    {
        try{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch(IOException ex){
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    @FXML
    public void viewRoundTeams()
    {
        try{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch(IOException ex){
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    public void game()
    {
        String variable = getSeason().playGame();
        ErrorController.setErrorMessage(variable);
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(variable, "/view/error.fxml", "All Games Played!", stage);
        } catch(IOException ex){
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    public void displayResultRecord()
    {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
        } catch(IOException ex){
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}

