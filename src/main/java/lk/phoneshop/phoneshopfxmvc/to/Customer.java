package lk.phoneshop.phoneshopfxmvc.to;

public class Customer {
    private String id;
    private String nic;
    private String name;
    private String address;
    private String contact;

    public Customer(String id, String nic, String name, String address, String contact) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Customer() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
