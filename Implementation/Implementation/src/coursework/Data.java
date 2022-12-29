package coursework;

public class Data {

    //Creating variables
    private Customer customer;
    private DateTime dateTime;
    private int dispense_ID;

    //Creating constructors for variables
    public Data(int dispense_ID, Customer customer, DateTime dateTime) {
        this.customer = customer;
        this.dateTime = dateTime;
        this.dispense_ID = dispense_ID;
    }

    //Creating getters and setters for Customer
    public Customer getCustomer() {
        return customer;
    }

    //Creating getters and setters for dateTime
    public DateTime getDateTime() {
        return dateTime;
    }

    public int getDispense_ID() {
        return dispense_ID;
    }

    public void setDispense_ID(int dispense_ID) {
        this.dispense_ID = dispense_ID;
    }
}
