package medicalStuff;

import java.time.LocalDate;

class PrArray implements ArrayMethods {

    private int nPrs=0;
    private final Prescription[] prList=new Prescription[50];

    /** This function adds a new prescription */

    protected void addNewPr(DocArray doc ,PatArray pat,MedArray med,Pharmacy ph) {

        String prCodeBuffer;
        Input scanner=new Input();

        if (nPrs >= 49) {
            System.out.println("Can't add a new prescription!List is already full!");
        } else if (DocArray.nDocs == 0 || PatArray.nPats == 0 || med.nMeds == 0) {
            System.out.println("\nUnable to create prescription since one of the lists is empty(medicine/doctor/patient)!\n");
        }
        else{
            System.out.println("_______________________________________________________");
            System.out.println("NEW PRESCRIPTION ADDITION");
            System.out.println("_______________________________________________________");

            System.out.print("Enter prescription's unique code: ");
            prCodeBuffer = scanner.readString();

            if (nPrs > 0) {
                for (int i = 0; i < nPrs; i++) {
                    if (prList[i].getPrCode().equals(prCodeBuffer)) {
                        System.out.println("Error! Prescription code already exists! ");
                        return;
                    }
                }
            }
            int docChoice;
            int patChoice;

            Doctor docBuffer;
            Patient patBuffer;
            System.out.println("Select the doctor who wrote the prescription(with number)\n");
            doc.printDocList();
            System.out.print("Enter choice: ");
            do {

                docChoice = scanner.readInt();
                if (docChoice > DocArray.nDocs || docChoice < 1)
                    System.out.print("The number you entered doesn't correspond to a doctor!Try again: ");
            } while (docChoice > DocArray.nDocs ||docChoice<1);

            docBuffer = DocArray.docList[docChoice - 1];
            System.out.println("Chosen Doctor: " + docBuffer.getFirstName() + " " + docBuffer.getLastName());

            System.out.println("Select the patient for whom the prescription is for(with number)\n ");
            pat.printPatList();
            System.out.print("Enter choice: ");
            do {

                patChoice = scanner.readInt();
                if (patChoice > PatArray.nPats || patChoice < 1) {
                    System.out.print("The number you entered doesn't correspond to a patient!Try again: ");
                }
            } while (patChoice > PatArray.nPats ||patChoice<1);

            patBuffer = PatArray.patList[patChoice - 1];
            System.out.println("Chosen Patient: " + patBuffer.getFirstName() + " " + patBuffer.getLastName());

            System.out.println("\nEnter the date that the prescription was written:\n");
            int prDD;
            int prMM;
            int prYY;
            Date newDate = new Date();

            do {
                System.out.print("Enter the day(number): ");
                prDD = scanner.readInt();

                System.out.print("Enter the month(number):");
                prMM = scanner.readInt();

                System.out.print("Enter the year: ");
                prYY = scanner.readInt();

                if (!newDate.checkDate(prDD, prMM, prYY)) {
                    System.out.println("Impossible date!Try again!");
                }
            } while (!newDate.checkDate(prDD, prMM, prYY));
            System.out.println("Date: " + prDD + "/" + prMM + "/" + prYY);

            System.out.print("\nHow many medicines do you want the prescription to consist of(up to 6): ");
            int howManyMeds;

            do {
                howManyMeds = scanner.readInt();
                if (howManyMeds > 6 || howManyMeds < 1) {
                    System.out.print("Error!Please enter a number between 1 and 6: ");
                }
            } while (howManyMeds > 6 || howManyMeds < 1);

            Medicine[] medListBuffer = new Medicine[howManyMeds];
            int medSelection;
            double quantityBuffer;
            med.printMedList();
            for (int i = 0; i < howManyMeds; i++) {
                System.out.print("\nSelect one of the medicines from the list(with number): ");
                do {
                    medSelection = scanner.readInt();
                    if (medSelection > med.nMeds || medSelection < 1) {
                        System.out.print("Please select one of the medicines from the list: ");
                    }
                } while (medSelection > med.nMeds || medSelection < 1);

                System.out.print("Select the dose of the medicine: ");
                do {
                    quantityBuffer = scanner.readDouble();
                    if(quantityBuffer<=0)
                        System.out.println("\nPlease select a valid quantity: ");
                }while (quantityBuffer<=0);

                medListBuffer[i] = new Medicine(med.medList[medSelection - 1].getMedicineName(), med.medList[medSelection - 1].getMedicineCode(), med.medList[medSelection - 1].getPrice(), quantityBuffer, ph);
                System.out.println("Selected Medicine: " + medListBuffer[i].getMedicineName() + " ,quantity: " + medListBuffer[i].getQuantity());
            }
            prList[nPrs] = new Prescription(prCodeBuffer, docBuffer, patBuffer, LocalDate.of(prYY,prMM,prDD), medListBuffer);
            nPrs++;
        }
    }

