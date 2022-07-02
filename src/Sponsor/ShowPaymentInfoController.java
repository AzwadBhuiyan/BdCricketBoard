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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ShowPaymentInfoController implements Initializable {

    private User thisUser;

    public void setUser(User u) {
        this.thisUser = u;
        Sponsor s = (Sponsor) thisUser;
        payment.setText("Total Payment of December:   " + s.getSalary() + "BDT");
    }
    @FXML
    private Label payment;

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SponsorFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        SponsorFrontPageController controller = loader.getController();
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
