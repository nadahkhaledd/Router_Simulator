package com.mycompany.ass2;

import java.awt.Color;
import java.awt.Component;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Container;


public class Tools {

    public static void msgBox(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void openForm(JFrame form) {
        form.setLocationRelativeTo(null);
        form.setDefaultCloseOperation(2);
        form.getContentPane().setBackground(Color.white);
        form.setVisible(true);
        form.setResizable(false);
    }
    
    public class Table {

        public Object[][] items;
        public int column;

        public Table(int x) {
            column = x;
            items = new Object[0][x];
        }

        public void addnewrow(Object row[]) {
            Object temp[][] = items;
            items = new Object[items.length + 1][column];
            System.arraycopy(temp, 0, items, 0, temp.length);
            items[items.length - 1] = row;
        }

        public void printitems() {
            for (Object[] x : items) {
                for (Object z : x) {
                    System.err.println(z + ";");
                }
            }
            System.out.println();
        }

        public void editrow(int rowi, int columni, Object newdata) {
            items[rowi][columni] = newdata;
        }

        public void deleterow(int row) {
            Object temp[][] = items;
            int z = 0;
            items = new Object[items.length - 1][column];
            for (int x = 0; x < temp.length; x++) {
                if (x != row) {
                    items[z] = temp[x];
                    z++;
                }

            }
        }

        public Object getvalue(int rowi, int columni) {
            return items[rowi][columni];
        }

        public Object[] getrow(int row) {
            return items[row];
        }
        

    }

}
