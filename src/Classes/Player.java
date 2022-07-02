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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AhNAF TAzWAR
 */
public class Player extends User implements Serializable {

    private int age;
    private float weight;
    private String height;
    private ArrayList<Match> matches = new ArrayList<Match>();
    ;

    public Player(String id, String name, String pass, String email, char gender, float s, String payment) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.gender = gender;
        paymentMethod = "default";
        this.setSalary(s);
        this.paymentMethod = payment;
//        matches = new ArrayList<Match>();
    }

    public Player() {

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public Player updateInfo(String id, String height, float weight, int age) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Player foundUser = null;
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
                        foundUser = (Player) u;
                        foundUser.setHeight(height);
                        foundUser.setWeight(weight);
                        foundUser.setAge(age);
                        userLists.add(foundUser);
                    } else {
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
        ReWriteUsers.rewrite(userLists);
        return foundUser;
    }

    public void applyLeave(String id, String subject, String body) {
        File f = null;
        FileWriter fw = null;
        try {
            f = new File("Files/LeaveApplications.txt");
            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }

            fw.write(
                    id + "::"
                    + subject + "::"
                    + body + "::" + "Pending" + "\n"
            );

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

    public String[] viewApplication(String id) {
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens = new String[4];
        try {
            f = new File("Files/LeaveApplications.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("::");
                    if (tokens[0].equals(id)) {
                        break;
                    }

                }
            } else {
                System.out.println("Application file does not exist.");
            }
        } catch (IOException ex) {
            System.out.println("IOException occured: " + ex);
        } finally {
        }
        return tokens;
    }

    @Override
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

    @Override
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

}
