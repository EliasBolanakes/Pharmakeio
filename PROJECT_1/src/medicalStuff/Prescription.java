package medicalStuff;
import java.time.LocalDate;

class Prescription {

    private String prCode;
    private Doctor prescribedBy;
    private Patient prescribedFor;
    private LocalDate prDate;
    protected Medicine[] medArray;

    Prescription(String prCode, Doctor prescribedBy, Patient prescribedFor,LocalDate prDate, Medicine[] medArray){

        this.prCode=prCode;
        this.prescribedBy=prescribedBy;
        this.prescribedFor=prescribedFor;
        this.prDate=prDate;
        this.medArray=medArray;
    }

    protected String getPrCode() {

        return prCode;
    }

    protected Doctor getPrescribedBy() {

        return prescribedBy;
    }

    protected Patient getPrescribedFor() {

        return prescribedFor;
    }

    public LocalDate getPrDate() {
        return prDate;
    }

    protected Medicine[] getMedArray() {

        return medArray;
    }
}
