/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AhNAF TAzWAR
 */
public class Budget implements Serializable {
    private String month, year;
    private ArrayList <String> expenses;

    public Budget(String month, String year, ArrayList<String> expenses) {
        this.month = month;
        this.year = year;
        this.expenses = expenses;
    }

    public void addExpense(String expenses) {
        this.expenses.add(expenses);
    }

    public ArrayList<String> getExpenses() {
        return expenses;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    
   

   
    
    
}
