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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SponsorFrontPageController implements Initializable {

    private Sponsor thisUser;

    public void setUser(User u) {
        this.thisUser = (Sponsor) u;
    }

    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/cricketboard/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        System.out.println("adadasd");

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void createAdvertisement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreateAdvertisement.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        CreateAdvertisementController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void viewAdvertisement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAdvertisement.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        ViewAdvertisementController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void showPaymentInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPaymentInfo.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        ShowPaymentInfoController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void deleteAdvertisement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DeleteAdvertisement.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        DeleteAdvertisementController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void changePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChangePassword.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        ChangePasswordController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

}
