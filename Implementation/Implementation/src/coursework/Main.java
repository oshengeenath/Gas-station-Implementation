package coursework;

public class Main {

    public static void main(String[] args) {DB_Connector db = new DB_Connector();
        db.connect();
        Owner owner = db.getOwner();
        System.out.println(owner.getPetrolRepository().getCapacity());
        System.out.println(owner.getDieselDispenser().get(0).getFuelType());
        System.out.println(owner.getDieselRepository().getCapacity());
        System.out.println(owner.getPetrolDispensers());
        //initialize();
    }

    private static void initialize(){

        FuelRepository petrolRepository = new FuelRepository("petrol", 25000);
        FuelRepository dieselRepository = new FuelRepository("diesel", 25000);
        Owner owner = new Owner(petrolRepository, dieselRepository);
        WaitingQueue waitingQueue = new WaitingQueue();

        //------------------Creating fuel queue--------------------------
        //------------------Creating petrol queue------------------------
        //92octane queue number 1 for Cars and Vans
        Queue_Management_System petrolQueue1 = new Queue_Management_System(new String[]{"car", "van"}, waitingQueue);
        //92octane queue number 2 for petrol vehicles except ThreeWheeler and motorBike
        Queue_Management_System petrolQueue2 = new Queue_Management_System(new String[]{"car", "van", "Other petrol vehicle except threeWheeler and motorBike"}, waitingQueue);
        //92octane queue number 3 for threeWheeler
        Queue_Management_System petrolQueue3 = new Queue_Management_System(new String[]{"threeWheeler"}, waitingQueue);
        //92octane queue number 4 for motorBike
        Queue_Management_System petrolQueue4 = new Queue_Management_System(new String[]{"motorBike"}, waitingQueue);

        //-------------------creating diesel queue------------------------
        //Diesel queue number 1 for publicTransport vehicles
        Queue_Management_System dieselQueue1 = new Queue_Management_System(new String[]{"publicTransport"}, waitingQueue);
        //Diesel queue number 2 for other diesel vehicles
        Queue_Management_System dieselQueue2 = new Queue_Management_System(new String[]{"other"}, waitingQueue);
        //Diesel queue number 3 for other diesel vehicles
        Queue_Management_System dieselQueue3 = new Queue_Management_System(new String[]{"other"}, waitingQueue);

        //--------------------Petrol dispensers-----------------------------
        OctaneFuelDispenserManager petrolDispenser1 = new OctaneFuelDispenserManager(petrolQueue1, petrolRepository, 450, "petrol");
        OctaneFuelDispenserManager petrolDispenser2 = new OctaneFuelDispenserManager(petrolQueue2, petrolRepository, 450, "petrol");
        OctaneFuelDispenserManager petrolDispenser3 = new OctaneFuelDispenserManager(petrolQueue3, petrolRepository, 450, "petrol");
        OctaneFuelDispenserManager petrolDispenser4 = new OctaneFuelDispenserManager(petrolQueue4, petrolRepository, 450, "petrol");

        owner.addPetrolDispenser(petrolDispenser1);
        owner.addPetrolDispenser(petrolDispenser2);
        owner.addPetrolDispenser(petrolDispenser3);
        owner.addPetrolDispenser(petrolDispenser4);

        //---------------------------Diesel dispensers----------------------------------
        DieselFuelDispenserManager dieselDispenser1 = new DieselFuelDispenserManager(dieselQueue1, dieselRepository, 430, "diesel");
        DieselFuelDispenserManager dieselDispenser2 = new DieselFuelDispenserManager(dieselQueue2, dieselRepository, 430, "diesel");
        DieselFuelDispenserManager dieselDispenser3 = new DieselFuelDispenserManager(dieselQueue3, dieselRepository, 430, "diesel");


        owner.addDieselDispenser(dieselDispenser1);
        owner.addDieselDispenser(dieselDispenser2);
        owner.addDieselDispenser(dieselDispenser3);
    }
}



