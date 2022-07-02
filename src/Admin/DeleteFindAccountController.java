/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Classes.Admin;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class DeleteFindAccountController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField accountID;
    @FXML
    private Label deleteConfirmation;
    @FXML
    private Label deleteConfirmation2;

    @FXML
    private void findAndDeleteAccOnClick(ActionEvent event) throws IOException {
/*
        if (accountID.getText().equals("123")) {

            deleteConfirmation.setText("The account has been successfully deleted!");
            deleteConfirmation2.setText("");
        } else {
            deleteConfirmation.setText("");
            deleteConfirmation2.setText("The account does not exists!");
        }*/

        Admin a = new Admin();
        boolean foundUser = a.deleteAccount(accountID.getText());

        if (foundUser) {
            System.out.println("User is deleted");
        } else {
            System.out.println("No user found!");
        }

    }

    private Admin thisUser = null;

    public void setUser(Admin u) {
        this.thisUser = u;
    }
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminFrontPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
