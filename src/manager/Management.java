/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import objectholder.Customers;
import objectholder.Orders;
import objectholder.Products;

/**
 *
 * @author USER
 */
public class Management {

    private Scanner sc = new Scanner(System.in);
    private List<Products> productList;
    private List<Customers> customerList;
    private List<Orders> orderList;

    public Management() {
        productList = new ArrayList<>();
        customerList = new ArrayList<>();
        orderList = new ArrayList<>();
    }
//  Add function zone

    public void addOrder() {
        String orderId = Validation.getOrderId("Enter order Id(Dxxx):", orderList, 1);
        String orderCustomerId = Validation.getOrderCustomerId("Enter order's customer id(Cxxx): ", customerList, 1);
        String orderProductId = Validation.getProductId("Enter order's product id(Pxxx): ", productList, 1);
        int orderQuantity = Validation.getOrderQuantity("Enter order's quantity: ");
        String orderDate = Validation.checkValidDate("Enter order's date - dd/mm/yy: ", 1);
        String orderStatus = Validation.getOrderPending("Enter order pending \n 1 or blank = False | 2 = True: ", 1);
        orderList.add(new Orders(orderId, orderCustomerId, orderProductId, orderQuantity, orderDate, orderStatus));
        saveOrderToFile();
    }

    public void addCustomer() {

        String cId, cName, cAddress, cPhone;
        cId = Validation.getCustomerId("Enter customer's Id (Cxxx):", customerList, 1);
        cName = Validation.getCustomerName("Enter customer's name:");
        cAddress = Validation.getCustomerAddress("Enter customer's address:");
        cPhone = Validation.getCustomerPhone("Enter customer's phone:");
        customerList.add(new Customers(cId, cName, cAddress, cPhone));
        saveCustomerToFile();
        customerList.removeAll(customerList);
    }
// End add zone
//Search function zone
    public void searchCustomer() {
        System.out.print("Enter customer id to search (Cxxx):");
        String searchCustomerById = sc.nextLine();
        if (customerList.isEmpty()) {
            System.out.println("Can't search because the list is empty");
        } else if (searchCustomerById.equals("")) {
            for (Customers c : customerList) {
                System.out.print("" + c);
            }
        } else {
            for (Customers c : customerList) {
                if (c.getCustomerId().equalsIgnoreCase(searchCustomerById)) {
                    System.out.println("" + c);
                } else if (!checkCustomerId(searchCustomerById)) {
                    System.out.println("Id not found");
                    break;

                }
            }

        }

    }
    //End search function zone

