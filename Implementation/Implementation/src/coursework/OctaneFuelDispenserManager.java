package coursework;

import java.util.ArrayList;

public class OctaneFuelDispenserManager implements FuelDispenseManager {

    //Define variables
    private static ArrayList<Data> dataArray = new ArrayList<>();
    private static int totalNumberOfVehiclesServed = 0;
    private int dispense_ID;
    private static float totalProfit = 0;
    private Queue_Management_System fuelQueue;
    private FuelRepository repository;
    private static float pricePerLiter;
    private String fuelType;

    //Creating constructors for variables
    public OctaneFuelDispenserManager(int dispense_ID,Queue_Management_System fuelQueue, FuelRepository repository, float pricePerLiter, String fuelType) {
        this.fuelQueue = fuelQueue;
        this.repository = repository;
        OctaneFuelDispenserManager.pricePerLiter = pricePerLiter;
        this.fuelType = fuelType;
        this.dispense_ID = dispense_ID;
    }
    @Override
    public void serveCustomer(DateTime date)  {
        DB_Connector dbConnector = new DB_Connector();
        dbConnector.connect();
        Data data = new Data(this.dispense_ID,fuelQueue.getFirstCustomer(), date);
        dataArray.add(data);
        incrementVehiclesServed();
        incrementProfit(fuelQueue.getFirstCustomer());
        fuelQueue.removeCustomer(0);
        this.repository.addFuel(-data.getCustomer().getFuel_Needed());
        dbConnector.reducePetrolStock(data.getCustomer().getFuel_Needed());
    }

    public static void addData(Data data){
        dataArray.add(data);
    }

    public static ArrayList<Data> getDataArray() {
        return dataArray;
    }

    public static Customer getHighestAmountOfFuel(DateTime date){
        Customer highestAmountOfFuel = new Customer(1, 1, 15, false);
        for (Data data : dataArray) {
            if (data.getDateTime() == date) {
                if (data.getCustomer().getFuel_Needed() > highestAmountOfFuel.getFuel_Needed()) {
                    highestAmountOfFuel = data.getCustomer();
                }
            }
        }
        return highestAmountOfFuel;
    }
    public static void addDataArray(ArrayList<Data>dataArrayList){
        dataArray = dataArrayList;
    }
    public static float totalFuelDispensed(int vehicleType){
        float totalFuelDispensed = 0;
        for (Data data : dataArray) {
            if (data.getCustomer().getVehicle_type() == (vehicleType)) {
                totalFuelDispensed += data.getCustomer().getFuel_Needed();
            }
        }
        return totalFuelDispensed;
    }

    public static float totalProfit(DateTime date){
        float profit = 0;
        for (Data data : dataArray) {
            if (data.getDateTime() == date) {
                profit += data.getCustomer().getFuel_Needed() * pricePerLiter;
            }
        }
        return profit;
    }

    public static void incrementVehiclesServed(){
        totalNumberOfVehiclesServed++;
    }

    //Creating getters for totalNumberOfVehiclesServed
    public static int getVehiclesServed(){
        return totalNumberOfVehiclesServed;
    }

    public static void incrementProfit(Customer customer){
        totalProfit += customer.getFuel_Needed()*pricePerLiter;
    }

    //Creating getters for totalProfit
    public static float getProfitPerDispenser(){
        return totalProfit;
    }

    public int getDispense_ID() {
        return dispense_ID;
    }

    public void setDispense_ID(int dispense_ID) {
        this.dispense_ID = dispense_ID;
    }

    public float checkFuelLeft(){
        return repository.checkFuelLeft();
    }

    //Creating getters for pricePerLiter
    public static float getPricePerLiter() {
        return pricePerLiter;
    }

    //Creating getters for fuelType
    public String getFuelType() {
        return fuelType;
    }
}
