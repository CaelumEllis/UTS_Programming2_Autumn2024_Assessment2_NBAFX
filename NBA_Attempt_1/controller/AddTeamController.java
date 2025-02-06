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

public class AddTeamController extends Controller<Teams>
{
    @FXML 
    private TextField newTeamName;
    @FXML
    private Button addTeamButton;

    public Teams getTeams()
    {
        return this.model;
    }

    public String returnNewTeamName()
    {
        return newTeamName.getText();
    }

    @FXML
    public void addTeam()
    {
        String variable = returnNewTeamName();
        if(getTeams().hasTeam(returnNewTeamName()))
        {
            ErrorController.setErrorMessage(variable + " Team already exists");
            try
            {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(variable, "/view/error.fxml", "Error!", stage);
            } catch(IOException ex){
                Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
              }
        } 
        if(variable.length() >= 1 && !getTeams().hasTeam(returnNewTeamName()))
        {
            Team team = new Team(returnNewTeamName());
                    getTeams().addTeam(team);
                    stage.close(); 
        }
    }
}
