/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.io.IOException;
import manager.Management;
import objectholder.Products;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments8
     * 
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
     Menu m = new Menu();

        try {
            m.enterChoice();
        } catch (Exception e) {
            System.out.println("Error!");
        }
       
    }
    
}
