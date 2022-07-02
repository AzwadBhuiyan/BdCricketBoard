/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Classes.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ApplyLeaveController implements Initializable {

    @FXML
    private TextField subject;
    @FXML
    private TextArea body;
    @FXML
    private Label confirmation;

    private Player thisUser;
    public void setUser(Player u) {
        this.thisUser = u;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        PlayerFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void applyOnClick(ActionEvent event) {
        Player p = new Player();
        if (subject.getText().isEmpty() != true && body.getText().isEmpty() != true) {
        p.applyLeave(thisUser.id, subject.getText(), body.getText());
        confirmation.setText("Submitted Successfully");
        }
        else{
            confirmation.setText("Please fill up the fields above");
        }
    }

}
