/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author AhNAF TAzWAR
 */
public class AppendableObjectOutputStream extends ObjectOutputStream{
    public AppendableObjectOutputStream(OutputStream os)throws IOException{
        super(os);
    }
    
    @Override
    protected void writeStreamHeader()throws IOException{}
}