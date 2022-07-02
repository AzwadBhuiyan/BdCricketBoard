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
public class UsersTable {
    String idCol, nameCol, typeCol, salaryCol;

    public UsersTable(String idCol, String nameCol, String typeCol, String salaryCol) {
        this.idCol = idCol;
        this.nameCol = nameCol;
        this.typeCol = typeCol;
        this.salaryCol = salaryCol;
    }

    public String getIdCol() {
        return idCol;
    }

    public String getNameCol() {
        return nameCol;
    }

    public String getTypeCol() {
        return typeCol;
    }

    public String getSalaryCol() {
        return salaryCol;
    }
    
    
}
