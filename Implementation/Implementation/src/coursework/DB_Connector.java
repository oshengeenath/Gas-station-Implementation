package coursework;

import java.sql.*;

public class DB_Connector { Connection con = null;
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cw", "root", "root");
        } catch (Exception e){
            System.out.println(e);
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

                OctaneFuelDispenserManager petrolDispenser = new OctaneFuelDispenserManager(petrolQueue, petrolRepository, rsPetrolDispenser.getFloat(2), "octane92");

                owner.addPetrolDispenser(petrolDispenser);
            }

            ResultSet rsDieselDispenser = st.executeQuery("select  * from diesel_dispenser");
            while (rsDieselDispenser.next()){
                String[] vehiclesAllowed = rsDieselDispenser.getString(4).split(" ");
                Queue_Management_System dieselQueue = new Queue_Management_System(vehiclesAllowed, waitingQueue);

                DieselFuelDispenserManager dieselDispener = new DieselFuelDispenserManager(dieselQueue, dieselRepository, rsDieselDispenser.getFloat(2), "diesel");

                owner.addDieselDispenser(dieselDispener);
            }

        } catch (Exception e){
            System.out.println(e);
        }
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

    private static int[] convertToIntArray(String[] vehiclesAllowedString){
        int[] vehiclesAllowed = new int[vehiclesAllowedString.length];
        for (int i=0; i<vehiclesAllowedString.length; i++){
            vehiclesAllowed[i] = Integer.parseInt(vehiclesAllowedString[i]);
        }
        return vehiclesAllowed;
    }
}