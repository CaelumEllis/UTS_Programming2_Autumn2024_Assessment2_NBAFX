package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerUpdateController extends Controller<Player>
{
    @FXML 
    private TextField playernametTextField;
    @FXML
    private TextField playerCreditTextField;
    @FXML 
    private TextField playerAgeTextField;
    @FXML 
    private TextField playerNoTextField;
    @FXML 
    private Button updateButton;
    @FXML private Button addButton;
    @FXML private Button closeButton;

    private Validator validator = new Validator();

    public Player getPlayer() {
        return this.model;
    }

    @FXML private void initialize() {
        if (getPlayer().getName().isEmpty()) {
            updateButton.setDisable(true);
            addButton.setDisable(false);
        } else {
            updateButton.setDisable(false);
            addButton.setDisable(true);
            setPlayerDetails();
        }
    }

    private void setPlayerDetails() {
        playernametTextField.setText(getPlayer().getName());
        playerCreditTextField.setText(String.valueOf(getPlayer().getCredit()));
        playerAgeTextField.setText(String.valueOf(getPlayer().getAge()));
        playerNoTextField.setText(String.valueOf(getPlayer().getNo()));
    }

    @FXML public void updatePlayer() {
        String name = playernametTextField.getText();
        String credit = playerCreditTextField.getText();
        String age = playerAgeTextField.getText();
        String no = playerNoTextField.getText();
        if (validator.isValid(name, credit, age, no)) {
            Player player = getPlayer();
            player.setName(name);
            player.setCredit(Double.parseDouble(credit));
            player.setAge(Integer.parseInt(age));
            player.setNo(Integer.parseInt(no));
            stage.close();
        } else {
            validator.generateErrors(name, credit, age, no);
            String message = validator.getFormattedErrors();
            ErrorController.setErrorMessage(message);
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(message, "/view/error.fxml", "Input Errors", stage);
            } catch (IOException ex) {
                Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        validator.clear();
    }

    @FXML public void addPlayer() {
        String name = playernametTextField.getText();
        String credit = playerCreditTextField.getText();
        String age = playerAgeTextField.getText();
        String no = playerNoTextField.getText();
        if (validator.isValid(name, credit, age, no)) {
            Player player = getPlayer();
            player.setName(name);
            player.setCredit(Double.parseDouble(credit));
            player.setAge(Integer.parseInt(age));
            player.setNo(Integer.parseInt(no));
            stage.close();
        } else {
            validator.generateErrors(name, credit, age, no);
            String message = validator.getFormattedErrors();
            ErrorController.setErrorMessage(message);
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(message, "/view/error.fxml", "Input Errors", stage);
            } catch (IOException ex) {
                Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        validator.clear();
    }

    @FXML public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
