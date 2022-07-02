/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Classes.Admin;
import Classes.Advertisement;
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
    @FXML
    private TableView paymentStatusTable;

    @FXML
    private TableColumn<PaymentStatus, String> sponserName;
    @FXML
    private TableColumn<PaymentStatus, String> advertisementType;
    @FXML
    private TableColumn<PaymentStatus, String> paymentStatus;
    @FXML
    private TableColumn<PaymentStatus, String> sponserID;
    @FXML
    private Label confirmation;
    /**
     * Initializes the controller class.
     */
    
    private void loadPaymentStatus() {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        paymentStatusTable.getItems().clear();
        try {
            f = new File("Files/Advertisements.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                Advertisement a;
                while (true) {
                    a = (Advertisement) ois.readObject();
                    PaymentStatus p = new PaymentStatus(a.getSponsorID(), a.getName(), a.getType(), a.getPaymentStatus());
                    paymentStatusTable.getItems().add(p);
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
        // TODO
        sponserID.setCellValueFactory(new PropertyValueFactory("sponserID"));
        sponserName.setCellValueFactory(new PropertyValueFactory("sponserName"));
        advertisementType.setCellValueFactory(new PropertyValueFactory("advertisementType"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory("paymentStatus"));
        loadPaymentStatus();
    }    

    @FXML
    private void deleteOnClick(ActionEvent event) {
        PaymentStatus p = (PaymentStatus) paymentStatusTable.getSelectionModel().getSelectedItem();
        String sID, aName, aType, pStatus = "";
        sID = p.sponserID;
        aName = p.sponserName;
        aType = p.advertisementType;
        pStatus = p.paymentStatus;
        Admin a = new Admin();
        a.deleteAdvertisement(sID, aName, aType, pStatus);
        loadPaymentStatus();
        confirmation.setText("Delete Successful");
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
    
}
