package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Team;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageTeamController extends Controller<Team>
{
    @FXML 
    private TableView<Player> specificTeamTv;
    @FXML 
    private TableColumn<Player, String> playerName;
    @FXML 
    private TableColumn<Player, Double> playerCredit;
    @FXML 
    private TableColumn<Player, Integer> playerAge;
    @FXML 
    private TableColumn<Player, Integer> playerNumber;
    @FXML 
    private Button addPlayerButton;
    @FXML 
    private Button updatePlayerButton;
    @FXML 
    private Button deletePlayerButton;
    @FXML 
    private Button saveCloseButton;
    @FXML 
    private TextField managedTeamNameTextField;

    private String originalTeamName;

    public Team getTeam()
    {
        return this.model;
    }

    private Player returnSelectedPlayer()
    {
        return specificTeamTv.getSelectionModel().getSelectedItem();
    }

    @FXML private void initialize()
    {
        managedTeamNameTextField.setText(getTeam().getName());

        specificTeamTv.setItems(getTeam().getCurrentPlayers());

        playerName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCredit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asObject());
        playerAge.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asObject());
        playerNumber.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());

        playerName.setPrefWidth(195);
        playerCredit.setPrefWidth(195);
        playerAge.setPrefWidth(195);
        playerNumber.setPrefWidth(195);

        specificTeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> addPlayerButton.setDisable(newSubject != null));
        specificTeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> deletePlayerButton.setDisable(deletePlayerButton == null));
        specificTeamTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> updatePlayerButton.setDisable(newSubject == null));

        originalTeamName = getTeam().getName();
    }

    @FXML public void AddPlayer()
    {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            Player newPlayer = new Player("", 0.0, 0, 0);
            ViewLoader.showStage(newPlayer, "/view/PlayerUpdateView.fxml", "Adding New Player", stage);

            stage.setOnHiding(event -> {
                if(!newPlayer.getName().isEmpty())
                {
                    getTeam().getPlayers().addPlayer(newPlayer);
                    specificTeamTv.setItems(getTeam().getCurrentPlayers());
                }
            });
        } catch(IOException ex){
            Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML 
    public void UpdatePlayer()
    {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(returnSelectedPlayer(), "/view/PlayerUpdateView.fxml", "Updating Player", stage);
        } catch(IOException ex){
            Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML 
    public void DeletePlayer()
    {
        Player player = specificTeamTv.getSelectionModel().getSelectedItem();
        getTeam().getPlayers().removePlayer(player);
        specificTeamTv.getSelectionModel().clearSelection();
    }

    @FXML 
    public void SaveClose()
    {
        String newName = managedTeamNameTextField.getText();
        if(newName.equals(originalTeamName))
        {
            stage.close();
        } else{
            getTeam().setName(newName);
          }
    }
}
