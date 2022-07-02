/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

/**
 *
 * @author AhNAF TAzWAR
 */
public class BudgetTable {
    String expenseCol, amountCol;

    public BudgetTable(String expenseCol, String amountCol) {
        this.expenseCol = expenseCol;
        this.amountCol = amountCol;
    }

    public String getExpenseCol() {
        return expenseCol;
    }

    public String getAmountCol() {
        return amountCol;
    }
    
    
}
