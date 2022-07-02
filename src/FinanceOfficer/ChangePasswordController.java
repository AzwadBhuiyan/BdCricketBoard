/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinanceOfficer;

import Classes.FinanceOfficer;
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

    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    @FXML
    private Label confirmation;

    private FinanceOfficer thisUser;

    public void setUser(FinanceOfficer u) {
        this.thisUser = u;
    }

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FinanceOfficerFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        FinanceOfficerFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void confirmOnClick(ActionEvent event) throws IOException {

        if (pass1.getText().equals(pass2.getText())) {
            thisUser.changePass(thisUser.id, pass2.getText());
            confirmation.setText("Password Has Been Changed Successfully!");
        } else {
            confirmation.setText("Password did not match!");
        }

    }
}
