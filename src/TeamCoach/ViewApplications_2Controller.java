/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamCoach;

import Classes.TeamCoach;
import Classes.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
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
public class ViewApplications_2Controller implements Initializable {

    private TeamCoach thisUser;
    private ApplicationTable application;

    public void setUser(User u, ApplicationTable a) {
        this.thisUser = (TeamCoach) u;
        this.application = (ApplicationTable) a;
        name.setText(application.getName());
        subject.setText(application.getSubject());
        subject.setText(application.getSubject());
        body.setText(getApplicationBody(application.getId()));
    }

    private String getApplicationBody(String id) {
        File f = null;
        Scanner sc;
        String str = "";
        String[] tokens = new String[4];
        try {
            f = new File("Files/LeaveApplications.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("::");
                    if (tokens[0].equals(id)) {
                        str = tokens[2];
//                        System.out.println(tokens[2]);
                    }

                }
            } else {
                System.out.println("Application file does not exist.");
            }
        } catch (IOException ex) {
            System.out.println("IOException occured: " + ex);
        } finally {
        }
        return str;
    }

    @FXML
    private Label viewApplications;
    @FXML
    private Label subject;
    @FXML
    private Label body;
    @FXML
    private Label confirm;
    @FXML
    private Label name;

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewApplications.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        ViewApplicationsController controller = loader.getController();
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
    private void declineOnClick(ActionEvent event) {
        thisUser.handleApplication(application.getId(), "Declined");
        confirm.setText("Appication is declined");
    }

    @FXML
    private void acceptOnClick(ActionEvent event) {
        thisUser.handleApplication(application.getId(), "Accepted");
        confirm.setText("Appication is accepted");
    }

}
