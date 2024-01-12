package lk.phoneshop.phoneshopfxmvc.tm;

public class OrderDetailTM {
    private String id;
    private String model;
   private double unitPrice;
    private int qty;
    private double total;

    public OrderDetailTM(String id, String model, double unitPrice, int qty, double total) {
        this.id = id;
        this.model = model;
        this.setUnitPrice(unitPrice);
        this.qty = qty;
        this.total = total;
    }

    public OrderDetailTM() {

    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", unitPrice=" + getUnitPrice() +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