        /** This function prints all the prescriptions */

        protected void printPrs(){
            System.out.print("\n");
            System.out.println("PRESCRIPTION LIST:\n");
        if(nPrs==0){
            System.out.println("No prescriptions entered yet!");
        }
        else{
            for(int i=0;i<nPrs;i++){

                    System.out.println("Prescription No"+(i+1)+" Code: "+prList[i].getPrCode());
                    System.out.println("Prescribed by: "+prList[i].getPrescribedBy().getAM()+" ,for: "+prList[i].getPrescribedFor().getAmka());
                    System.out.println("Prescription's Medicines: ");
                    for(var x:prList[i].medArray){
                        System.out.println(x.getMedicineName()+" ,quantity: "+x.getQuantity());
                    }
                    System.out.print("\n");
            }
        }
    }

    /** This function deletes a prescription */

        protected void deletePrescription(){
            Input scanner=new Input();
            String deleteCodeBuffer;
            printPrs();
            boolean prDeleted=false;
            String tryAgain;
           do {
               System.out.print("Which of the prescriptions above do you want to delete(Enter unique prescription code): ");
               deleteCodeBuffer = scanner.readString();

               for (int i = 0; i < nPrs; i++) {
                   if (deleteCodeBuffer.equals(prList[i].getPrCode())) {
                       prList[i] = null;
                       prDeleted = true;
                       break;
                   }
               }
               if (prDeleted) {
                   System.out.println("Prescription with code: " + deleteCodeBuffer + " successfully deleted!");
                   tryAgain = "no";
               } else {
                   System.out.println("The code you typed doesn't match with any of the existing prescriptions!");
                   System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");

                   tryAgain = scanner.readString();
               }
           }while (tryAgain.equals("yes")) ;
           arrangeArray(prList);
        }

        /** This function combines the 4 functions with different search criteria */

        protected void findBy(){
            Input scanner=new Input();
            int option;
            System.out.println("\nWhich of the following criteria should determine your search: ");
            System.out.println("1) Prescription Code\n2) Doctor Am\n3) Patient Amka\n4) Time frame\n(Enter a number between 1 and 4): ");
            System.out.print("Enter choice: ");
            do{
                option=scanner.readInt();

                if(option<1||option>4){
                    System.out.print("Please try again and enter a number between 1 and 4: ");
                }
            }while (option<1||option>4);
            if(option==1){
                findByPrCode();
            }
            else if(option==2){
                findByDocAM();
            }
            else if(option==3){
                findByPatAmka();
            }
            else
                findByDate();
        }

        /** This function finds a function by its unique code */

        private void findByPrCode() {
            Input scanner=new Input();
            String prCodeBuffer ;
            boolean prFound = false;
            String tryAgain = "no";
            do {
                System.out.print("Enter the unique code of the prescription you are searching for: ");
                prCodeBuffer = scanner.readString();

                for (int i = 0; i < nPrs; i++) {
                    if (prCodeBuffer.equals(prList[i].getPrCode())) {
                        System.out.println("Prescription found!");
                        System.out.println("Code: " + prList[i].getPrCode() + " ,prescribed by: " + prList[i].getPrescribedBy().getAM() + " " + " ,prescribed for: " + prList[i].getPrescribedFor().getAmka() + " ,Date: " + prList[i].getPrDate().toString());
                        System.out.println("Medicine list: ");
                        for (var x : prList[i].medArray) {
                            System.out.println("Medicine: " + x.getMedicineName() + " ,quantity: " + x.getQuantity());
                        }
                        prFound = true;
                        tryAgain = "no";
                        break;
                    }
                }
                if(!prFound){
                    System.out.println("No prescription found with the code you entered!");
                    System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                    tryAgain = scanner.readString();
                }
            } while (tryAgain.equals("yes"));
        }

        /** This function finds the prescriptions written by a doctor */

