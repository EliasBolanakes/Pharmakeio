package medicalStuff;

class Patient {

    private String firstName;
    private String lastName;
    private long Amka;


    Patient(String firstName,String lastName,long Amka){

        this.firstName=firstName;
        this.lastName=lastName;
        this.Amka=Amka;
    }

    protected String getFirstName() {

        return firstName;
    }

    protected String getLastName() {

        return lastName;
    }

    protected   long getAmka() {

        return Amka;
    }
}
