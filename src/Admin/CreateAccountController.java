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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class CreateAccountController implements Initializable {

    ObservableList<String> accountTypes = FXCollections.observableArrayList("Admin", "Team Coach", "Player", "Sponsor", "Financial Officer");

    @FXML
    private ComboBox accTypes;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup gender;
    @FXML
    private TextField userID;
    @FXML
    private TextField name;
    @FXML
    private TextField pass;
    @FXML
    private TextField email;
    @FXML
    private Label confirm;

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

    @FXML
    private void createOnClick(ActionEvent event) throws IOException {
        User u;
        Admin a = new Admin();
        char gender = 'n';
        String id = userID.getText();
        String userName = name.getText();
        String userPass = pass.getText();
        String userEmail = email.getText();

        if (male.isSelected()) {
            gender = 'm';
            System.out.println("Genger: Male");
        } else if (female.isSelected()) {
            gender = 'f';
            System.out.println("Genger: Female");
        }
        if (accTypes.getSelectionModel().getSelectedItem() == "Admin") {
            u = new Admin(id, userName, userPass, userEmail, gender);
            boolean isAccCreated = a.createAccount(u);
            
            if (isAccCreated == true) {
                confirm.setText("Admin account  created!");
            } else {
                confirm.setText("Account ID exists!");
            }
            
        } else if (accTypes.getSelectionModel().getSelectedItem() == "Team Coach") {
//          u = new TeamCoach(); 
            System.out.println("Inside: Teach Coach");
            confirm.setText("");
        } else if (accTypes.getSelectionModel().getSelectedItem() == "Player") {
//          u = new TeamCoach(); 
            System.out.println("Inside: Player");
            confirm.setText("");
        } else if (accTypes.getSelectionModel().getSelectedItem() == "Sponsor") {
//          u = new TeamCoach(); 
            System.out.println("Inside: Sponsor");
            confirm.setText("");
        } else if (accTypes.getSelectionModel().getSelectedItem() == "Financial Officer") {
//          u = new TeamCoach(); 
            System.out.println("Inside: Financial Officer");
            confirm.setText("");
        } else {
//          u = new TeamCoach(); 
            confirm.setText("Please select a user type!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accTypes.setItems(accountTypes);
    }

}
