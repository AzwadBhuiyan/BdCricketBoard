/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketboard;

import Admin.AdminFrontPageController;
import Classes.Admin;
import Classes.Advertisement;
import Classes.Budget;
import Classes.FinanceOfficer;
import Classes.Player;
import Classes.Sponsor;
import Classes.TeamCoach;
import Classes.User;
import FinanceOfficer.FinanceOfficerFrontPageController;
import Player.PlayerFrontPageController;
import Sponsor.SponsorFrontPageController;
import TeamCoach.TeamCoachFrontPageController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author AhNAF TAzWAR
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label test2;

    @FXML
    private Label error;

    @FXML
    private TextField accountID;
    @FXML
    private PasswordField password;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("It is working!");
        test2.setText("Wellcome to Cricket Board Bangladesh!");
    }

    @FXML
    private void signInOnClick(ActionEvent event) throws IOException {

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User tempUser = null;

        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
                    if (accountID.getText().equals(u.id) && password.getText().equals(u.pass)) {
                        tempUser = u;
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

        FXMLLoader loader = new FXMLLoader();
        Scene scene = null;
        if (tempUser instanceof Admin) {
            loader.setLocation(getClass().getResource("/Admin/AdminFrontPage.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            AdminFrontPageController controller = loader.getController();
            controller.setUser(tempUser);

        } else if (tempUser instanceof Sponsor) {
            loader.setLocation(getClass().getResource("/Sponsor/SponsorFrontPage.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            SponsorFrontPageController controller = loader.getController();
            controller.setUser(tempUser);

        } else if (tempUser instanceof Player) {
            loader.setLocation(getClass().getResource("/Player/PlayerFrontPage.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            PlayerFrontPageController controller = loader.getController();
            controller.setUser(tempUser);

        } else if (tempUser instanceof TeamCoach) {
            loader.setLocation(getClass().getResource("/TeamCoach/TeamCoachFrontPage.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            TeamCoachFrontPageController controller = loader.getController();
            controller.setUser(tempUser);

        } else if (tempUser instanceof FinanceOfficer) {
            loader.setLocation(getClass().getResource("/FinanceOfficer/FinanceOfficerFrontPage.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            FinanceOfficerFrontPageController controller = loader.getController();
            controller.setUser(tempUser);

        }
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        createUsers();
//        readUsers();
//        createAdvertisement();
//        readAvertisement();
//        createBudget();
    }

    private void createUsers() {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        User u1 = new Admin("12345", "Ahnaf Tazwar", "asd", "ahnaf@gmail.com", 'm');
        /*
        User u2 = new Sponsor("32165", "Ahmed Ali", "qwe", "ahmedAli@gmail.com", 'm', 2500);
        User u3 = new Player("112233", "Tamim Iqbal", "qwe", "tamimIqbal@gmail.com", 'm', 2500, "Bangladesh Bank::32165497214::Tamim Iqbal");
        User u4 = new TeamCoach("4321", "John Watson", "zc", "johnWatson@gmail.com", 'm', 2500, "Agrani Bank::43215497214::John Watson");
        User u5 = new FinanceOfficer("221133", "Akbar Chowdhury", "zc", "akbarChowdhury@gmail.com", 'm', 2500, "Bank Asia::4221133497214::Akbar Chowdhury");
         */
//        Sponsor s = (Sponsor) u2;
//        s.setSalary(25000);
//        u2 = s;
        try {
            f = new File("Files/Users.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(u1);
            /*
            oos.writeObject(u2);
            oos.writeObject(u3);
            oos.writeObject(u4);
            oos.writeObject(u5);
*/
        } catch (IOException ex) {
            System.out.println("There was an error: " + ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("There was an error: " + ex);
            }
        }
    }

    private void createBudget() {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        ArrayList<String> expenses = new ArrayList<String>();
        expenses.add("Player Salary:800000");
        expenses.add("Team Coach Salary:80000");
        expenses.add("Stadium Maintenance:50000");
        Budget b = new Budget("January", "2021", expenses);

        try {
            f = new File("Files/Budgets.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(b);

        } catch (IOException ex) {
            System.out.println("There was an error: " + ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("There was an error: " + ex);
            }
        }
    }

    private void createAdvertisement() {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Advertisement a = new Advertisement("32165", "RFL", "Banner");
        try {
            f = new File("Files/Advertisements.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(a);

        } catch (IOException ex) {
            System.out.println("There was an error: " + ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("There was an error: " + ex);
            }
        }
    }

    private void readAvertisement() {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Files/Advertisements.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                Advertisement u;
                while (true) {
                    u = (Advertisement) ois.readObject();
                    System.out.println("Advertisement");
                    System.out.println("ID: " + u.getSponsorID());
                    System.out.println("Name: " + u.getName());
                    System.out.println("Type: " + u.getType());
                    System.out.println("Payment Status: " + u.getPaymentStatus());
                    System.out.println("is Active: " + u.getIsActive());
                    System.out.println("----------------------------------");

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

    private void readUsers() {
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
                    if (u instanceof Admin) {
                        System.out.println("User Type: Admin");
                    } else if (u instanceof Sponsor) {
                        System.out.println("User Type: Sponsor");
                    }
                    System.out.println("ID: " + u.id);
                    System.out.println("Name: " + u.name);
                    System.out.println("Password: " + u.pass);
                    System.out.println("Email: " + u.email);
                    System.out.println("Gender: " + u.gender);
                    System.out.println("----------------------------------");

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

    private void elseif(boolean equals) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
