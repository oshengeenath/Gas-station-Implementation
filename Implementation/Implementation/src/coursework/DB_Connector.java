package coursework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DB_Connector { Connection con = null;
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cw", "root", "root");

        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void add_Customer(){
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cw", "root", "root");
            Statement statement = con.createStatement();
            System.out.println("Enter the ID : ");
            int customer_ID = scanner.nextInt();
            System.out.println("Enter vehicle number :" );
            int registration_No = scanner.nextInt();
            System.out.println("Enter the vehicle type : ");
            int vehicle_Type = scanner.nextInt();
            System.out.println("Enter the fuel type : ");
            int fuel_Type = scanner.nextInt();
            System.out.println("Enter the number of liters needed : ");
            float fuel_Needed = scanner.nextFloat();
            //Give the query

            statement.executeUpdate("INSERT INTO customer VALUES('"+customer_ID+"','"+registration_No+"','"+vehicle_Type+"','"+fuel_Type+"','"+fuel_Needed+"')");
            System.out.println("Values added successfully");
        } catch (Exception e){
            System.out.println("Invalid data please re-enter");
        }
    }

    public Owner getOwner() {
        Owner owner = null;
        FuelRepository petrolRepository = null;
        FuelRepository dieselRepository = null;
        try {
            Statement st = con.createStatement();

            ResultSet rsFuelRepository = st.executeQuery("select * from fuel_repository");
            while (rsFuelRepository.next()){
                if (rsFuelRepository.getString(2).equals("octane92")){
                    petrolRepository = new FuelRepository(rsFuelRepository.getString(2), rsFuelRepository.getFloat(3));
                } else {
                    dieselRepository = new FuelRepository(rsFuelRepository.getString(2), rsFuelRepository.getFloat(3));
                }
            }

            owner = new Owner(petrolRepository, dieselRepository);
            WaitingQueue waitingQueue = new WaitingQueue();

            ResultSet rsPetrolDispenser = st.executeQuery("select * from 92octane_dispenser");
            while (rsPetrolDispenser.next()){
                String[] vehiclesAllowed = rsPetrolDispenser.getString(4).split(" ");
                Queue_Management_System petrolQueue = new Queue_Management_System(vehiclesAllowed, waitingQueue);

                OctaneFuelDispenserManager petrolDispenser = new OctaneFuelDispenserManager(rsPetrolDispenser.getInt(1) ,petrolQueue, petrolRepository, rsPetrolDispenser.getFloat(2), "octane92");

                owner.addPetrolDispenser(petrolDispenser);
            }

            ResultSet rsDieselDispenser = st.executeQuery("select  * from diesel_dispenser");
            while (rsDieselDispenser.next()){
                String[] vehiclesAllowed = rsDieselDispenser.getString(4).split(" ");
                Queue_Management_System dieselQueue = new Queue_Management_System(vehiclesAllowed, waitingQueue);

                DieselFuelDispenserManager dieselDispener = new DieselFuelDispenserManager(rsDieselDispenser.getInt(1),dieselQueue, dieselRepository, rsDieselDispenser.getFloat(2), "diesel");

                owner.addDieselDispenser(dieselDispener);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        addDataArray();
        return owner;
    }

    public void addPetrolDispenser(int dispenserNo, float pricePerLiter, String vehiclesAllowed){
        String query = "insert into petrol_dispenser values (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1 , dispenserNo);
            pst.setFloat(2, pricePerLiter);
            pst.setInt(3, 1);
            pst.setString(4, vehiclesAllowed);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addDieselDispenser(int dispenserNo, float pricePerLiter, String vehiclesAllowed){
        String query = "insert into diesel_dispenser values (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1 , dispenserNo);
            pst.setFloat(2, pricePerLiter);
            pst.setInt(3, 2);
            pst.setString(4, vehiclesAllowed);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void addDataArray(){
        ArrayList<Data> petrolDataArray = new ArrayList<>();
        ArrayList<Data> dieselDataArray = new ArrayList<>();
        ArrayList<Integer> customerIDs = new ArrayList<>();
        ArrayList<DateTime> dateTimes = new ArrayList<>();
        ArrayList<String> dispenserIds = new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rsServedInfo = st.executeQuery("select * from served_info");
            while (rsServedInfo.next()){
                DateTime dateTime = new DateTime(rsServedInfo.getInt(3), rsServedInfo.getInt(4), rsServedInfo.getInt(5));
                dateTimes.add(dateTime);
                customerIDs.add(rsServedInfo.getInt(2));
                dispenserIds.add(rsServedInfo.getString(6));
            }
            for (int i=0; i<customerIDs.size(); i++) {
                ResultSet rsCustomer = st.executeQuery("SELECT * FROM customer WHERE customer_ID=" + customerIDs.get(i));
                rsCustomer.next();
                Customer customer = new Customer( rsCustomer.getInt(3), rsCustomer.getInt(4), rsCustomer.getFloat(5), true);
                Data data = new Data(Integer.parseInt(  dispenserIds.get(i)),customer, dateTimes.get(i));
                if (rsCustomer.getInt(4) == 1) {
                    petrolDataArray.add(data);
                } else if (rsCustomer.getInt(4) == 2) {
                    dieselDataArray.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        OctaneFuelDispenserManager.addDataArray(petrolDataArray);
        DieselFuelDispenserManager.addDataArray(dieselDataArray);
    }

    private static int[] convertToIntArray(String[] vehiclesAllowedString){
        int[] vehiclesAllowed = new int[vehiclesAllowedString.length];
        for (int i=0; i<vehiclesAllowedString.length; i++){
            vehiclesAllowed[i] = Integer.parseInt(vehiclesAllowedString[i]);
        }
        return vehiclesAllowed;
    }
}