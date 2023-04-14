/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import objectholder.Customers;
import objectholder.Orders;
import objectholder.Products;

/**
 *
 * @author USER
 */
public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public static String checkValidDate(String msg, int mode) {
        String pattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        String input;
        while (39 < 79) {
            System.out.print(msg);
            input = sc.nextLine();
            if ((input.equals("") || input.equals(" ")) && mode == 2) {
                return input;
            } else {
                if (input.matches(pattern) && mode == 1 || input.matches(pattern) && mode == 2) {
                    return input;
                } else {
                    System.out.println("Invalid date or Wrong format\nPlease input like this format dd/mm/yy");
                }
            }

        }

    }

    public static String getOrderId(String msg, List<Orders> orderList, int mode) {
        String pattern = "(D)[0-9][0-9][0-9]";
        String orderId;
        while (39 < 79) {
            System.out.print(msg);
            orderId = sc.nextLine();
            if (orderId.matches(pattern)) {
                if (idOrderExist(orderId, orderList) && mode == 1) {
                    System.out.println("Can't add because order Id already exist");
                } else if (!idOrderExist(orderId, orderList) && mode == 2) {
                    System.out.println("Can't update because order Id not exist");
                } else if (!idOrderExist(orderId, orderList) && mode == 3) {
                    System.out.println("Can't delete because order Id not exist");
                } else if (idOrderExist(orderId, orderList) && mode == 2 || (!idOrderExist(orderId, orderList) && mode == 1) || (idOrderExist(orderId, orderList) && mode == 3)) {

                    return orderId;
                }

            } else {
                System.out.println("Wrong format or blank input!!");
            }

        }

    }

    public static String getOrderPending(String msg, int mode) {
        String orderPending;
        while (39 < 79) {
            System.out.print(msg);

            orderPending = sc.nextLine();
            if ((orderPending.equals("") || orderPending.equals(" ")) && mode == 2) {
                return orderPending;
            } else if ((("1".equals(orderPending) || ("".equals(orderPending) || " ".equals(orderPending))) && mode == 1) || ("1".equals(orderPending) && mode == 2)) {
                orderPending = "false";
                return orderPending;
            } else if ("2".equals(orderPending) && mode == 1 || "2".equals(orderPending) && mode == 2) {
                orderPending = "true";
                return orderPending;

            } else {
                System.out.println("Please enter 1-False or 2-True(Blank = False)");
            }

        }
    }

    public static String getProductId(String msg, List<Products> productList, int mode) {
//        mode 1 = "Check id product exisence"
//        mode 2 = add order product id
        String pattern = "(P)[0-9][0-9][0-9]";
        String productId;

        while (39 < 79) {
            System.out.print(msg);
            productId = sc.nextLine();
            if ((productId.equals("") || productId.equals(" ")) && mode == 2) {
                return productId;
            } else {
                if (productId.matches(pattern)) {
                    if (!idProductExist(productId, productList) && mode == 1 || (!idProductExist(productId, productList) && mode == 2)) {
                        System.out.println("Id not exist");
                    } else if (idProductExist(productId, productList) && mode == 1 || idProductExist(productId, productList) && mode == 2) {
                        return productId;
                    }

                } else {
                    System.out.println("Wrong format!!");
                }
            }

        }

    }

    public static String getCustomerId(String msg, List<Customers> customerList, int mode) {
//        mode 1 : add customer
//        mode 2 : update customer + add order

        String pattern = "(C)[0-9][0-9][0-9]";
        String id;

        while (true) {
            System.out.print(msg);
            id = sc.nextLine().toUpperCase();
            if (id.matches(pattern)) {
                if (idCustomerExist(id, customerList) && mode == 1) {
                    System.out.println("Id already exist");
                } else if (!idCustomerExist(id, customerList) && mode == 2) {
                    System.out.println("Id not exist");
                } else if ((!idCustomerExist(id, customerList) && mode == 1) || (idCustomerExist(id, customerList) && mode == 2)) {
                    return id;
                }

            } else {
                System.out.println("Wrong fornat or Blank input!!");
            }
        }

    }

    public static String getOrderCustomerId(String msg, List<Customers> customerList, int mode) {
//        mode 1 : add customer
//        mode 2 : update customer + add order

        String pattern = "(C)[0-9][0-9][0-9]";
        String id;

        while (true) {
            System.out.print(msg);
            id = sc.nextLine();
            if ((id.equals("") || id.equals(" ")) && mode == 2) {
                return id;
            }
            if (id.matches(pattern)) {
                if (!idCustomerExist(id, customerList) && mode == 1) {
                    System.out.println("Can't add because id not exist!!");
                } else if (!idCustomerExist(id, customerList) && mode == 2) {
                    System.out.println("Can't update because id not exist!!");
                } else if (idCustomerExist(id, customerList) && mode == 2 || (idCustomerExist(id, customerList) && mode == 1)) {
                    return id;
                }

            } else {
                System.out.println("Wrong fornat or blank input");
            }

        }

    }

    public static int getOrderQuantity(String msg) {
        int orderQuantity;
        while (true) {
            System.out.print(msg);
            try {
                orderQuantity = Integer.parseInt(sc.nextLine());
                if (orderQuantity < 0) {
                    System.out.println("Please input integer number");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please input integer number!!!\nDon't in put special character!!!");
            }
        }
        return orderQuantity;

    }

    private static boolean idProductExist(String orderId, List<Products> productList) {
        for (Products p : productList) {
            if (p.getProductID().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    private static boolean idOrderExist(String orderId, List<Orders> orderList) {
        for (Orders o : orderList) {
            if (o.getOrderID().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    private static boolean idCustomerExist(String customerId, List<Customers> customerList) {
        for (Customers c : customerList) {
            if (c.getCustomerId().equals(customerId)) {
                return true;
            }

        }
        return false;

    }

    public static String getCustomerNameForUpdate(String msg) {
        String input;
        while (true) {

            String patternForUpdate = "^([a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*){5,30}$";
            System.out.print(msg);
            input = sc.nextLine().toUpperCase();
            if (input.matches(patternForUpdate) || input.equals("")) {
                break;
            } else {
                System.out.println("Don't input number!!!");
            }
        }
        return input;
    }

    public static String getCustomerName(String msg) {
        String name;
        String pattern = "^([a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*)$";
        while (39 < 79) {
            System.out.print(msg);
            name = sc.nextLine().toUpperCase();
            if (name.equalsIgnoreCase("")) {
                System.out.println("Dont input blank!!!!");
            } else if (name.matches(pattern)) {
                break;
            } else {
                System.out.println("Wrong format please enter again");
            }

        }

        return name;
    }

    public static String getCustomerAddressForUpdate(String msg) {
        String address;
        String pattern = "[\\s\\S]*.";
        while (39 < 79) {
            System.out.print(msg);
            address = sc.nextLine().toUpperCase();
            if (address.equalsIgnoreCase("") || address.equalsIgnoreCase(" ")) {
                break;
            } else if (address.matches(pattern)) {
                break;
            }

        }
        return address;
    }

    public static String getCustomerAddress(String msg) {
        String address;
        String pattern = "[\\s\\S]*.";
        while (39 < 79) {
            System.out.print(msg);
            address = sc.nextLine().toUpperCase();
            if (address.equalsIgnoreCase("") || address.equalsIgnoreCase(" ")) {
                System.out.println("Dont input blank!!!!");
            } else if (address.matches(pattern)) {
                break;
            }

        }
        return address;
    }

    public static String getCustomerPhoneUpdate(String msg) {
        String phone;
        String pattern = "^[0-9\\-\\+]{9,15}$";
        while (39 < 79) {
            System.out.print(msg);
            phone = sc.nextLine();
            if (phone.equals("") || phone.equals(" ")) {
                break;
            } else if (phone.matches(pattern)) {
                break;
            }
        }
        return phone;
    }

    public static String getCustomerPhone(String msg) {
        String phone;
        String pattern = "^[0-9\\-\\+]{9,15}$";
        while (39 < 79) {
            System.out.print(msg);
            phone = sc.nextLine();
            if (phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(" ")) {
                System.out.println("Dont input blank!!!!");
            } else if (phone.matches(pattern) && (phone.length() >= 10 && 12 >= phone.length())) {
                break;
            } else {
                System.out.println("Please input in range 10 - 12");
            }
        }
        return phone;
    }

//    public static String getNameByCustomerId(String id, List<Customers> customerList) {
//        for (Customers c : customerList) {
//            if (c.getCustomerId().equals(id)) {
//                return c.getCustomerName();
//            }
//        }
//        return null;
//
//    }

    public static String getOrderCustomerId(String id, List<Orders> orderList, List<Customers> customerList) {
        for (Customers c : customerList) {
            if (c.getCustomerId().equals(id)) {
                return c.getCustomerName();
            }
        }
        return null;
    }

    public static String orderCustomerName(String id, List<Customers> cList, List<Orders> oList) {
        String name;
        for (Customers c : cList) {
            if (c.getCustomerId().equals(id)) {
                name = c.getCustomerName();
                return name;
            }
        }
        return null;
    }
}
