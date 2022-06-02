package medicalStuff;

class PatArray {

    protected static int nPats=0;
    protected static Patient[] patList=new Patient[50];

    /** This function adds a new patient */

    protected static void addNewPat(){

        Input scanner=new Input();
        String patFirstNameBuffer;
        String patLastNameBuffer;
        long patAmkaBuffer;

        if(nPats>=49){
            System.out.println("Can't add a new patient! List is already full!");
        }
        else{
            System.out.println("_______________________________________________________");
            System.out.println("NEW PATIENT ADDITION ");
            System.out.println("_______________________________________________________");

            System.out.print("Enter patient's first name: ");
            patFirstNameBuffer = scanner.readString();

            System.out.print("Enter patient's last name: ");
            patLastNameBuffer= scanner.readString();

            System.out.print("Enter patient's unique Amka: ");
            patAmkaBuffer= scanner.readLong();


                if(nPats>0){
                    for(int i=0;i<nPats;i++){
                        if(patList[i].getAmka()==patAmkaBuffer){
                            System.out.println("\nError! Patient Amka already exists!\n");
                            return;
                        }
                    }
                }

            patList[nPats]=new Patient(patFirstNameBuffer,patLastNameBuffer,patAmkaBuffer);
            nPats++;
        }
    }

    /** This function prints all the patients */

    protected void printPatList(){
        if(nPats==0){
            System.out.println("\nNo patients entered yet!\n");
        }
        else{
            System.out.println("\nPATIENT LIST:\n");
            for(int i=0;i<nPats;i++){
                System.out.println((i+1)+") Patient's first name: "+patList[i].getFirstName()+" ,Last name: "+patList[i].getLastName()+" ,Amka: "+patList[i].getAmka());
            }
        }
    }

    /** this function initializes the first 5 patients */

    protected void initialisePats(){

        patList[0]=new Patient("patient1_nm","patient1_sm",11111111);
        patList[1]=new Patient("patient2_nm","patient2_sm",22222222);
        patList[2]=new Patient("patient3_nm","patient3_sm",33333333);
        patList[3]=new Patient("patient4_nm","patient4_sm",44444444);
        patList[4]=new Patient("patient5_nm","patient5_sm",55555555);
        nPats=5;
    }
}
