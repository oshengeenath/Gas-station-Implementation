package coursework;

public class WaitingQueue extends Queue {
    public WaitingQueue() {
        super();
    }

    public Customer sendCustomerToFuelQueue(){
        Customer customer = customers.get(0);
        customers.remove(0);
        return customer;
    }
}
