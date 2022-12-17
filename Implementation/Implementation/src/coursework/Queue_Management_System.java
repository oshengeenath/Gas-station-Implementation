package coursework;

public class Queue_Management_System extends Queue{

    //Defining variables
    private final int maximumCustomers = 10;
    private int noOfCustomers;
    private String[] vehiclesAllowed;
    private WaitingQueue waitingQueue;

    //Creating constructors for variables
    public Queue_Management_System(String[] vehiclesAllowed, WaitingQueue waitingQueue){
        super();
        this.noOfCustomers = 0;
        this.vehiclesAllowed = vehiclesAllowed;
        this.waitingQueue = waitingQueue;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (noOfCustomers>=maximumCustomers) {
            if (checkVehicleType(customer)) {
                super.addCustomer(customer);
                noOfCustomers++;
            }
            else {
                System.out.println("Vehicle not allowed in the Queue");
            }
        } else {
            System.out.println("Queue is full");
            waitingQueue.addCustomer(customer);
            System.out.println("Added the customer to the fuel Queue");
        }
    }

    //can use to remove the served customer and remove a selected customer
    public void removeCustomer(int index){
        customers.remove(index);
        noOfCustomers--;
    }
    //Creating getters for customer
    public Customer getFirstCustomer(){
        return customers.get(0);
    }

    public int getFreeSpace(){
        return maximumCustomers-noOfCustomers;
    }

    private boolean checkVehicleType(Customer customer){
        for (String s : vehiclesAllowed) {
            if (s.equals(customer.getVehicle_type())) {
                return true;
            }
        }
        return false;
    }

    public String[] getVehiclesAllowed() {
        return vehiclesAllowed;
    }
}
