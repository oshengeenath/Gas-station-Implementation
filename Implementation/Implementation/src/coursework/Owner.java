package coursework;

import java.util.ArrayList;

public class Owner {

    //Defining Variables
    private ArrayList<OctaneFuelDispenserManager> petrolDispensers = new ArrayList<>();
    private ArrayList<DieselFuelDispenserManager> dieselDispenser = new ArrayList<>();
    private FuelRepository petrolRepository;
    private FuelRepository dieselRepository;

    //Creating constructors for the variables
    public Owner(FuelRepository petrolRepository, FuelRepository dieselRepository){
        this.petrolRepository = petrolRepository;
        this.dieselRepository = dieselRepository;
    }

    public void addPetrolDispenser(OctaneFuelDispenserManager dispenser){
        petrolDispensers.add(dispenser);
    }

    public void addDieselDispenser(DieselFuelDispenserManager dispense){
        dieselDispenser.add(dispense);
    }

    //Creating getters for petrolDispenser
    public ArrayList<OctaneFuelDispenserManager> getPetrolDispensers() {
        return petrolDispensers;
    }

    //Creating getters for dieselDispenser
    public ArrayList<DieselFuelDispenserManager> getDieselDispenser() {
        return dieselDispenser;
    }

    //Creating getters for petrolRepository
    public FuelRepository getPetrolRepository() {
        return petrolRepository;
    }

    //Creating getters and setters for dieselRepository
    public FuelRepository getDieselRepository() {
        return dieselRepository;
    }
}
