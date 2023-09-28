package fastfoodkitchen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;                                                         //imports
import java.io.PrintWriter;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * ITSC 1213 University of North Carolina at Charlotte
 */
public class FastFoodKitchen {

    private ArrayList<BurgerOrder> orderList = new ArrayList();

    private int TotalHamburgers = 0;
    private int TotalCheeseburgers = 0;
    private int TotalVeggieburgers = 0;                                            //initialize varible for keeping track of total items
    private int TotalSodas = 0;

    private static int nextOrderNum = 1;                                            //tracks order numbers

    Scanner listScanner;
    String s;
    BurgerOrder ORDER;

    FileOutputStream fs = new FileOutputStream("EndOfDayReport.txt");
    PrintWriter outFS = new PrintWriter(fs);                                      //two printwrites and outputstreams for the 2 files

    FileOutputStream fs2 = new FileOutputStream("BurgerOrderDay2.txt");
    PrintWriter outFS2 = new PrintWriter(fs2);

    FastFoodKitchen() throws FileNotFoundException {
        try {
            this.listScanner = new Scanner(new File("burgerOrders.csv"));          //file path to import orders
            listScanner.nextLine();
            while (listScanner.hasNext()) {
                s = listScanner.nextLine();
                incrementNextOrderNum();
                String[] a = s.split(",");                                                                      //reading from file after splitting commas

                ORDER = new BurgerOrder(parseInt(a[1]), parseInt(a[2]), parseInt(a[3]), parseInt(a[4]), parseBoolean(a[5]), parseInt(a[0]));
                TotalHamburgers += parseInt(a[1]);
                TotalCheeseburgers += parseInt(a[2]);
                TotalVeggieburgers += parseInt(a[3]);
                TotalSodas += parseInt(a[4]);                                                               //converting string from the csv file into suitable data type to enter 
                //into the array orderList as BurgerOrders
                orderList.add(ORDER);

            }
        } catch (FileNotFoundException e) {
            System.out.println("The file path is probably wrong. Please double check it.");                         //catch
        }

        /* orderList.add(new BurgerOrder(3, 15, 4, 10, false, getNextOrderNum()));
        incrementNextOrderNum();
        orderList.add(new BurgerOrder(10, 10, 3, 3, true, getNextOrderNum()));
        incrementNextOrderNum();
        orderList.add(new BurgerOrder(1, 1, 1, 2, false, getNextOrderNum()));
        incrementNextOrderNum(); */
    }

    public void addHamburger(int ham) {
        TotalHamburgers += ham;
    }

    public void addCheeseburger(int cheese) {
        TotalCheeseburgers += cheese;
    }
                                                                                        //methods for adding into totals of items

    public void addVeggieburger(int veg) {
        TotalVeggieburgers += veg;
    }

    public void addSodas(int soda) {
        TotalSodas += soda;
    }

    public void subtractTotalHamburgers(int orderID) {
        TotalHamburgers -= orderList.get(orderID - 1).getNumHamburger();
    }

    public void subtractTotalVeggieburgers(int orderID) {                               //subtracting from totals in case the users cancels any order
        TotalVeggieburgers -= orderList.get(orderID - 1).getNumVeggieburgers();
    }

    public void subtractTotalCheeseburgers(int orderID) {
        TotalCheeseburgers -= orderList.get(orderID - 1).getNumCheeseburgers();
    }

    public void subtractTotalSodas(int orderID) {
        TotalSodas -= orderList.get(orderID - 1).getNumSodas();
    }

    public int getTotalHamburgers() {
        return TotalHamburgers;
    }

    public int getTotalCheeseburgers() {
        return TotalCheeseburgers;
    }

    public int getTotalVeggieburgers() {
        return TotalVeggieburgers;                                                     //getters for totals of items
    }

    public int getTotalSodas() {
        return TotalSodas;
    }

    public void header() throws IOException {

        outFS.println("                   ********** End Of Day Report **********");
        outFS.println(" ");
        outFS.println(" ");
        outFS.println("---- Completed Orders ----");
        outFS.println(" ");                                                                 //header of the end of day report file printed
        outFS.println(" ");
        outFS.flush();

    }

