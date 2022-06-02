package medicalStuff;
import java.time.LocalDate;

class Date {

    /** This function checks if a given date is valid */

    protected boolean checkDate(int DD,int MM, int YY){

        if(YY%4==0){
            if(MM>12||MM<1){
                System.out.println("Impossible! Month should be between 1 and 12!");
                return false;
            }
            else {
                if(MM==1||MM==3||MM==5||MM==7||MM==8||MM==10||MM==12){

                    if(DD>31||DD<1){
                        System.out.println("The month the prescription was written has 31 days!");
                        return false;
                    }
                    else
                        return true;
                }
                else if(MM==2){
                    if(DD>29||DD<1){
                        System.out.println("The month you entered has 29 days!");
                        return false;
                    }
                    else
                        return true;
                }
                else{
                    if(DD>30||DD<1){
                        System.out.println("The month you entered has 30 days!");
                        return false;
                    }
                    else
                        return true;
                }
            }
        }
        else{
            if(MM>12||MM<1){
                System.out.println("Impossible! Month should be between 1 and 12!");
                return false;
            }
            else {
                if(MM==1||MM==3||MM==5||MM==7||MM==8||MM==10||MM==12){

                    if(DD>31||DD<1){
                        System.out.println("The month the prescription was written has 31 days!");
                        return false;
                    }
                    else
                        return true;
                }
                else if(MM==2){
                    if(DD>28||DD<1){
                        System.out.println("The month you entered has 28 days!");
                        return false;
                    }
                    else
                        return true;
                }
                else{
                    if(DD>30||DD<1){
                        System.out.println("The month you entered has 30 days!");
                        return false;
                    }
                    else
                        return true;
                }
            }
        }
    }

    /** This function checks if a given time-frame is valid */

    protected boolean checkIfBetween(int startDD,int startMM,int startYY,int endDD,int endMM,int endYY){

        LocalDate startSearch=LocalDate.of(startYY,startMM,startDD);
        LocalDate endSearch=LocalDate.of(endYY,endMM,endDD);

        if(startSearch.isAfter(endSearch)){
            System.out.println("Ending Day can't be before starting date!");
            return false;
        }

        else
            return true;
    }

    /** This function finds all the prescriptions written in a time-frame */

    protected int findInBetween(Prescription pr, int startDD, int startMM, int startYY, int endDD, int endMM, int endYY){

        LocalDate startSearch= LocalDate.of(startYY,startMM,startDD);
        LocalDate endSearch= LocalDate.of(endYY,endMM,endDD);

        if(startSearch.isBefore(pr.getPrDate())&&endSearch.isAfter(pr.getPrDate())){
            System.out.println("\nPrescription Found!");
            System.out.println("Code: "+pr.getPrCode()+" ,prescribed by: "+pr.getPrescribedBy().getAM()+" "+" ,prescribed for: "+pr.getPrescribedFor().getAmka()+" ,Date: "+pr.getPrDate().toString());
            System.out.println("Medicine list: ");
            for(var y:pr.medArray){
                System.out.println("Medicine: "+y.getMedicineName()+" ,quantity: "+y.getQuantity());
            }
            return 1;
        }
        else if (startSearch.isEqual(pr.getPrDate())||endSearch.isEqual(pr.getPrDate())) {

            System.out.println("\nPrescription Found!");
            System.out.println("Code: " + pr.getPrCode() + " ,prescribed by: " + pr.getPrescribedBy().getAM() + " " + " ,prescribed for: " + pr.getPrescribedFor().getAmka() + " ,Date: " + pr.getPrDate().toString());
            System.out.println("Medicine list: ");
            for (var y : pr.medArray) {
                System.out.println("Medicine: " + y.getMedicineName() + " ,quantity: " + y.getQuantity());
            }
            return 1;
        }
        else
            return 0;
    }
}
