package fastfoodkitchen;

/**
 *
 * ITSC 1213 
 * University of North Carolina at Charlotte
 */

public class BurgerOrder {

    private int numHamburgers = 0;
    private int numCheeseburgers = 0;
    private int numVeggieburgers = 0;
    private int numSodas = 0;                                                           //initialize fields
    private boolean orderToGo = false;
    private int orderNum = 1;

    /**
     *
     * @param numHamburgers
     * @param numCheeseburgers
     * @param numVeggieburgers
     * @param numSodas
     * @param orderToGo
     * @param orderNum
     */
    public BurgerOrder(int numHamburgers, int numCheeseburgers, int numVeggieburgers, int numSodas, boolean orderToGo, int orderNum) {
        this.numHamburgers = numHamburgers;
        this.numCheeseburgers = numCheeseburgers;
        this.numVeggieburgers = numVeggieburgers;
        this.numSodas = numSodas;                                                                       //constructor
        this.orderToGo = orderToGo;
        this.orderNum = orderNum;
    }

    /**
     * Get the value of numHamburgers
     *
     * @return the value of numHamburgers
     */
    public int getNumHamburger() {
        return numHamburgers;
    }

    /**
     * Set the value of numHamburgers
     *
     * @param numHamburgers new value of numHamburgers
     */
    public void setNumHamburger(int numHamburgers) {
        if (numHamburgers < 0) {
            System.out.println("Number of hamburgers must be 0 or more.");
        } else {
            this.numHamburgers = numHamburgers;
        }
    }                                                                                           //getters and setters here for all items

    /**
     * Get the value of numCheeseburgers
     *
     * @return the value of numCheeseburgers
     */
    public int getNumCheeseburgers() {
        return numCheeseburgers;
    }

    /**
     * Set the value of numCheeseburgers
     *
     * @param numCheeseburgers new value of numCheeseburgers
     */
    public void setNumCheeseburgers(int numCheeseburgers) {
        if (numCheeseburgers < 0) {
            System.out.println("Number of cheeseburgers must be 0 or more.");
        } else {
            this.numCheeseburgers = numCheeseburgers;
        }
    }

    /**
     * Get the value of numVeggieburgers
     *
     * @return the value of numVeggieburgers
     */
    public int getNumVeggieburgers() {
        return numVeggieburgers;
    }

    /**
     * Set the value of numVeggieburgers
     *
     * @param numVeggieburgers new value of numVeggieburgers
     */
    public void setNumVeggieburgers(int numVeggieburgers) {
        if (numVeggieburgers < 0) {
            System.out.println("Number of veggieburgers must be 0 or more.");
        } else {
            this.numVeggieburgers = numVeggieburgers;
        }
    }

    /**
     * Get the value of numSodas
     *
     * @return the value of numSodas
     */
    public int getNumSodas() {
        return numSodas;
    }

    /**
     * Set the value of numSodas
     *
     * @param numSodas new value of numSodas
     */
    public void setNumSodas(int numSodas) {
        this.numSodas = numSodas;
    }

    /**
     * Get the value of orderNum
     *
     * @return the value of orderNum
     */
    public int getOrderNum() {
        return orderNum;
    }
                                                                                                    //more getters and setters
    /**
     * Set the value of orderNum
     *
     * @param orderNum new value of orderNum
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * Get the value of orderToGo
     *
     * @return the value of orderToGo
     */
    public boolean isOrderToGo() {
        return orderToGo;
    }

    /**
     * Set the value of orderToGo
     *
     * @param orderToGo new value of orderToGo
     */
    public void setOrderToGo(boolean orderToGo) {
        this.orderToGo = orderToGo;
    }
     /**
     * Get the total number of burgers in an order
     *
     * @return the total number of burgers in an order
     */
    public int getTotalBurgers() {
        return numHamburgers + numCheeseburgers + numVeggieburgers;
    }

    @Override
    public String toString() {                                                                                                  //toString for a specific BurgerOrder
        return "Order Number: " + orderNum + " Hamburgers: " + numHamburgers + ", Cheeseburgers: " + numCheeseburgers
                + ", Veggieburgers: " + numVeggieburgers + ", Sodas: " + numSodas + ", orderToGo: " + orderToGo
                ;
    }
    
    public String toString2(){
        return orderNum + "," + numHamburgers + "," +numCheeseburgers +","+numVeggieburgers + ","+numSodas +","+orderToGo;      //toString for the day 2 file in specific format
    }

}
