package coursework;

public class FuelRepository {

    //Creating variables
    private String fuelType;
    private float fuelLeft;
    private float capacity;

    //Creating constructors for variables
    public FuelRepository(String fuelType, float capacity){
        this.fuelType = fuelType;
        this.capacity = capacity;
        fuelLeft = 0;
    }

    public void addFuel(float fuel){
        fuelLeft += fuel;
    }

    public float checkFuelLeft(){
        return fuelLeft;
    }

    //Creating getters for fuelType
    public String getFuelType() {
        return fuelType;
    }

    //Creating getters for capacity
    public float getCapacity() {
        return capacity;
    }
}
