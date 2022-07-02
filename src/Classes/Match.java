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
public class Match implements Serializable{
    protected int runs, wickets;
    protected float overs;

    public Match(int runs, int wickets, float overs) {
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
    }
    
    
}
