/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Classes.Admin;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class IncrementSalaryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField userID;
    @FXML
    private TextField incrementAmount;
    @FXML
    private Label incrementLabel;
    @FXML
    private Label confirmation;

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

    @FXML
    private void saveOnClick(ActionEvent event) {
        String id = userID.getText();
        Float amount = Float.parseFloat(incrementAmount.getText());
        thisUser.incrementSalary(id, amount);

    }

    @FXML
    private void loadOnClick(ActionEvent event) {
        String id = userID.getText();
        incrementLabel.setText("");
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
//                    System.out.println("Nope: " + u.id);
                    if (u.id.equals(userID.getText())) {
//                        System.out.println("YES");
                        incrementLabel.setText("Current Salary:    " + u.getSalary() + " BDT");
                        break;
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
        if (incrementLabel.getText().equals("")) {
            incrementLabel.setText("No User Found");
        }

    }

}
