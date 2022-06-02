package medicalStuff;

public class Menu {

    String[] menuArray = new String[9];
    MedArray newMedArray = new MedArray();
    DocArray newDocArray = new DocArray();
    PatArray newPatArray = new PatArray();
    PrArray newPrArray = new PrArray();

    /**
     * This function projects the menu, gets the user's choice and performs all the functions
     **/

    public void showMenu(Pharmacy newPharmacy) throws InterruptedException {

        Input scanner=new Input();
        System.out.println("\nWELCOME TO: " + newPharmacy.getPharmacyName());

        int choice;
        boolean choiceConfirmed;
        do {
            menuArray[0] = "1 'Enter new medicine'";
            menuArray[1] = "2 'Enter new doctor'";
            menuArray[2] = "3 'Enter new patient'";
            menuArray[3] = "4 'Enter new prescription'";
            menuArray[4] = "5 'Delete existing prescription'";
            menuArray[5] = "6 'Find existing prescription by: code/date/Amka/doctor'";
            menuArray[6] = "7 'Calculate prescription cost'";
            menuArray[7] = "8 'Show information for doctor/medicine/prescription'";
            menuArray[8] = "9 'Return to main menu'";

            System.out.println("_______________________________________________________");
            System.out.println("SELECT ONE OF THE FOLLOWING ACTIONS (WITH No): \n ");

            for (var x : menuArray) {
                System.out.println(x);
            }

            System.out.print("ENTER CHOICE: ");
            do {
                choice = scanner.readInt();
                if (choice>9||choice<1)
                    System.out.print("Wrong input! Please enter an integer number between 1 and 9: ");
            } while (choice>9||choice<1);
            System.out.println("You chose option No" + menuArray[choice - 1]);
            System.out.print("Enter 'yes' to confirm and precede or anything else to reselect: ");
            choiceConfirmed = choiceConfirmation();
        } while (!choiceConfirmed);

        switch (choice) {
            case 1 -> newMedArray.addNewMed(newPharmacy);
            case 2 -> DocArray.addNewDoc();
            case 3 -> PatArray.addNewPat();
            case 4 -> newPrArray.addNewPr(newDocArray, newPatArray, newMedArray, newPharmacy);
            case 5 -> newPrArray.deletePrescription();
            case 6 -> newPrArray.findBy();
            case 7 -> newPrArray.selectCalculation();
            case 8 -> printInfo();
            case 9 -> {
                System.out.println("TERMINATING PROGRAM...");
                return;
            }
        }
            Thread.sleep(1000);
            showMenu(newPharmacy);
    }

    /**
     * This function confirms if the user enter the option he wanted
     **/

    private boolean choiceConfirmation() {
        Input scanner=new Input();

        String confirmation ;
        confirmation = scanner.readString();

        if (confirmation.equals("yes"))
            return true;
        else {
            System.out.println("Choose again!");
            return false;
        }
    }

    /**
     * This function prints information about medicines/doctors/patients/prescriptions
     **/

    private void printInfo(){

        Input scanner=new Input();
        int choice;
        do {
            System.out.println("\nType: \n1) for medicine info \n2) for doctor info \n3) for patient info \n4) for prescription info");
            System.out.print("Enter choice: ");
            choice=scanner.readInt();
            if(choice>4||choice<1){
                System.out.println("\nTry again and enter a number between 1 and 4\n");
            }
        }while (choice>4||choice<1);
        switch (choice) {
            case 1 -> newMedArray.printMedList();
            case 2 -> newDocArray.printDocList();
            case 3 -> newPatArray.printPatList();
            case 4 -> newPrArray.printPrs();
        }
    }

    public void initialise(Pharmacy newPharmacy){
        newDocArray.initialiseDocs();
        newMedArray.initialiseMeds(newPharmacy);
        newPatArray.initialisePats();
        newPrArray.initialisePrs(newMedArray,newPharmacy);
    }
}
