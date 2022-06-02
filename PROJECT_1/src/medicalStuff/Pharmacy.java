package medicalStuff;

public class Pharmacy {

    public String pharmacyName;
    public String addressCity;
    public String addressRoad;
    public int addressNo;
    public long pharmacyPhone;

    public Pharmacy(String pharmacyName, String addressCity, String addressRoad, int addressNo, long pharmacyPhone){

        this.pharmacyName=pharmacyName;
        this.addressCity=addressCity;
        this.addressRoad=addressRoad;
        this.addressNo=addressNo;
        this.pharmacyPhone=pharmacyPhone;
    }

    public String getPharmacyName() {

        return pharmacyName;
    }

    public String getAddressCity() {

        return addressCity;
    }

    public String getAddressRoad() {

        return addressRoad;
    }

    public int getAddressNo() {

        return addressNo;
    }


    public long getPharmacyPhone() {

        return pharmacyPhone;
    }
}
