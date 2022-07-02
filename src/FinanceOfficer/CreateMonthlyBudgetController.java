/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinanceOfficer;

import Admin.MonthlyBudget;
import Classes.FinanceOfficer;
import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class CreateMonthlyBudgetController implements Initializable {

    @FXML
    private TextField amount;
    @FXML
    private TextField expenseType;
    @FXML
    private Label confirm;
    @FXML
    private TableView budgetTable;
    @FXML
    private TableColumn<MonthlyBudget, String> expenseCol;
    @FXML
    private TableColumn<MonthlyBudget, String> amountCol;
    @FXML
    private ComboBox month;
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
    
    private ArrayList<String> expenseList = new ArrayList<String>();
    String m, y;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        month.setItems(monthList);
        year.setItems(yearList);
        expenseCol.setCellValueFactory(new PropertyValueFactory("expenseCol"));
        amountCol.setCellValueFactory(new PropertyValueFactory("amountCol"));
        
        
    }    
    
    @FXML
    private void backOnClick(ActionEvent event) throws IOException{
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

    @FXML
    private void addMonthlyBudget(ActionEvent event) {
        
         m = month.getSelectionModel().getSelectedItem().toString();
         y = year.getSelectionModel().getSelectedItem().toString();
        
        String typeOfExpense = expenseType.getText();
        String expense = amount.getText();
        expenseList.add(typeOfExpense + ":" + expense);
        budgetTable.getItems().add(new MonthlyBudget(typeOfExpense, expense));
    }

    @FXML
    private void createMonthlyBudget(ActionEvent event) {
        thisUser.createMonthlyExpense(m, y, expenseList);
    }
}
