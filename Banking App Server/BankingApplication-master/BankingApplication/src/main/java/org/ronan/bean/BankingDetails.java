package org.ronan.bean;

//====================================================================
//  data model class for bank details
//====================================================================
public class BankingDetails {

    //vars
    int id;
    String Name;
    String Address;
    int Sortcode;

    //consturctor
    public BankingDetails() {
        super();
    }

    public BankingDetails(int id, String Name, String Address, int Sortcode) {
        super();
        this.id = id;
        this.Name = Name;
        this.Address = Address;
        this.Sortcode = Sortcode;
    }

    //getters setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getSortcode() {
        return Sortcode;
    }

    public void setSortcode(int sortcode) {
        Sortcode = sortcode;
    }

}
