public class Crop {
    private int id;
    private String name;
    private String type;
    private double pricePerKg;

    public Crop(int id, String name, String type, double pricePerKg) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerKg = pricePerKg;
    }

    public Crop(String name, String type, double pricePerKg) {
        this.name = name;
        this.type = type;
        this.pricePerKg = pricePerKg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pricePerKg=" + pricePerKg +
                '}';
    }
}
