package coursework;

public class Customer {

    //Creating variables
    private int vehicle_type;
    private int fuel_type;
    private float fuel_Needed;
    private boolean got_Ticket;

    //Creating constructors for variables
    public Customer(int vehicle_type, int fuel_type, float fuel_Needed, boolean got_Ticket) {
        this.vehicle_type = vehicle_type;
        this.fuel_type = fuel_type;
        this.fuel_Needed = fuel_Needed;
        this.got_Ticket = got_Ticket;
    }
    //Creating getters and setters for vehicle_type
    public int getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(int vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    //Creating getters and setters for fuel_type
    public int getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(int fuel_type) {
        this.fuel_type = fuel_type;
    }

    //Creating getters and setters for fuel_Needed
    public float getFuel_Needed() {
        return fuel_Needed;
    }

    public void setFuel_Needed(float fuel_Needed) {
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

