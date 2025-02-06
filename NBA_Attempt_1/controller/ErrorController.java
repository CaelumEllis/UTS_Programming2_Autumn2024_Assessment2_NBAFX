package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ErrorController extends Controller<Object>
{
    @FXML 
    private Button closeButton;
    @FXML 
    private Text errorMsgText;

    private static String currentMessage = "";
    
    @FXML 
    private void initialize() 
    {
        setErrorMessageText();
    }

    @FXML private void close()
    {
        stage.close();
    }

    public static void setErrorMessage(String message)
    {
        currentMessage = message;
    }

    public void setMessage(String message)
    {
        errorMsgText.setText(message);
    }

    private void setErrorMessageText()
    {
        errorMsgText.setText(currentMessage);
    }
}
