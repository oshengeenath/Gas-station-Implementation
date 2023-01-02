package coursework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {DB_Connector db = new DB_Connector();
        while (true) {
            db.connect();
            Owner owner = db.getOwner();
            //-----------------------------Getting user inputs------------------------------------------------------------
            Scanner Input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter 1 to add customer information" + '\n' +
                    "Enter 2 to serve a vehicle" + '\n' +
                    "Enter 3 to check stats" + '\n' +
                    "Enter 4 to add a new dispenser");
            int user_input = Input.nextInt();  // Read user input

            //-----------------------------------According to the user inputs running the program-------------------------
            if (user_input == 1) {
                db.add_Customer();
            } else if (user_input == 2) {
                db.served_info();
            } else if (user_input == 3) {

                Scanner scannerCase3 = new Scanner(System.in);
                System.out.println("""
                        1. Total fuel dispensed per vehicle type per fuel type
                        2. The vehicle that received the largest amount of fuel for the day and the type of fuel received
                        3. Total number of vehicles served by each dispenser along with the amount of fuel and the total income per dispenser
                        4. Total income of the gas station  per day per fuel type
                        5. Remaining stock""");
                String selectedStats = scannerCase3.next();
                switch (selectedStats) {
                    case "1" -> {
                        ArrayList<Data> petrolDataArray = OctaneFuelDispenserManager.getDataArray();
                        ArrayList<Data> dieselDataArray = DieselFuelDispenserManager.getDataArray();
                        float[] totalFuelDispensed = new float[12];
                        for (int i = 0; i < 12; i++) {
                            totalFuelDispensed[i] = 0;
                        }
                        for (Data data : petrolDataArray) {
                            if (data.getCustomer().getVehicle_type() == 1) {
                                totalFuelDispensed[0] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 2) {
                                totalFuelDispensed[1] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 3) {
                                totalFuelDispensed[2] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 4) {
                                totalFuelDispensed[3] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 5) {
                                totalFuelDispensed[4] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 6) {
                                totalFuelDispensed[5] += data.getCustomer().getFuel_Needed();
                            }
                        }
                        for (Data data : dieselDataArray) {
                            if (data.getCustomer().getVehicle_type() == 1) {
                                totalFuelDispensed[6] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 2) {
                                totalFuelDispensed[7] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 3) {
                                totalFuelDispensed[8] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 4) {
                                totalFuelDispensed[9] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 5) {
                                totalFuelDispensed[10] += data.getCustomer().getFuel_Needed();
                            } else if (data.getCustomer().getVehicle_type() == 6) {
                                totalFuelDispensed[11] += data.getCustomer().getFuel_Needed();
                            }
                        }
                        System.out.println("Petrol\n" +
                                "Car              : " + totalFuelDispensed[0] + "l\n" +
                                "Van              : " + totalFuelDispensed[1] + "l\n" +
                                "Three Wheel      : " + totalFuelDispensed[2] + "l\n" +
                                "Motor Bike       : " + totalFuelDispensed[3] + "l\n" +
                                "Public Transport : " + totalFuelDispensed[4] + "l\n" +
                                "Other            : " + totalFuelDispensed[5] + "l\n\n" +
                                "Diesel\n" +
                                "Car              : " + totalFuelDispensed[6] + "l\n" +
                                "Van              : " + totalFuelDispensed[7] + "l\n" +
                                "Three Wheel      : " + totalFuelDispensed[8] + "l\n" +
                                "Motor Bike       : " + totalFuelDispensed[9] + "l\n" +
                                "Public Transport : " + totalFuelDispensed[10] + "l\n" +
                                "Other            : " + totalFuelDispensed[11] + "l");
                    }
                    case "2" -> {
                        Date date = new Date();
                        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        ArrayList<Data> petrolDataArray = OctaneFuelDispenserManager.getDataArray();
                        ArrayList<Data> dieselDataArray = DieselFuelDispenserManager.getDataArray();
                        String highestServedFuel = "Petrol";
                        float highestServed = 0;
                        for (Data data : petrolDataArray) {
                            DateTime customerDate = data.getDateTime();
                            if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                                if (data.getCustomer().getFuel_Needed() > highestServed) {
                                    highestServed = data.getCustomer().getFuel_Needed();
                                }
                            }
                        }
                        for (Data data : dieselDataArray) {
                            DateTime customerDate = data.getDateTime();
                            if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                                if (data.getCustomer().getFuel_Needed() > highestServed) {
                                    highestServed = data.getCustomer().getFuel_Needed();
                                    highestServedFuel = "Diesel";
                                }
                            }
                        }
                        if (highestServed > 0) {
                            System.out.println(highestServed + " litres of " + highestServedFuel);
                        } else {
                            System.out.println("No customers were served today");
                        }
                    }
                    case "3" -> {
                        ArrayList<Data> petrolDataArray = OctaneFuelDispenserManager.getDataArray();
                        ArrayList<Data> dieselDataArray = DieselFuelDispenserManager.getDataArray();
                        ArrayList<float[]> petrolDispenserIncome = new ArrayList<>();
                        ArrayList<float[]> dieselDispenserIncome = new ArrayList<>();
                        for (int i = 0; i < owner.getPetrolDispensers().size(); i++) {
                            petrolDispenserIncome.add(new float[]{0, 0});
                        }
                        for (int i = 0; i < owner.getDieselDispenser().size(); i++) {
                            dieselDispenserIncome.add(new float[]{0, 0});
                        }
                        for (Data data : petrolDataArray) {
                            int dispenserID = (data.getDispense_ID());
                            petrolDispenserIncome.get(dispenserID)[1]++;
                            petrolDispenserIncome.get(dispenserID)[0] += data.getCustomer().getFuel_Needed();
                        }
                        for (Data data : dieselDataArray) {
                            int dispenserID = (data.getDispense_ID());
                            dieselDispenserIncome.get(dispenserID)[1]++;
                            dieselDispenserIncome.get(dispenserID)[0] += data.getCustomer().getFuel_Needed();
                        }
                        for (int i = 0; i < owner.getPetrolDispensers().size(); i++) {
                            int number = i + 1;
                            System.out.println("Petrol Dispenser " + number);
                            System.out.println("Total Number of Vehicles Served : " + petrolDispenserIncome.get(i)[1]);
                            System.out.println("Total Fuel Served : " + petrolDispenserIncome.get(i)[0]);
                            System.out.println("Total Profit : " + petrolDispenserIncome.get(i)[0] * OctaneFuelDispenserManager.getPricePerLiter() + "\n");
                        }
                        for (int i = 0; i < owner.getDieselDispenser().size(); i++) {
                            int number = i + 1;
                            System.out.println("Diesel Dispenser " + number);
                            System.out.println("Total Number of Vehicles Served : " + dieselDispenserIncome.get(i)[1]);
                            System.out.println("Total Fuel Served : " + dieselDispenserIncome.get(i)[0]);
                            System.out.println("Total Profit : " + dieselDispenserIncome.get(i)[0] * DieselFuelDispenserManager.getPricePerLiter() + "\n");
                        }

                    }
                    case "4" -> {
                        Date date = new Date();
                        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        float petrolIncome = 0;
                        float dieselIncome = 0;
                        ArrayList<Data> petrolDataArray = OctaneFuelDispenserManager.getDataArray();
                        ArrayList<Data> dieselDataArray = DieselFuelDispenserManager.getDataArray();
                        for (Data data : petrolDataArray) {
                            DateTime customerDate = data.getDateTime();
                            if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                                petrolIncome += data.getCustomer().getFuel_Needed() * OctaneFuelDispenserManager.getPricePerLiter();
                            }
                        }
                        for (Data data : dieselDataArray) {
                            DateTime customerDate = data.getDateTime();
                            if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                                petrolIncome += data.getCustomer().getFuel_Needed() * DieselFuelDispenserManager.getPricePerLiter();
                            }
                        }
                        System.out.println("Total Income from Petrol Dispensers : " + petrolIncome);
                        System.out.println("Total Income from Diesel Dispensers : " + dieselIncome);
                    }
                    case "5" -> {
                        float petrolStock = owner.getPetrolRepository().checkFuelLeft();
                        float dieselStock = owner.getDieselRepository().checkFuelLeft();
                        System.out.println("Petrol Stock Left : " + petrolStock);
                        System.out.println("Diesel Stock Left : " + dieselStock);
                    }
                }
            } else if (user_input == 4) {
                Scanner scannerCase4 = new Scanner(System.in);
                System.out.println("""
                        press 1 to add 92 Octane fuel Dispenser
                        press 2 to add Diesel fuel Dispenser""");
                String selectedStats2 = scannerCase4.next();
                switch (selectedStats2) {
                    case "1" -> {
                        db.add_92Octane_dispenser();
                    }
                    case "2" -> {
                        db.add_diesel_dispenser();
                    }
                }
            }


        }     //--------------------------------------------------------------------

        //-----------------------------------------------------------------
        //initialize();
    }






}



