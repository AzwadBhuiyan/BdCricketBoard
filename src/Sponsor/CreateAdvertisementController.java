/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sponsor;

import Classes.Sponsor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class CreateAdvertisementController implements Initializable {

    ArrayList<String> fileTypeList;

    private ImageView imgPane;

    @FXML
    private Label fileName;
    @FXML
    private RadioButton bannerR;
    @FXML
    private ToggleGroup adType;
    @FXML
    private RadioButton logoR;
    @FXML
    private RadioButton jerseyR;

    private Sponsor thisUser;
    @FXML
    private TextField adName;

    public void setUser(Sponsor u) {
        this.thisUser = u;
    }

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
        /*
        fileTypeList = new ArrayList<String>();
        fileTypeList.add("*.jpg");
        fileTypeList.add("*.jpeg");
        fileTypeList.add("*.png");
         */
    }

    /*
    @FXML
    private void fileUpload(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image files", fileTypeList));
        File f = fc.showOpenDialog(null);

    }
     */
    @FXML
    private void saveAdvertisement(ActionEvent event) {
        String type = "";
        if (bannerR.isSelected()) {
            type = "Banner";
        } else if (logoR.isSelected()) {
            type = "Logo";
        } else if (jerseyR.isSelected()) {
            type = "Jersey";
        }
        this.thisUser.createAdvertisement(this.thisUser.id, adName.getText(), type);
    }

    /*
    @FXML
    private void fileUpload(MouseEvent event) {
    }
     */
}
