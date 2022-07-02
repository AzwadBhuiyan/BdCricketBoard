/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamCoach;

import Classes.TeamCoach;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ChangePasswordController implements Initializable {

    private TeamCoach thisUser;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    @FXML
    private Label confirmation;

    public void setUser(TeamCoach u) {
        this.thisUser = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TeamCoachFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        TeamCoachFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void confirmOnClick(ActionEvent event) {
        if (pass1.getText().equals(pass2.getText())) {
            thisUser.changePass("12345", pass1.getText());
            confirmation.setText("Password Has Been Changed Successfully!");
        } else {
            confirmation.setText("Password did not match!");
        }
    }

}
