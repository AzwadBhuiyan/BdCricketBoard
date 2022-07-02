/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author AhNAF TAzWAR
 */
public class ReWriteUsers {

    public static void rewrite(ArrayList<User> usersList) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        User u1 = new Admin("12345", "Ahnaf Tazwar", "asdzxc", "ahnaf@gmail.com", 'm');
        try {
            f = new File("Files/Users.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            for (User u : usersList) {
                oos.writeObject(u);
            }

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
    
    public static void rewriteAdvertisements(ArrayList<Advertisement> advertisementLists) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        User u1 = new Admin("12345", "Ahnaf Tazwar", "asdzxc", "ahnaf@gmail.com", 'm');
        try {
            f = new File("Files/Advertisements.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            for (Advertisement a : advertisementLists) {
                oos.writeObject(a);
            }

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
}
