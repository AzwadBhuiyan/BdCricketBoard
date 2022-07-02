/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Classes.Admin;
import Classes.FinanceOfficer;
import Classes.Player;
import Classes.Sponsor;
import Classes.TeamCoach;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ViewAllUsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView usersTable;
    @FXML
    private TableColumn<UsersTable, String> idCol;
    @FXML
    private TableColumn<UsersTable, String> nameCol;
    @FXML
    private TableColumn<UsersTable, String> typeCol;
    @FXML
    private TableColumn<UsersTable, String> salaryCol;

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

    private void loadAllUsers() {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        UsersTable ut;
        String type = "";
        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();

                    if (u instanceof Admin) {
                        type = "Admin";
                    } else if (u instanceof Sponsor) {
                        type = "Sponsor";
                    } else if (u instanceof Player) {
                        type = "Player";
                    } else if (u instanceof TeamCoach) {
                        type = "TeamCoach";
                    } else if (u instanceof FinanceOfficer) {
                        type = "FinanceOfficer";
                    }

                    ut = new UsersTable(u.id, u.name, type, Float.toString(u.getSalary()));
                    usersTable.getItems().add(ut);
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
        idCol.setCellValueFactory(new PropertyValueFactory("idCol"));
        nameCol.setCellValueFactory(new PropertyValueFactory("nameCol"));
        typeCol.setCellValueFactory(new PropertyValueFactory("typeCol"));
        salaryCol.setCellValueFactory(new PropertyValueFactory("salaryCol"));
        
        loadAllUsers();
    }

}
