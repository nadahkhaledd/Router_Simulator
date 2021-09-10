/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ass2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kerols
 */
public class Device extends Thread{

    public String name;
    public String type;
    public Router router;
    private int connectionNumber;

    public Device(String t, String n) throws IOException {
        name = n;
        type = t;
    }

    public void setConnectionNumber(int connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public int getConnectionNumber() {
        return connectionNumber;
    }

    @Override
    public void run() {
        try {
            router.occupy(this);
            connect();
            perform();
            logout();
            router.release(this);
        } catch (InterruptedException | IOException e) {
        }
        // TODO Auto-generated catch block
        
    }

    public void connect() throws IOException {
        write(name + " Occupied");
    }

    public void perform() throws IOException, InterruptedException {
        write(name + " performs online activity");
        Thread.sleep((int) (Math.random() % 100));
    }

    public void logout() throws IOException {
        write(name + " Logged out");
    }

    public void setRouter(Router r) {
        router = r;
    }

    public void write(String msg) throws IOException {
        String m = "Connection " + connectionNumber + ": " + msg;
        if(msg.charAt(0) == '('){
            router.getjTable().addRow(new String[]{msg});
        }else{
            router.getjTable().addRow(new String[]{m});
        }
    }
}
