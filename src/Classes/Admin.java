/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Admin.PaymentStatus;
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
public class Admin extends User implements Serializable {

    public Admin(String id, String name, String pass, String email, char gender) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.gender = gender;
        paymentMethod = "default";
        this.setSalary(25000.0f);
    }

    public Admin() {
    }

    public User findUser(String id) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User foundUser = null;

        try {
            f = new File("Files/Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                User u;
                while (true) {
                    u = (User) ois.readObject();
                    if (u.id.equals(id)) {
                        foundUser = u;
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
        return foundUser;
    }

    public boolean createAccount(User u) {
        if (findUser(u.id) != null) {
            File f = null;
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                f = new File("Files/Users.bin");
                if (f.exists()) {
                    fos = new FileOutputStream(f, true);
                    oos = new AppendableObjectOutputStream(fos);
                } else {
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                }
                oos.writeObject(u);

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
            return true;
        } else {
            return false;
        }
    }

    public void incrementSalary(String id, Float s){
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
                        u.setSalary(s * 1.0f);
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
//        reWriteUsers(userLists);
        ReWriteUsers.rewrite(userLists);
        
    }
    
    //make it void
    public boolean editAccount(String id, String name, String email) {

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean returnUser = false;
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
                        u.name = name;
                        u.email = email;
                        returnUser = true;
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
//        reWriteUsers(userLists);
        ReWriteUsers.rewrite(userLists);
        return returnUser;
    }

    public boolean deleteAccount(String id) {

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean foundUser = false;
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
                        foundUser = true;
                        continue;
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

    public void viewMonthyBudget(String month) {
        System.out.println("viewMonthyBudget");
    }

    public void deleteAdvertisement(String id, String name, String type, String status) {
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
                            && a.getType().equals(type) && a.getPaymentStatus().equals(status)) {
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

    public void updatePayment(String id, String name, String type, String status, String newStatus) {
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
                            && a.getType().equals(type) && a.getPaymentStatus().equals(status)) {
                        a.setPaymentStatus(newStatus);
                    }
                    advertisementLists.add(a);
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

    @Override
    void changePaymentMethod(String prevPass, String newPass) {
        System.out.println("Method Changed");
    }
}

//changePaymenMethod need to be implemented
//Graph - Team Coach - Show Player Statistics
//Dudget Class - need to created
