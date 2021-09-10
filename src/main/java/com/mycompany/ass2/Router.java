/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ass2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kerols
 */
public class Router {

    ArrayList<Device> list;
    Semaphore semaphore;
    DefaultTableModel table;
    

    public Router(ArrayList<Device> D, int size) {
        list = D;
        semaphore = new Semaphore(size);
    }

    public void setjTable(DefaultTableModel table) {
        this.table = table;
    }

    public DefaultTableModel getjTable() {
        return table;
    }

    public void start() throws InterruptedException, IOException {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setRouter(this);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }
        for(int i = 0; i < list.size(); i++){
            list.get(i).join();
        }
        File file = new File("output.txt");
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file,true)) {
            Vector<Vector> data = table.getDataVector();
            for(int i = 0; i < data.size(); i++){
                writer.write((String)(data.elementAt(i)).elementAt(0) + "\r\n");
            }
        }
    }

    public void occupy(Device device) throws IOException {
        semaphore.P(device);
    }

    public void release(Device d) {
        semaphore.V(d);
    }
}
