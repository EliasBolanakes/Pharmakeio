package medicalStuff;

class Doctor {

    private String firstName;
    private String lastName;
    private String AM;

    Doctor(String firstName,String lastName,String AM){

        this.firstName=firstName;
        this.lastName=lastName;
        this.AM=AM;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected String getAM() {
        return AM;
    }
}
