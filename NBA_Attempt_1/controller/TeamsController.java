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

public class TeamsController  extends Controller<Teams>
{
@FXML 
private TableView<Team> TeamTv;
@FXML 
private TableColumn<Team, String> teamName;
@FXML 
private TableColumn<Team, Integer> NoOfPlayers;
@FXML 
private TableColumn<Team, Double> AvPlayerCredit;
@FXML 
private TableColumn<Team, Double> AvPlayerAge;
@FXML 
private Button addTeamButton;
@FXML 
private Button manageTeamButton;
@FXML 
private Button deleteTeamButton;
@FXML 
private Button closeButton;

private Team returnSelectedTeam()
{
    return TeamTv.getSelectionModel().getSelectedItem();
}

public Teams getTeams()
{
    return model;
}

@FXML 
private void initialize()
{
    TeamTv.setItems(getTeams().currentTeams());

    teamName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    NoOfPlayers.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asObject());
    AvPlayerCredit.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asObject());
    AvPlayerAge.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asObject());

    teamName.setPrefWidth(195);
    NoOfPlayers.setPrefWidth(195);
    AvPlayerCredit.setPrefWidth(195);
    AvPlayerAge.setPrefWidth(195);

    TeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> deleteTeamButton.setDisable(newSubject == null));
    TeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> manageTeamButton.setDisable(newSubject == null));
    TeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> addTeamButton.setDisable(newSubject != null));
}

    @FXML
    public void close()
    {
        stage.close();
    }

    @FXML
    public void addTeam()
    {
        try
        {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch(IOException ex){
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    public void manageTeam()
    {
        try
        {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(returnSelectedTeam(), "/view/ManageTeamView.fxml", "Managing Team: " + returnSelectedTeam() , stage);
        } catch(IOException ex)
          {
            Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void DeleteTeam()
    {
        Team team = TeamTv.getSelectionModel().getSelectedItem();
        getTeams().remove(team);
        TeamTv.getSelectionModel().clearSelection();
    }
}
