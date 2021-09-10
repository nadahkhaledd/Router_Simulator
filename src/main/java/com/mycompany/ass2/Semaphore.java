/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ass2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author kerols
 */
public class Semaphore {
    protected int value = 0;
    Queue<Integer> q;
	
    public Semaphore() { 
        this.q = new LinkedList<>();
        value = 0; 
    }

    public Semaphore(int initial) { 
        this.q = new LinkedList<>();
        for(int i = 0; i < initial; i++){
            q.add(i+1);
        }
        value = initial; 
    }

    public synchronized void P(Device device) throws IOException {
            value-- ;
            if (value < 0) {
                    try { 
                            device.write("(" + device.name + ")(" + device.type +") arrived and waiting");
                            wait();
                            device.setConnectionNumber(q.poll());
                    } catch( InterruptedException e ) { }
            } else {
                device.setConnectionNumber(q.poll());
                device.write("(" + device.name + ")(" + device.type + ")arrived");
                    
            }

    }

    public synchronized void V(Device d) {
            q.add(d.getConnectionNumber());
            value++ ; 
            if (value <= 0) {
                    notify();
            }
    }
}
