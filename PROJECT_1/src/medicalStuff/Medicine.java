package medicalStuff;

class Medicine {

    private String medicineName;
    private String medicineCode;
    private double price;
    private double quantity;
    private Pharmacy whichPharmacy;

    Medicine(String medicineName,String medicineCode,double price,double quantity,Pharmacy whichPharmacy){
        this.medicineName=medicineName;
        this.medicineCode=medicineCode;
        this.quantity=quantity;
        this.price=price;
        this.whichPharmacy=whichPharmacy;
    }

    Medicine(String medicineName,String medicineCode,double price,Pharmacy whichPharmacy){
        this.medicineName=medicineName;
        this.medicineCode=medicineCode;
        quantity=1;
        this.price=price;
        this.whichPharmacy=whichPharmacy;
    }

    public String getMedicineName() {

        return medicineName;
    }

    public String getMedicineCode() {

        return medicineCode;
    }

    public double getPrice() {

        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}
