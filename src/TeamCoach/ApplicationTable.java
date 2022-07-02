/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamCoach;

/**
 *
 * @author AhNAF TAzWAR
 */
public class ApplicationTable {
    String id, name, subject, status;

    public ApplicationTable(String id, String name, String subject, String status) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }
    
    
}
