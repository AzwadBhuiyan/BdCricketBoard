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
public class CheckApplicationStatusController implements Initializable {

    @FXML
    private Label subject;
    @FXML
    private Label body;
    @FXML
    private Label status;

    private Player thisUser;
    public void setUser(Player u) {
        this.thisUser = u;
        Player p = new Player();
//        System.out.println(p.viewApplication(thisUser.id));
        String[] applicationContents = p.viewApplication(thisUser.id);
        subject.setText(applicationContents[1]);
        body.setText(applicationContents[2]);
        status.setText(applicationContents[3]);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
   
    
}
