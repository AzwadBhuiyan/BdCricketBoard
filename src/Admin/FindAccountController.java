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
public class FindAccountController implements Initializable {

  

    @FXML
    private TextField accountID;
    @FXML
    private Label confirmation;

    @FXML
    private void findIDOnClick(ActionEvent event) throws IOException {
        Admin a = new Admin();
        User userObj = a.findUser(accountID.getText());

        if (userObj != null) {
            confirmation.setText(userObj.name);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditAccount.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            //access the controller
            EditAccountController controller = loader.getController();
            controller.setUser(this.thisUser, userObj);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        } else {
            confirmation.setText("NO user found!");
        }
    }

    private Admin thisUser = null;

    public void setUser(Admin u) {
        this.thisUser = u;
    }
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        AdminFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