    public void completedOrder(int order) throws IOException {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == order) {
                outFS.println(orderList.get(i).toString());                            //finds the order that the user wants to complete and prints it before removing it from arraylist
            }
        }

        outFS.println(" ");
        outFS.flush();

    }

    public void ender() throws IOException {
        outFS.println("---- Orders Remaining ----");
        outFS.println(" ");
        outFS.println(" ");
                                                                                    //prints all the orders left after completion at the end of the report
        for (int i = 0; i < orderList.size(); i++) {
            outFS.println(orderList.get(i).toString());
        }

        outFS.println(" ");
        outFS.println(" ");
        outFS.println("Total Hamburger orders: " + TotalHamburgers);
        outFS.println("Total Cheeseburger orders: " + TotalCheeseburgers);
        outFS.println("Total Veggieburger orders: " + TotalVeggieburgers);
        outFS.println("Total Soda orders: " + TotalSodas);                          //prints the total for all items in the report file
        outFS.flush();
        outFS.close();
        fs.close();

    }

    public void day2() throws IOException {
        outFS2.println("orderID,numHamburgers,numCheeseburgers,numVeggieburgers,numSodas,toGO");
        for (int i = 0; i < orderList.size(); i++) {
            outFS2.println(orderList.get(i).toString2());
        }
        outFS2.flush();                                                                                 //method to print remaining orders as csv for day 2 orders
        outFS2.close();
        fs2.close();

    }

    public static int getNextOrderNum() {                                                   
        return nextOrderNum;
    }

    private void incrementNextOrderNum() {
        nextOrderNum++;
    }

    public int addOrder(int ham, int cheese, int veggie, int soda, boolean toGo) {              //alows user to add order
        int orderNum = getNextOrderNum();
        orderList.add(new BurgerOrder(ham, cheese, veggie, soda, toGo, orderNum));
        incrementNextOrderNum();
        orderCallOut(orderList.get(orderList.size() - 1));
        return orderNum;

    }

    public boolean isOrderDone(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {                                //check status of order
            if (orderList.get(i).getOrderNum() == orderID) {
                return false;
            }
        }
        return true;
    }

    public boolean cancelOrder(int orderID) {                                       //allows user to cancel an order
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                subtractTotalHamburgers(orderID);
                subtractTotalCheeseburgers(orderID);
                subtractTotalVeggieburgers(orderID);
                subtractTotalSodas(orderID);
                orderList.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getNumOrdersPending() {                                              //how many orders left
        return orderList.size();
    }

    public boolean cancelLastOrder() {

        if (!orderList.isEmpty()) { // same as  if (orderList.size() > 0) 
            subtractTotalHamburgers(orderList.size());
            subtractTotalCheeseburgers(orderList.size());
            subtractTotalVeggieburgers(orderList.size());                               //cancels most recent order
            subtractTotalSodas(orderList.size());
            orderList.remove(orderList.size() - 1);
            return true;
        }

        return false;
    }

    private void orderCallOut(BurgerOrder order) {
        if (order.getNumCheeseburgers() > 0) {
            System.out.println("You have " + order.getNumHamburger() + " hamburgers");
        }
        if (order.getNumCheeseburgers() > 0) {                                                      //calls out order
            System.out.println("You have " + order.getNumCheeseburgers() + " cheeseburgers");
        }
        if (order.getNumVeggieburgers() > 0) {
            System.out.println("You have " + order.getNumVeggieburgers() + " veggieburgers");
        }
        if (order.getNumSodas() > 0) {
            System.out.println("You have " + order.getNumSodas() + " sodas");
        }

    }

    public void completeSpecificOrder(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {                        //completes order based on the order ID user enters
                System.out.println("Order number " + orderID + " is done!");
                if (orderList.get(i).isOrderToGo()) {
                    orderCallOut(orderList.get(i));
                }
                orderList.remove(i);
            }
        }

    }

    public void completeNextOrder() {
        int nextOrder = orderList.get(0).getOrderNum();
        completeSpecificOrder(nextOrder);

    }

    // Part 2
    public ArrayList<BurgerOrder> getOrderList() {
        return orderList;                                                               //returns arraylist
    }

    public int findOrderSeq(int whatWeAreLookingFor) {
        for (int j = 0; j < orderList.size(); j++) {
            if (orderList.get(j).getOrderNum() == whatWeAreLookingFor) {
                return j;
            }
        }
        return -1;
    }

//    public int findOrderBin(int whatWeAreLookingFor) {
//        int left = 0;
//        int right = orderList.size() - 1;
//        while (left <= right) {
//            int middle = (left + right) / 2;
//            if (whatWeAreLookingFor < orderList.get(middle).getOrderNum()) {
//                right = middle - 1;
//            } else if (whatWeAreLookingFor > orderList.get(middle).getOrderNum()) {
//                left = middle + 1;
//            } else {
//                return middle;
//            }
//        }
//        return -1;
//    }
    public int findOrderBin(int orderID) {
        int left = 0;
        int right = orderList.size() - 1;
        while (left <= right) {
            int middle = ((left + right) / 2);
            if (orderID < orderList.get(middle).getOrderNum()) {
                right = middle - 1;
            } else if (orderID > orderList.get(middle).getOrderNum()) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;

    }

    public void selectionSort() {
        for (int i = 0; i < orderList.size() - 1; i++) {
            int minIndex = i;
            for (int k = i + 1; k < orderList.size(); k++) {
                if (orderList.get(minIndex).getTotalBurgers() > orderList.get(k).getTotalBurgers()) {
                    minIndex = k;
                }
            }
            BurgerOrder temp = orderList.get(i);
            orderList.set(i, orderList.get(minIndex));
            orderList.set(minIndex, temp);
        }
    }

    public void insertionSort() {
        for (int j = 1; j < orderList.size(); j++) {
            BurgerOrder temp = orderList.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.getTotalBurgers() < orderList.get(possibleIndex - 1).getTotalBurgers()) {
                orderList.set(possibleIndex, orderList.get(possibleIndex - 1));
                possibleIndex--;
            }
            orderList.set(possibleIndex, temp);
        }
    }

//    public void selectionSort() { //weird method!
//
//        for (int j = 0; j < orderList.size() - 1; j++) {
//            int minIndex = j;
//            for (int k = j + 1; k < orderList.size(); k++) {
//
//                 if (orderList.get(minIndex).getTotalBurgers() > orderList.get(j).getTotalBurgers()){
//                    minIndex = k;
//                }
//            }
//            BurgerOrder temp = orderList.get(j);
//            orderList.set(j, orderList.get(minIndex));
//            orderList.set(minIndex, temp);
//
//        }
//    }
}
