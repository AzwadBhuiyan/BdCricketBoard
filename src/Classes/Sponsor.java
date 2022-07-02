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
public class Sponsor extends User implements Serializable {

    public Sponsor(String id, String name, String pass, String email, char gender, float s) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.gender = gender;
        paymentMethod = "default";
//        salary = 0.0f;
        this.setSalary(s);
    }

    public Sponsor() {

    }

    public void createAdvertisement(String sponsorID, String name, String type) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Advertisement a = new Advertisement(sponsorID, name, type);
        try {
            f = new File("Files/Advertisements.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

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

    public void deleteAdvertisement(String id, String name, String type) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Advertisement> advertisementLists = new ArrayList<Advertisement>();

        try {
            f = new File("Files/Advertisements.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                Advertisement a;
                while (true) {
                    a = (Advertisement) ois.readObject();
                    if (a.getSponsorID().equals(id) && a.getName().equals(name)
                            && a.getType().equals(type)) {
                        continue;
                    } else {

                        advertisementLists.add(a);
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
        ReWriteUsers.rewriteAdvertisements(advertisementLists);
    }

    @Override
    void changePaymentMethod(String prevPass, String newPass) {
        System.out.println("Method Changed");
    }

    @Override
    public void changePass(String id, String newPass) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean isPasswordChanged = false;
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
                        isPasswordChanged = true;
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
