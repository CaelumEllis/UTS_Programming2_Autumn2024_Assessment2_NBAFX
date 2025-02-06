package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class ViewPlayersController extends Controller<Teams>
{
    @FXML 
    private TableView<Player> playerTv;
    @FXML 
    private TableColumn<Player, String> playerTeamName;
    @FXML 
    private TableColumn<Player, String> playerName;
    @FXML 
    private TableColumn<Player, Double> playerCredit;
    @FXML 
    private TableColumn<Player, Integer> playerAge;
    @FXML 
    private TableColumn<Player, Integer> playerNo;
    @FXML 
    private TableColumn<Player, String> playerLevel;

    @FXML 
    private TextField levelFilterTextField;
    @FXML 
    private TextField nameFilterTextField;
    @FXML 
    private TextField fromAgeFilterTextField;
    @FXML 
    private TextField toAgeFilterTextField;
    @FXML

    private Button closeButton;

    private ObservableList<Player> allPlayers;
    private ObservableList<Player> filteredPlayers;

    public Teams getPlayers()
    {
        return this.model;
    }
    
    @FXML 
    private void initialize()
    {
    allPlayers = getPlayers().allPlayersList();
    filteredPlayers = FXCollections.observableArrayList(allPlayers);
    playerTv.setItems( filteredPlayers);

    playerTeamName.setCellValueFactory(cellData -> cellData.getValue().getTeamNameProperty());
    playerName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    playerCredit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asObject());
    playerAge.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asObject());
    playerNo.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());
    playerLevel.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
    
    playerTeamName.setPrefWidth(150);
    playerName.setPrefWidth(150);
    playerCredit.setPrefWidth(150);
    playerAge.setPrefWidth(150);
    playerNo.setPrefWidth(150);
    playerLevel.setPrefWidth(150);

    nameFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    levelFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    fromAgeFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    toAgeFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    }

    private void filterPlayers()
    {
        String name = nameFilterTextField.getText().trim().toLowerCase();
        String level = levelFilterTextField.getText().trim().toLowerCase();
        
        int ageFrom;
        if(fromAgeFilterTextField.getText().isEmpty())
        {    
            ageFrom = Integer.MIN_VALUE;
        } else{
            ageFrom = Integer.parseInt(fromAgeFilterTextField.getText());
          }

        int ageTo;
        if(toAgeFilterTextField.getText().isEmpty())
        {
            ageTo = Integer.MAX_VALUE;
        } else{
            ageTo = Integer.parseInt(toAgeFilterTextField.getText());
          }

        filteredPlayers.setAll(allPlayers.filtered(player -> 
            (name.isEmpty() || player.getName().toLowerCase().contains(name) 
            || player.getTeamName().toLowerCase().contains(name)) && (level.isEmpty() 
            || player.getLevel().toLowerCase().contains(level)) &&
            player.getAge() >= ageFrom && player.getAge() <= ageTo));
    }

    

    @FXML
    public void close(){
        stage.close();
    }
}

