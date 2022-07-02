/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamCoach;

import Classes.TeamCoach;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ViewApplicationsController implements Initializable {

    private TeamCoach thisUser;

    public void setUser(TeamCoach u) {
        this.thisUser = u;
    }
    @FXML
    private Label viewApplications;
    @FXML
    private TableView viewApplicationsTable;
    @FXML
    private TableColumn<ApplicationTable, String> id;
    @FXML
    private TableColumn<ApplicationTable, String> name;
    @FXML
    private TableColumn<ApplicationTable, String> subject;
    @FXML
    private TableColumn<ApplicationTable, String> status;

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TeamCoachFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        TeamCoachFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void loadApplicationBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewApplications_2.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        ViewApplications_2Controller controller = loader.getController();
        controller.setUser(thisUser, (ApplicationTable) viewApplicationsTable.getSelectionModel().getSelectedItem() );

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    private void loadApplications() {
        File f = null;
        Scanner sc;
        String str;
        String[] tokens = new String[4];
        try {
            f = new File("Files/LeaveApplications.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("::");
                    System.out.println(tokens[3]);
                    viewApplicationsTable.getItems().add(new ApplicationTable(tokens[0], tokens[1], tokens[2], tokens[3]));

                }
            } else {
                System.out.println("Application file does not exist.");
            }
        } catch (IOException ex) {
            System.out.println("IOException occured: " + ex);
        } finally {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        subject.setCellValueFactory(new PropertyValueFactory("subject"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        loadApplications();
    }

}
