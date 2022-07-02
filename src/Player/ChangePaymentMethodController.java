/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Classes.Player;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ChangePaymentMethodController implements Initializable {

    @FXML
    private TextField accName;
    @FXML
    private TextField accNo;
    @FXML
    private Label confirm;
    @FXML
    private ComboBox banksCombo;
    @FXML
    private Label currentPayment;

    private Player thisUser;

    public void setUser(Player u) {
        this.thisUser = u;
//        System.out.println(this.thisUser);
        String p = this.thisUser.getPaymentMethod(this.thisUser.id);
        String[] payment = p.split("::");

        String str = "Current Payment Method\n" + "Bank Name: " + payment[0] + "\nAccount No.: " + payment[1] + "\nAccount Name: " + payment[2];
        currentPayment.setText(str);
    }

    ObservableList<String> banksList = FXCollections.observableArrayList("Agrani Bank", "Bangladesh Bank", "Janata Bank", "Bank Asia", "Mutual Trust Bank");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        banksCombo.setItems(banksList);

    }

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        PlayerFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void saveBtnOnClick(ActionEvent event) {
        if (accNo.getText().isEmpty() != true && accName.getText().isEmpty() != true
                && banksCombo.getSelectionModel().getSelectedItem().toString().isEmpty() != true) {

            String bankName = banksCombo.getSelectionModel().getSelectedItem().toString();
            String accountNo = accNo.getText();
            String accountName = accName.getText();

            String sendedPayment = bankName + "::" + accountNo + "::" + accountName;

            thisUser.changePaymentMethod(thisUser.id, sendedPayment);
            confirm.setText("Payment method has been changed successfully!");
        }
    }
}
