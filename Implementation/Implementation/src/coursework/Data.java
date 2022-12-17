package coursework;

public class Data {

    //Creating variables
    private Customer customer;
    private DateTime dateTime;

    //Creating constructors for variables
    public Data(Customer customer, DateTime dateTime) {
        this.customer = customer;
        this.dateTime = dateTime;
    }

    //Creating getters and setters for Customer
    public Customer getCustomer() {
        return customer;
    }

    //Creating getters and setters for dateTime
    public DateTime getDateTime() {
        return dateTime;
    }
}
