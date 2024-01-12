package lk.phoneshop.phoneshopfxmvc.tm;

public class PhoneTM {
private String id;
private String brand;
private String model;
private int ram;
private double price;
private int qty;


    @Override
    public String toString() {
        return "PhoneTM{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }

    public String getId() {
        return id;
    }

    public PhoneTM(String id, String brand, String model, int ram, double price,int qty) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.price = price;
        this.qty=qty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
