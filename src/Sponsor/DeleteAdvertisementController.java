/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sponsor;

import Classes.Advertisement;
import Classes.Sponsor;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class DeleteAdvertisementController implements Initializable {

    private User thisUser;

    public void setUser(User u) {
        this.thisUser = u;
        loadAdvertisements();
    }
    
    @FXML
    private TableView advertisementTable;
    @FXML
    private TableColumn<ViewAdvertisement, String> nameCol;
    @FXML
    private TableColumn<ViewAdvertisement, String> typeCol;
    @FXML
    private Label confirmation;

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SponsorFrontPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    private void loadAdvertisements() {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        advertisementTable.getItems().clear();
        try {
            f = new File("Files/Advertisements.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                Advertisement a;
                while (true) {
                    a = (Advertisement) ois.readObject();
                    if (a.getSponsorID().equals(thisUser.id)) {
                        ViewAdvertisement p = new ViewAdvertisement(a.getName(), a.getType());
                        advertisementTable.getItems().add(p);
                    }

                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory("nameCol"));
        typeCol.setCellValueFactory(new PropertyValueFactory("typeCol"));
    }

    @FXML
    private void deleteBtnOnClick(ActionEvent event) {
        ViewAdvertisement va = (ViewAdvertisement) advertisementTable.getSelectionModel().getSelectedItem();
        String sID, aName, aType = "";
        sID = thisUser.id;
        aName = va.nameCol;
        aType = va.typeCol;
        Sponsor s = new Sponsor();
        s.deleteAdvertisement(sID, aName, aType);
        confirmation.setText("Delete Successful");
        loadAdvertisements();
    }

}
