/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sponsor;

import Classes.Sponsor;
import Classes.User;
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

    private User thisUser;

    public void setUser(User u) {
        this.thisUser = u;
    }

    @FXML
    private Label confirmation;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SponsorFrontPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void confirmOnClick(ActionEvent event) {
        if (pass1.getText().equals(pass2.getText())) {
            Sponsor s = new Sponsor();
            s.changePass(thisUser.id, pass1.getText());
            confirmation.setText("Password Has Been Changed Successfully!");
        } else {
            confirmation.setText("Password did not match!");
        }
    }

}
