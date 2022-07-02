/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 *
 * @author AhNAF TAzWAR
 */
public class Advertisement implements Serializable {

    private String sponsorID, name, type, paymentStatus;
    private boolean isActive;

    public Advertisement(String sponsorID, String name, String type) {
//        this.id = id;
        this.sponsorID = sponsorID;
        this.name = name;
        this.type = type;
        isActive = false;
        paymentStatus = "Due";
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    

//    public String getId() {
//        return id;
//    }

    public String getSponsorID() {
        return sponsorID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    
}
