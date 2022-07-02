/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AhNAF TAzWAR
 */
public class FinanceOfficer extends User implements Serializable {

    

    public FinanceOfficer(String id, String name, String pass, String email, char gender, float s, String payment) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.gender = gender;
        this.paymentMethod = payment;
        this.setSalary(s);
    }

    public void createMonthlyExpense(String month, String year, ArrayList<String> expenses) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        Budget b = new Budget(month, year, expenses);

        try {
            f = new File("Files/Budgets.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

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

    @Override
    public void changePaymentMethod(String id, String toBeEditingPayment) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<User> userLists = new ArrayList<User>();

        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
                    if (u.id.equals(id)) {
                        u.paymentMethod = toBeEditingPayment;
                    }
                    userLists.add(u);
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
        ReWriteUsers.rewrite(userLists);
    }
}
