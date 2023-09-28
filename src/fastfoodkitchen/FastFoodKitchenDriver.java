package fastfoodkitchen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * ITSC 1213 
 * University of North Carolina at Charlotte
 */

public class FastFoodKitchenDriver {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, InputMismatchException {

        try                                                                                     //try
        {
        
        FastFoodKitchen kitchen = new FastFoodKitchen();

       
        
        
        
        
        
       
        
          kitchen.header();
        
         Scanner sc = new Scanner(System.in);                                                       //scanner
        
        
        while (kitchen.getNumOrdersPending() != 0) {                                                                //condition for while loop
            // see what the user wants to do
            System.out.println("Please select from the following menu of options, by typing a number:");
            System.out.println("\t 1. Order food");
            System.out.println("\t 2. Cancel last order");
            System.out.println("\t 3. Show number of orders currently pending");                                //displays what user can do
            System.out.println("\t 4. Complete order");
            System.out.println("\t 5. Check on order");
            System.out.println("\t 6. Cancel order");
            System.out.println("\t 7. Exit");

            int num = sc.nextInt();                                                                         //scanner + switch statement to take user input
            switch (num) {
                case 1:
                    System.out.println("How many hamburgers do you want?");
                    int ham = sc.nextInt();
                    kitchen.addHamburger(ham);
                    System.out.println("How many cheeseburgers do you want?");
                    int cheese = sc.nextInt();
                    kitchen.addCheeseburger(cheese);
                    System.out.println("How many veggieburgers do you want?");
                    int veggie = sc.nextInt();                                                      //user adds an order
                    kitchen.addVeggieburger(veggie);
                    System.out.println("How many sodas do you want?");
                    int sodas = sc.nextInt();
                    kitchen.addSodas(sodas);
                    System.out.println("Is your order to go? (Y/N)");
                    char letter = sc.next().charAt(0);
                    boolean TOGO = false;
                    if (letter == 'Y' || letter == 'y') {
                        TOGO = true;
                    }
                    int orderNum = kitchen.addOrder(ham, cheese, veggie, sodas, TOGO);
                    System.out.println("Thank you. Your order number is " + orderNum);
                    System.out.println();
                    break;
                case 2:
                    boolean ready = kitchen.cancelLastOrder();
                    if (ready) {
                        System.out.println("Thank you. The last order has been canceled");
                    } else {                                                                        //cancel last order
                        System.out.println("Sorry. There are no orders to cancel.");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("There are " + kitchen.getNumOrdersPending() + " pending orders");       //how many orders left
                    break;
                case 4:
                    Thread.sleep(1000);
                    System.out.println("Enter order number to complete?");                          //completes specific order
                    int order = sc.nextInt();
                     kitchen.completedOrder(order);                                             //prints it to the report file
                    
                    kitchen.completeSpecificOrder(order);                                       //removes order from arraylist
                    System.out.println("Your order is ready. Thank you!");
                    break;
                case 5:
                    System.out.println("What is your order number?");
                    order = sc.nextInt();
                    ready = kitchen.isOrderDone(order);                                                 //checks if order is done or not
                    if (ready) {
                        System.out.println("Sorry, no order with this number was found.");                              
                    } else {
                        System.out.println("No, it's not ready, but it should be up soon. Sorry for the wait.");
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("What is your order number?");
                    order = sc.nextInt();
                    boolean cancel = kitchen.cancelOrder(order);                                                    //cancels specific order
                    if (cancel) {
                        System.out.println("Your order has been successfully cancelled ");
                    } else {
                        System.out.println("Sorry, we can’t find your order number in the system");
                    }
                    System.out.println();
                    break;
                case 7:
                    
                   kitchen.ender();
                   kitchen.day2();                                                                  //prints the required text files and exits
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sorry, but you need to enter a 1, 2, 3, 4, 5, 6, or a 7");          //if user doesn't enter a right option 

            } //end switch

        } //end while loop 
        }
        catch (InputMismatchException e){
            System.out.println("Input Mismatch Exception. Please recheck your input. You need to enter an integer according to the options.");
            
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception, recheck your file location and address");
            
        }
        
        catch (IOException e){
            System.out.println("IOException error");                                                            //catching different types of errors
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException.");
        }
        catch (Exception e){
            System.out.println("There is a general exception.");
        }
     
    } // end main
}// end class

