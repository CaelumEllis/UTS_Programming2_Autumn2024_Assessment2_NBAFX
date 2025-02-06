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
import model.*;

public class ExploreTeamsController extends Controller<Teams>
{

    @FXML
    private Button teamsMenuButton;
    @FXML
    private Button viewPlayersButton;
    @FXML
    private Button closeButton;

    public Teams getTeams()
    {
        return this.model;
    }

    @FXML
    public void teamsMenu()
    {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/TeamsTable.fxml", "Teams Menu", stage);
        } catch(IOException ex){
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    public void close()
    {
        stage.close();
    }

    @FXML
    public void viewPlayers()
    {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "Players", stage);
        } catch(IOException ex){
            Logger.getLogger(ViewPlayersController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }   
}