package medicalStuff;

public class DocArray {
    protected static int nDocs=0;
    public static Doctor[] docList=new Doctor[50];


    /** This function adds a new doctor */

    protected static void addNewDoc(){
        Input scanner=new Input();

        String docFirstNameBuffer;
        String docLastNameBuffer;
        String docAmBuffer;


        if(nDocs>=49){
            System.out.println("\nCan't add a new doctor! List is already full!\n");
        }
        else{
            System.out.println("_______________________________________________________");
            System.out.println("NEW DOCTOR ADDITION ");
            System.out.println("_______________________________________________________");

            System.out.print("Enter doctor's first name: ");
            docFirstNameBuffer = scanner.readString();
            System.out.print("Enter doctor's last name: ");
            docLastNameBuffer = scanner.readString();
            System.out.print("Enter doctor's unique AM: ");
            docAmBuffer= scanner.readString();


            if(nDocs>0){
                for(int i=0;i<nDocs;i++){
                    if(docList[i].getAM().equals(docAmBuffer)){
                        System.out.println("\nError! Doctor AM already exists!\n");
                        return;
                    }
                }
            }

            docList[nDocs]=new Doctor(docFirstNameBuffer,docLastNameBuffer,docAmBuffer);
            nDocs++;
        }
    }

    /** This function prints all the doctors */

    protected void printDocList(){
        if(nDocs==0){
            System.out.println("\nNo doctors entered yet!\n");
        }
        else{
            System.out.println("\nDOCTOR LIST:\n");
            for(int i=0;i<nDocs;i++){
                System.out.println((i+1)+") Doctor's first name: "+docList[i].getFirstName()+" ,Last name: "+docList[i].getLastName()+" ,AM: "+docList[i].getAM());
            }
        }
    }

    /** This function initializes the first 5 doctors */

    protected void initialiseDocs(){
        docList[0]=new Doctor("doctor1_nm","doctor1_sm","doc11111");
        docList[1]=new Doctor("doctor2_nm","doctor2_sm","doc22222");
        docList[2]=new Doctor("doctor3_nm","doctor3_sm","doc33333");
        docList[3]=new Doctor("doctor4_nm","doctor4_sm","doc44444");
        docList[4]=new Doctor("doctor5_nm","doctor5_sm","doc55555");
        nDocs=5;
    }
}

