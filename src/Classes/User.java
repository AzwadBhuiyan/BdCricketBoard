/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AhNAF TAzWAR
 */
public abstract class User implements Serializable {

    public String id, name, pass, email, paymentMethod;
    public char gender;
    private float salary;

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    

    public void changePass(String id, String newPass) {
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
                        u.pass = newPass;
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

    ;
    
    public String getPaymentMethod(String id) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String paymentM = "";

        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
                    if (u.id.equals(id)) {
                        paymentM = u.paymentMethod;
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
        return paymentM;
    }

    

    abstract void changePaymentMethod(String id, String toBeEditingPayment);
}
