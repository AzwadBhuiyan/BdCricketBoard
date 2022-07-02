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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class EditAccountController implements Initializable {

    private User thisUser = null, tempUser = null;

    public void setUser(User u, User tempUser) {
        this.thisUser = u;
        this.tempUser = tempUser;
        accountID.setText(this.tempUser.id);
        name.setText(this.tempUser.name);
        email.setText(this.tempUser.email);
//        accTypes.setValue("Admin");
    }

    @FXML
    private TextField name;

    

    ObservableList<String> accountTypes = FXCollections.observableArrayList("Admin", "Team Coach", "Player", "Sponsor", "Financial Officer");
    @FXML
    private TextField email;
   
    @FXML
    private Label accountID;
    @FXML
    private Label confirmation;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FindAccount.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);

        //access the controller
        FindAccountController controller = loader.getController();
        controller.setUser((Admin)thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(personViewScene);
        window.show();

    }

    @FXML
    private void saveOnClick(ActionEvent event) throws IOException {

        Admin a = new Admin();
        boolean confirmUser = a.editAccount("12345", name.getText(), email.getText());
        if (confirmUser == true) {
            confirmation.setText("Account Has Been Successfully Saved!");
        } else {
            confirmation.setText("Account Has Been Successfully Saved!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
