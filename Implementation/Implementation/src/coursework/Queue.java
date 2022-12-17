package coursework;

import java.util.ArrayList;

public abstract class Queue {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    public Queue(){

    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void giveTicketToCustomer(Customer customer){
        customer.isGot_Ticket();
    }
}
