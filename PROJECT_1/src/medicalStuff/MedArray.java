package medicalStuff;

public class MedArray {

    protected int nMeds=0;
    protected Medicine[] medList=new Medicine[50];

    /** This function adds a new medicine */

    protected void addNewMed(Pharmacy ph){
        Input scanner=new Input();

        String medNameBuffer="";
        String medCodeBuffer="";
        double medPriceBuffer=0;


        if(nMeds>=49){
            System.out.println("\nCan't add a new medicine! List is already full!\n");
        }
        else{
            System.out.println("_______________________________________________________");
            System.out.println("NEW MEDICINE ADDITION ");
            System.out.println("_______________________________________________________");

            System.out.print("Enter medicine's name: ");
            medNameBuffer = scanner.readString();

            if (nMeds > 0) {
                for (int i = 0; i < nMeds; i++) {
                    if (medList[i].getMedicineName().equals(medNameBuffer)) {
                        System.out.println("\nError! Medicine name already exists!\n");
                        return;
                    }
                }
            }

            System.out.print("Enter medicine's unique code: ");
            medCodeBuffer= scanner.readString();

            if(nMeds>0){
                for(int i=0;i<nMeds;i++){
                    if(medList[i].getMedicineCode().equals(medCodeBuffer)){
                        System.out.println("\nError! Medicine code already exists!\n");
                        return;
                    }
                }
            }
            do{
            System.out.print("Enter medicine's price: ");
            medPriceBuffer= scanner.readDouble();
            if(medPriceBuffer<=0)
                System.out.print("Enter a positive number please: ");
            }while (medPriceBuffer<=0);
        }

        medList[nMeds]=new Medicine(medNameBuffer,medCodeBuffer,medPriceBuffer,ph);
        nMeds++;
    }

        /** This function prints all the medicines */

    protected void printMedList(){
        if(nMeds==0){
            System.out.println("\nNo medicines entered yet!\n");
        }
        else{
            System.out.println("\nMEDICINE LIST:\n");
            for(int i=0;i<nMeds;i++){
                System.out.println((i+1)+") Medicine name: "+medList[i].getMedicineName()+" ,Code: "+medList[i].getMedicineCode()+" ,Price: "+medList[i].getPrice());
            }
        }
    }

    /** This function initializes the first 5 medicines */

    protected void initialiseMeds(Pharmacy ph){

        medList[0]=new Medicine("med1_nm","med11111",10,1,ph);
        medList[1]=new Medicine("med2_nm","med22222",20,1,ph);
        medList[2]=new Medicine("med3_nm","med33333",30,1,ph);
        medList[3]=new Medicine("med4_nm","med44444",40,1,ph);
        medList[4]=new Medicine("med5_nm","med55555",50,1,ph);
        nMeds=5;
    }
}
