package coursework;

public class Customer {

    //Creating variables
    private String vehicle_type;
    private String fuel_type;
    private double fuel_Needed;
    private boolean got_Ticket;

    //Creating constructors for variables
    public Customer(String vehicle_type, String fuel_type, double fuel_Needed, boolean got_Ticket) {
        this.vehicle_type = vehicle_type;
        this.fuel_type = fuel_type;
        this.fuel_Needed = fuel_Needed;
        this.got_Ticket = got_Ticket;
    }
    //Creating getters and setters for vehicle_type
    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    //Creating getters and setters for fuel_type
    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    //Creating getters and setters for fuel_Needed
    public double getFuel_Needed() {
        return fuel_Needed;
    }

    public void setFuel_Needed(double fuel_Needed) {
        this.fuel_Needed = fuel_Needed;
    }

    //Creating getters and setters for got_Ticket
    public boolean isGot_Ticket() {
        return got_Ticket;
    }

    public void setGot_Ticket(boolean got_Ticket) {
        this.got_Ticket = got_Ticket;
    }
}