        private void findByDocAM(){
            Input scanner=new Input();
            String docAMBuffer;
            boolean prFound=false;
            String tryAgain="no";
            do{
                System.out.print("Enter the doctor's unique AM: ");
                docAMBuffer= scanner.readString();

                for(int i=0;i<nPrs;i++) {

                    if (docAMBuffer.equals(prList[i].getPrescribedBy().getAM())) {
                        System.out.println("Prescription found!");
                        System.out.println("Code: " + prList[i].getPrCode() + " ,prescribed by: " + prList[i].getPrescribedBy().getAM() + " " + " ,prescribed for: " + prList[i].getPrescribedFor().getAmka() + "Date: " + prList[i].getPrDate().toString());
                        System.out.println("Medicine list: ");
                        for (var x : prList[i].medArray) {
                            System.out.println("Medicine: " + x.getMedicineName() + " ,quantity: " + x.getQuantity());
                        }
                        prFound = true;
                        tryAgain = "no";
                    }
                }
                    if (!prFound){
                        System.out.println("No prescription found with the doctor AM you entered!");
                        System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                        tryAgain= scanner.readString();
                    }
            }while (tryAgain.equals("yes"));
        }

    /** This function finds all the prescription written for a patient  */

    private void findByPatAmka() {
        Input scanner = new Input();
        long patAmkaBuffer;
        boolean prFound = false;
        String tryAgain = "no";
        do {
            System.out.print("Enter the patient's unique Amka: ");
            patAmkaBuffer = scanner.readLong();

            for (int i = 0; i < nPrs; i++) {
                if (prList[i].getPrescribedFor().getAmka() == patAmkaBuffer) {
                    System.out.println("Prescription found!");
                    System.out.println("Code: " + prList[i].getPrCode() + " ,prescribed by: " + prList[i].getPrescribedBy().getAM() + " " + " ,prescribed for: " + prList[i].getPrescribedFor().getAmka() + "Date: " + prList[i].getPrDate().toString());
                    System.out.println("Medicine list: ");
                    for (var x : prList[i].medArray) {
                        System.out.println("Medicine: " + x.getMedicineName() + " ,quantity: " + x.getQuantity());
                    }
                    prFound = true;
                    tryAgain = "no";
                }
            }
            if (!prFound) {
                System.out.println("No prescription found with the patient Amka you entered!");
                System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                tryAgain = scanner.readString();
            }
        }while (tryAgain.equals("yes")) ;

    }

    /** This function finds all the prescriptions written in a time-frame */

    private void findByDate() {
        Input scanner = new Input();
        int startDD ;
        int startMM ;
        int startYY ;
        int endDD ;
        int endMM ;
        int endYY ;
        Date newDate = new Date();
        String tryAgain="no";
        do {
            do {
                do {
                    System.out.println("Enter details for the start of the search: \n");
                    System.out.print("Enter the starting day of the search(number): ");
                    startDD = scanner.readInt();

                    System.out.print("Enter the starting month of the search(number): ");
                    startMM = scanner.readInt();


                    System.out.print("Enter the starting year of the search(number): ");
                    startYY = scanner.readInt();

                    if (!newDate.checkDate(startDD, startMM, startYY)) {
                        System.out.println("Try again!");
                    }

                }while (!newDate.checkDate(startDD, startMM, startYY)) ;
                do {
                    System.out.println("Enter details for the end of the search: \n");

                    System.out.print("Enter the ending day of the search(number): ");
                    endDD = scanner.readInt();

                    System.out.print("Enter the ending month of the search(number): ");
                    endMM = scanner.readInt();

                    System.out.print("Enter the ending year of the search(number): ");
                    endYY = scanner.readInt();

                    if (!newDate.checkDate(endDD, endMM, endYY)) {
                        System.out.println("Try again!");
                    }
                } while (!newDate.checkDate(endDD, endMM, endYY));

                if (!newDate.checkIfBetween(startDD, startMM, startYY, endDD, endMM, endYY)) {
                    System.out.println("Try again!");
                } else {
                    System.out.println("Search frame: " + startDD + "/" + startMM + "/" + startYY + " - " + endDD + "/" + endMM + "/" + endYY);
                }
            } while (!newDate.checkIfBetween(startDD, startMM, startYY, endDD, endMM, endYY));

            int prsFound=0;
            for(int i=0;i<nPrs;i++){
                    prsFound+=newDate.findInBetween(prList[i],startDD,startMM,startYY,endDD,endMM,endYY);
            }
            if(prsFound==0){
                System.out.println("No prescriptions found in that given time frame!");
                System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                tryAgain= scanner.readString();
            }
        }while (tryAgain.equals("yes"));
    }

