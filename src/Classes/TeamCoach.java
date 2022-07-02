/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author AhNAF TAzWAR
 */
public class TeamCoach extends User implements Serializable {

    

    public TeamCoach(String id, String name, String pass, String email, char gender, float s, String payment) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.gender = gender;
        paymentMethod = "default";
        this.paymentMethod = payment;
        this.setSalary(s);
    }

    public void trackPlayerRecords(String playerID, String r, String o, String w) {
        int runs = Integer.parseInt(r);
        int wickets = Integer.parseInt(w);
        float overs = Float.parseFloat(o);

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
//                    if (u instanceof Player) {
                    if (u.id.equals(playerID)) {
                        Match m = new Match(runs, wickets, overs);
                        Player tempPlayer = (Player) u;
//                            tempPlayer.setSalary(124723.10f);
                        tempPlayer.addMatch(m);
//                            User tempUser = (User)tempPlayer;
                        userLists.add(tempPlayer);
                    } //                    }
                    else {

                        userLists.add(u);
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
//        reWriteUsers(userLists);
        ReWriteUsers.rewrite(userLists);
    }

    public void handleApplication(String id, String status) {

        File f = null;
        Scanner sc;
        String str;
        String[] tokens = new String[4];
        ArrayList<String> applicationLists = new ArrayList<String>();
        try {
            f = new File("Files/LeaveApplications.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("::");
                    if (tokens[0].equals(id)) {
                        tokens[3] = status;
                        str = tokens[0] + "::" + tokens[1] + "::" + tokens[2] + "::" + tokens[3];
                    }
                    applicationLists.add(str);
                }
            } else {
                System.out.println("Application file does not exist.");
            }
        } catch (IOException ex) {
            System.out.println("IOException occured: " + ex);
        } finally {
        }

        f = null;
        FileWriter fw = null;
        try {
            f = new File("Files/LeaveApplications.txt");
            fw = new FileWriter(f);

            for (String application : applicationLists) {
                fw.write(application);
            }

        } catch (IOException ex) {
            System.out.println("IOException occured: " + ex);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println("IOException occured on closing file attempt: " + ex);
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
