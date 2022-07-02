/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinanceOfficer;

import Admin.MonthlyBudget;
import Classes.Budget;
import Classes.FinanceOfficer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ViewBudgetController implements Initializable {

    @FXML
    private TableView budgetTable;
    @FXML
    private TableColumn<MonthlyBudget, String> expenseCol;
    @FXML
    private TableColumn<MonthlyBudget, String> amountCol;
    @FXML
    private ComboBox month;
    @FXML
    private Label totalLabel1;
    @FXML
    private Label totalLabel2;
    @FXML
    private ComboBox year;

    ObservableList<String> monthList = FXCollections.observableArrayList("January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December");

    ObservableList<String> yearList = FXCollections.observableArrayList("2021", "2022", "2023", "2024", "2025",
            "2026", "2027", "2028", "2029", "2030");

    
    private FinanceOfficer thisUser;

    public void setUser(FinanceOfficer u) {
        this.thisUser = u;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        month.setItems(monthList);
        year.setItems(yearList);
        expenseCol.setCellValueFactory(new PropertyValueFactory("expenseCol"));
        amountCol.setCellValueFactory(new PropertyValueFactory("amountCol"));
    }

    private void loadBudget(String month, String year) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        budgetTable.getItems().clear();
//        System.out.println(month + year);
        String[] amount;
        ArrayList<String> expensesList = new ArrayList<String>();
        try {
            f = new File("Files/Budgets.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                Budget b;
                while (true) {
                    b = (Budget) ois.readObject();
                    System.out.println("Inside1");
                    if (b.getMonth().equals(month) && b.getYear().equals(year)) {
                        System.out.println("Inside2");
                        expensesList = b.getExpenses();
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
        float totalAmount = 0.0f;
        for (String perExpense : expensesList) {
            amount = perExpense.split(":");
            MonthlyBudget mb = new MonthlyBudget(amount[0], amount[1]);
            budgetTable.getItems().add(mb);
            totalAmount = totalAmount + Float.parseFloat(amount[1]);
        }
        totalLabel2.setText("BDT " + Float.toString(totalAmount));
    }

    @FXML
    private void loadBudget(ActionEvent event) {
        budgetTable.getItems().clear();
        String m = month.getSelectionModel().getSelectedItem().toString();
        String y = year.getSelectionModel().getSelectedItem().toString();
        loadBudget(m, y);

        totalLabel1.setText("Total Budget:");
    }

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FinanceOfficerFrontPage.fxml"));
        Parent root = loader.load();

        Scene personViewScene = new Scene(root);
        FinanceOfficerFrontPageController controller = loader.getController();
        controller.setUser(thisUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

}