    /** this function calculates the total cost of all the prescriptions written by a doctor */

    private void calculateDoctorCost(){
        Input scanner=new Input();
        String docAMBuffer ;

        double docSum=0;
        boolean docFound=false;
        String tryAgain;
        do {
            tryAgain="no";
            System.out.print("Enter the doctor's AM to find the total cost of the prescriptions he has written: ");
            docAMBuffer= scanner.readString();

            for(int i=0;i<nPrs;i++){
                if(docAMBuffer.equals(prList[i].getPrescribedBy().getAM())){
                    docSum+=calculatePrCost(i);
                    docFound =true;
                }
            }
            if (docFound){
                System.out.println("Doctor's total: "+docSum+"$");
            }
            else {
                System.out.println("No doctor found with given AM!");
                System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                tryAgain = scanner.readString();
            }
        }while (tryAgain.equals("yes"));
    }

    /** this function calculates the total cost of all the prescriptions written for a patient */

    private void calculatePatientCost(){
        Input scanner=new Input();
        long patAmkBuffer ;

        double patSum=0;
        boolean patFound=false;
        String tryAgain;
        do {
            tryAgain="no";
            System.out.print("Enter the patient's Amka to find the total cost of the prescriptions he has been prescribed: ");
            patAmkBuffer= scanner.readLong();

            for(int i=0;i<nPrs;i++){
                if(patAmkBuffer==prList[i].getPrescribedFor().getAmka()){
                    patSum+=calculatePrCost(i);
                    patFound =true;
                }
            }
            if (patFound){
                System.out.println("Patient's total: "+patSum+"$");
                return;
            }
            else {
                System.out.println("No patient found with given Amka!");
                System.out.print("Would you like to try again?(Type 'yes' to retry or anything else to exit search): ");
                tryAgain = scanner.readString();
            }
        }while (tryAgain.equals("yes"));
    }

    /** This function combines the 2 functions above */

    protected void selectCalculation() {
        Input scanner=new Input();
        int choice ;
        System.out.println("\nFind $ total based on: \n1)Patient's Amka \n2)Doctor's AM");
        System.out.print("Enter choice: ");
        do {
            choice=scanner.readInt();
            switch (choice) {
                case 1 -> calculatePatientCost();
                case 2 -> calculateDoctorCost();
                default -> System.out.print("Please Type 1 or 2: ");
            }
        }while (choice != 1 && choice != 2) ;
    }

    private double calculatePrCost(int i){
        double sum=0;
        for(var x: prList[i].medArray){
            sum+=x.getPrice()*x.getQuantity();
        }
        return sum;
    }

    /** This function initializes the first 10 prescriptions */