    public boolean checkCustomerId(String s) {
        for (Customers c : customerList) {
            if (c.getCustomerId().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

//    Load function zone
    public void loadOrderFromFile() {
        try {
            File f = new File("orders.txt");
            if (!f.exists()) {
                System.out.println("New file is created <3");
                f.createNewFile();
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (39 < 79) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] info = line.split("[,]");
                String orderId = info[0].trim();
                String orderCustomerId = info[1].trim();
                String orderProductId = info[2].trim();
                int orderQuantity = Integer.parseInt(info[3].trim());
                String orderDate = info[4].trim();
                String orderStatus = info[5].trim();
                orderList.add(new Orders(orderId, orderCustomerId, orderProductId, orderQuantity, orderDate, orderStatus));

            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Error!!!");
        }

    }

    public void loadProductFromFile() throws FileNotFoundException, IOException {
        File f = new File("products.txt");
        if (!f.exists()) {
            System.out.println("New file is created <3");
            f.createNewFile();
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        while (39 < 79) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split("[,]");
            String productId = info[0].trim();
            String productName = info[1].trim();
            String productUnit = info[2].trim();
            String productOrigin = info[3].trim();
            double productPrice = Double.parseDouble(info[4].trim());
            productList.add(new Products(productId, productName, productUnit, productOrigin, productPrice));
        }
        fr.close();
        br.close();
    }

    public void loadCustomerFromFile() {
        try {
            File f = new File("customers.txt");
            if (!f.exists()) {
                System.out.println("New file is created <3");
                f.createNewFile();
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (39 < 79) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] info = line.split("[,]");
                String customerId = info[0].trim();
                String customerName = info[1].trim();
                String customerAddress = info[2].trim();
                String customerPhone = info[3].trim();
                customerList.add(new Customers(customerId, customerName, customerAddress, customerPhone));

            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Load fail!!");
        }

    }
// End load function zone

//    Save function zone
    public void saveCustomerToFile() {
        try {
            FileWriter fw = new FileWriter("customers.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Customers c : customerList) {
                bw.write(c.toString());

            }
            System.out.println("Added and save successful");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Save failed - Please disable read-only in text file properties");
        }
    }

    public void saveOrderToFile() {
        try {
            FileWriter fw = new FileWriter("orders.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Orders o : orderList) {
                bw.write(o.toString());
            }
            System.out.println("Save successfully");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Save failed - Please disable read-only in text file properties");
        }
    }

//End save function zone
    
//    Print function zone
    public void printProduct() {
        if (productList.isEmpty()) {
            System.out.println("Empty");
        }
        System.out.println("PRODUCT LIST");
        System.out.println("|Id |Name     |Quantity           |Origin       |Price     |");
        for (Products p : productList) {
            System.out.printf("%s,%s,%s,%s,%f\n",
                    p.getProductID(), p.getProductName(), p.getUnit(), p.getOrigin(), p.getPrice());

        }
    }

    public void printCustomer() {
        System.out.println("CUSTOMER LIST");
        if (customerList.isEmpty()) {
            System.out.println("Empty");
        }
        for (Customers c : customerList) {
            System.out.printf("%s,%s,%s,%s\n",
                    c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getCustomerPhone());
        }
    }

    public void printPendingOrder() {
        System.out.println("Pending order");
        for (Orders o : orderList) {
            if (o.getOrderStatus().equalsIgnoreCase("FALSE")) {
                System.out.println(o);
            }
        }
    }

    public void printOrderV2() {
        sortOrderByCustomerName();
        for (Orders o : orderList) {
            System.out.printf("%s,%s,%s,%d,%s,%s\n", o.getOrderID(), Validation.orderCustomerName(o.getCustomerID(), customerList, orderList), o.getProductID(), o.getOrderQuantity(), o.getOrderDate(), o.getOrderStatus());
        }
    }

    public void printOrder() {
        sortOrderByCustomerName();
        System.out.println("ORDER LIST");
        if (orderList.isEmpty()) {
            System.out.println("Empty");
        }
        for (Orders o : orderList) {
            System.out.print(o);
        }
    }
    //    End print function zone

    public void sortOrderByCustomerName() {
        Collections.sort(orderList, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return Validation.getOrderCustomerId(o1.getCustomerID(), orderList, customerList).compareTo(Validation.getOrderCustomerId(o2.getCustomerID(), orderList, customerList));
            }
        });

    }
//        End print function zone

    public void updateOrder() {
        String orderIdUpdate;
        String orderCustomerId, orderProductid, orderQuantityUpdate, orderDateUpdate, orderPendingUpdate;

        int count = 0;
        do {
            if (orderList.isEmpty()) {
                System.out.println("Can't update - The list is empty");
            }

            orderIdUpdate = Validation.getOrderId("Enter order's id you want to update (Dxxx): ", orderList, 2);

            for (Orders o : orderList) {

                if (orderIdUpdate.equals(o.getOrderID())) {

//                    Update order customer id
                    orderCustomerId = Validation.getOrderCustomerId("Enter order's customer's id to update (Cxxx): ", customerList, 2);
                    if (orderCustomerId.equals("") || orderCustomerId.equals(" ")) {
                        o.setCustomerID(o.getCustomerID());
                    } else {
                        o.setCustomerID(orderCustomerId);
                    }
//                    End update customer id

//                    Update order product id
                    orderProductid = Validation.getProductId("Enter product's id to update (Pxxx):", productList, 2);
                    if (orderProductid.equals("") || orderProductid.equals(" ")) {
                        o.setProductID(o.getProductID());
                    } else {
                        o.setProductID(orderProductid);
                    }

//                      End update order product id
//                  Update order quantity
                    while (true) {
                        System.out.print("Enter order quantity to update: ");
                        orderQuantityUpdate = sc.nextLine();
                        if (orderQuantityUpdate.equals("") || orderQuantityUpdate.equals(" ")) {
                            o.setOrderQuantity(o.getOrderQuantity());
                            break;
                        } else if (orderQuantityUpdate.matches(("^([0-9]){1,15}$"))) {
                            o.setOrderQuantity(Integer.parseInt(orderQuantityUpdate));
                            break;
                        } else {
                            System.out.println("Please input integer number: ");
                        }
                    }
//               End update order quantity

                    orderDateUpdate = Validation.checkValidDate("Enter order's date to update (dd/mm/yy): ", 2);
                    if (orderDateUpdate.equals("") || orderDateUpdate.equals(" ")) {
                        o.setOrderDate(o.getOrderDate());
                    } else {
                        o.setOrderDate(orderDateUpdate);
                    }

//                    Update oreder pending
                    orderPendingUpdate = Validation.getOrderPending("Enter order pending \n 1 or blank = False  2 = True: ", 2);
                    if (orderPendingUpdate.equals("") || orderPendingUpdate.equals(" ")) {
                        o.setOrderStatus(o.getOrderStatus());
                    } else {
                        o.setOrderStatus(orderPendingUpdate);
                    }

                    saveOrderToFile();
                }

            }
            break;
        } while (true);
    }

    public void updateCustomer() {
        String customerUpdate;
        String customerNameUpdate, customerAddressForUpdate, customerPhoneUpdate;

        do {
            if (customerList.isEmpty()) {
                System.out.println("Can't update - The list is empty!");
            }
            customerUpdate = Validation.getCustomerId("Enter customer id you want to update:", customerList, 2);

            for (Customers c : customerList) {
                if (customerUpdate.equals(c.getCustomerId())) {
//                    Update name
                    customerNameUpdate = Validation.getCustomerNameForUpdate("Enter name to update:");
                    if (customerNameUpdate.equals("")) {
                        c.setCustomerName(c.getCustomerName());
                    } else {
                        c.setCustomerName(customerNameUpdate);
                    }
//                    End update name

//                Update address
                    customerAddressForUpdate = Validation.getCustomerAddressForUpdate("Enter customer address for update:");
                    if (customerAddressForUpdate.equals("") || customerAddressForUpdate.equals(" ")) {
                        c.setCustomerAddress(c.getCustomerAddress());
                    } else {
                        c.setCustomerAddress(customerAddressForUpdate);
                    }
//                    End update name

//                    Update phone number
                    customerPhoneUpdate = Validation.getCustomerPhoneUpdate("Enter customer's phone to update:");
                    if (customerPhoneUpdate.equals("") || customerPhoneUpdate.equals(" ")) {
                        c.setCustomerPhone(c.getCustomerPhone());

                    } else {
                        c.setCustomerPhone(customerPhoneUpdate);
                    }

                    saveCustomerToFile();

                }
            }
            break;
        } while (true);
    }

    public void deleteOrder() {
        String orderId;
        String ask;
        String pattern = "^[nN]";
        orderId = Validation.getOrderId("Enter order's id you want to delete: ", orderList, 3);
        System.out.print("You sure you want to delete?\n(n/N) to cancel or press any key to delete: ");
        ask = 
                sc.nextLine();
        if (!ask.matches(pattern)) {
            for (Orders o : orderList) {
                if (o.getOrderID().equals(orderId)) {
                    orderList.remove(o);
                    break;
                }
            }

        } else {
            System.out.println("Thank you <3");
        }

        saveOrderToFile();
    }
}
