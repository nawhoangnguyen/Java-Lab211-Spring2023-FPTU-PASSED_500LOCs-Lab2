/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.util.Scanner;
import manager.Management;

/**
 *
 * @author USER
 */
public class Menu {

    Management m = new Management();
    private Scanner sc = new Scanner(System.in);
    int choice;

    public void load() {
        try {
            m.loadCustomerFromFile();
            m.loadOrderFromFile();
            m.loadProductFromFile();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void MenuDisplay() {
        System.out.println("|||==||==||==||==||==||==||==||==||==||==||==||");
        System.out.println("||               Order Management            ||");
        System.out.println("||-------------------------------------------||");
        System.out.println("||01.List all Products                       ||");
        System.out.println("||02.List all Customers                      ||");
        System.out.println("||03.Search a Customer based on his/her ID   ||");
        System.out.println("||04.Add a Customer                          ||");
        System.out.println("||05.Update Customer                         ||");
        System.out.println("||06.Print all Orders                        ||");
        System.out.println("||07.Print all pending Orders                ||");
        System.out.println("||08.Add new Orders                          ||");
        System.out.println("||09.Update order information                ||");
        System.out.println("||10.Delete order information                ||");
        System.out.println("||11.Quit                                    ||");
        System.out.println("|||==||==||==||==||==||==||==||==||==||==||==||");
        System.out.print("Your choice: ");
    }

    public void enterChoice() {
        load();
        do {
            while (39 < 79) {
                MenuDisplay();
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.print("Wrong choice\nPlease input your choice again\n");
                }
            }

            switch (choice) {
                case 1:
                    m.printProduct();
                    break;
                case 2:
                    m.printCustomer();
                    break;
                case 3:
                    boolean contCase3 = true;
                    do {
                        m.searchCustomer();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase3 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase3 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase3);
                    break;
                case 4:
                    boolean contCase4 = true;
                    do {
                        m.printCustomer();
                        m.addCustomer();
                        m.loadCustomerFromFile();
                        m.printCustomer();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase4 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase4 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase4);
                    break;

                case 5:
                    boolean contCase5 = true;
                    do {
                        m.printCustomer();
                        m.updateCustomer();
                        m.printCustomer();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase5 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase5 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase5);
                    break;
                case 6:
                    m.printOrderV2();
                    m.printOrder();
                    break;
                case 7:
                    m.printPendingOrder();
                    break;
                case 8:
                    boolean contCase8 = true;
                    do {
                        m.printOrder();
                        m.addOrder();
                        m.loadOrderFromFile();
                        m.printOrder();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase8 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase8 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase8);
                    break;
                case 9:
                    boolean contCase9 = true;

                    do {
                        m.printOrder();
                        m.updateOrder();
                        m.printOrder();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase9 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase9 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase9);
                    break;
                case 10:
                    boolean contCase10 = true;
                    do {
                        m.printOrder();
                        m.deleteOrder();
                        m.printOrder();
                        System.out.println("Press any key to go back to Menu or (N) to continue");
                        String s;
                        String pattern = "^[nN]";
                        boolean cont = true;

                        do {
                            s = sc.nextLine();

                            if (!s.matches(pattern)) {
                                cont = false;
                                contCase10 = false;
                                break;
                            } else if (s.matches(pattern)) {
                                contCase10 = true;
                                cont = false;
                            }

                        } while (cont);
                    } while (contCase10);
                    break;
                case 11:
                    System.out.println("Thank you <3");
                    System.exit(0);
                default:
                    System.out.println("Please enter 1 - 11");
            }

        } while (true);
    }
}
