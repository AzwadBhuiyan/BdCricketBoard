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
public class PaymentStatus {
    String sponserID, sponserName, advertisementType, paymentStatus;

    public PaymentStatus(String sponserID, String sponserName, String advertisementType, String paymentStatus) {
        this.sponserID = sponserID;
        this.sponserName = sponserName;
        this.advertisementType = advertisementType;
        this.paymentStatus = paymentStatus;
    }

    public String getSponserID() {
        return sponserID;
    }

    public String getSponserName() {
        return sponserName;
    }

    public String getAdvertisementType() {
        return advertisementType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    
    
}