    protected void initialisePrs(MedArray meds,Pharmacy ph){

        Medicine[] prList0Buffer=new Medicine[3];
        prList0Buffer[0]=new Medicine(meds.medList[0].getMedicineName(), meds.medList[0].getMedicineCode(),meds.medList[0].getPrice(),2,ph);
        prList0Buffer[1]=new Medicine(meds.medList[1].getMedicineName(),meds.medList[1].getMedicineCode(),meds.medList[1].getPrice(),4,ph);
        prList0Buffer[2]=new Medicine(meds.medList[2].getMedicineName(), meds.medList[2].getMedicineCode(),meds.medList[2].getPrice(),1,ph);
        prList[0]=new Prescription("prescr11111", DocArray.docList[0], PatArray.patList[0],LocalDate.of(2022,3,23),prList0Buffer);

        Medicine[] prList1Buffer=new Medicine[1];
        prList1Buffer[0]=new Medicine(meds.medList[0].getMedicineName(), meds.medList[0].getMedicineCode(),meds.medList[0].getPrice(),1,ph);
        prList[1]=new Prescription("prescr22222", DocArray.docList[0], PatArray.patList[1],LocalDate.of(2022,3,24),prList1Buffer);

        Medicine[] prList2Buffer=new Medicine[2];
        prList2Buffer[0]=new Medicine(meds.medList[0].getMedicineName(), meds.medList[0].getMedicineCode(),meds.medList[0].getPrice(),3,ph);
        prList2Buffer[1]=new Medicine(meds.medList[2].getMedicineName(),meds.medList[2].getMedicineCode(),meds.medList[2].getPrice(),2,ph);
        prList[2]=new Prescription("prescr33333", DocArray.docList[1], PatArray.patList[1],LocalDate.of(2022,3,25),prList2Buffer);

        Medicine[] prList3Buffer=new Medicine[3];
        prList3Buffer[0]=new Medicine(meds.medList[3].getMedicineName(), meds.medList[3].getMedicineCode(),meds.medList[3].getPrice(),1,ph);
        prList3Buffer[1]=new Medicine(meds.medList[2].getMedicineName(),meds.medList[2].getMedicineCode(),meds.medList[2].getPrice(),2,ph);
        prList3Buffer[2]=new Medicine(meds.medList[1].getMedicineName(), meds.medList[1].getMedicineCode(),meds.medList[1].getPrice(),1,ph);
        prList[3]=new Prescription("prescr44444", DocArray.docList[1], PatArray.patList[2],LocalDate.of(2022,3,26),prList3Buffer);

        Medicine[] prList4Buffer=new Medicine[3];
        prList4Buffer[0]=new Medicine(meds.medList[0].getMedicineName(), meds.medList[0].getMedicineCode(),meds.medList[0].getPrice(),1,ph);
        prList4Buffer[1]=new Medicine(meds.medList[4].getMedicineName(),meds.medList[4].getMedicineCode(),meds.medList[4].getPrice(),1,ph);
        prList4Buffer[2]=new Medicine(meds.medList[1].getMedicineName(), meds.medList[1].getMedicineCode(),meds.medList[1].getPrice(),1,ph);
        prList[4]=new Prescription("prescr55555", DocArray.docList[2], PatArray.patList[2],LocalDate.of(2022,4,1),prList4Buffer);

        Medicine[] prList5Buffer=new Medicine[2];
        prList5Buffer[0]=new Medicine(meds.medList[3].getMedicineName(), meds.medList[3].getMedicineCode(),meds.medList[3].getPrice(),2,ph);
        prList5Buffer[1]=new Medicine(meds.medList[4].getMedicineName(),meds.medList[4].getMedicineCode(),meds.medList[4].getPrice(),2,ph);
        prList[5]=new Prescription("prescr66666", DocArray.docList[2], PatArray.patList[0],LocalDate.of(2022,4,4),prList5Buffer);

        Medicine[] prList6Buffer=new Medicine[2];
        prList6Buffer[0]=new Medicine(meds.medList[3].getMedicineName(), meds.medList[3].getMedicineCode(),meds.medList[3].getPrice(),1,ph);
        prList6Buffer[1]=new Medicine(meds.medList[0].getMedicineName(),meds.medList[0].getMedicineCode(),meds.medList[0].getPrice(),2,ph);
        prList[6]=new Prescription("prescr77777", DocArray.docList[3], PatArray.patList[3],LocalDate.of(2022,4,6),prList6Buffer);

        Medicine[] prList7Buffer=new Medicine[1];
        prList7Buffer[0]=new Medicine(meds.medList[1].getMedicineName(), meds.medList[1].getMedicineCode(),meds.medList[1].getPrice(),5,ph);
        prList[7]=new Prescription("prescr88888", DocArray.docList[3], PatArray.patList[4],LocalDate.of(2022,4,16),prList7Buffer);

        Medicine[] prList8Buffer=new Medicine[1];
        prList8Buffer[0]=new Medicine(meds.medList[4].getMedicineName(), meds.medList[4].getMedicineCode(),meds.medList[4].getPrice(),5,ph);
        prList[8]=new Prescription("prescr99999", DocArray.docList[4], PatArray.patList[4],LocalDate.of(2022,4,20),prList8Buffer);
        nPrs=9;
    }

    /** This function locates the null elements of an array in the end*/

    @Override
    public void arrangeArray(Prescription[] prList) {
        int num=0;
        Prescription[] tempArray=new Prescription[prList.length];
        for (Prescription prescription : prList) {
            if (prescription != null) {
                tempArray[num] = prescription;
                num++;
            }
        }
        System.arraycopy(tempArray, 0, prList, 0, prList.length);
        nPrs--;
    }
}









