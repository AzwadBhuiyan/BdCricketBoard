/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamCoach;

import Classes.Player;
import Classes.TeamCoach;
import Classes.User;
import Player.PlayerFrontPageController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class TrackPlayerRecordsController implements Initializable {

    private TeamCoach thisUser;

    public void setUser(TeamCoach u) {
        this.thisUser = u;
    }

    ObservableList<String> playersList = FXCollections.observableArrayList();

    @FXML
    private ComboBox playersCombo;
    @FXML
    private TextField totalRuns;
    @FXML
    private TextField oversPlayed;
    @FXML
    private TextField wicketsPlayed;
    @FXML
    private Label matchCounter;
    @FXML
    private Label confirm;

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

    private void getPlayers() {
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
                    if (u instanceof Player) {
                        playersList.add(u.id);
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
        playersCombo.setItems(playersList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getPlayers();
    }

    @FXML
    private void countMatches(ActionEvent event) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        String pID = playersCombo.getSelectionModel().getSelectedItem().toString();
        int counter = 0;
        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
                    if (u instanceof Player) {
                        if (((Player) u).id.equals(pID)) {
                            Player tempP = (Player) u;
                            counter = tempP.getMatches().size();
                            break;
                        }
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
        matchCounter.setText("Total Matches Played: " + Integer.toString(counter + 1));
        System.out.println("Total Matches: " + counter);
    }

    @FXML
    private void saveOnClick(ActionEvent event) {
        String pID = playersCombo.getSelectionModel().getSelectedItem().toString();
        thisUser.trackPlayerRecords(pID, totalRuns.getText(), oversPlayed.getText(), wicketsPlayed.getText());
        confirm.setText("Records saved successfully");
    }

}
