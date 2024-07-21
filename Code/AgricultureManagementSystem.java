import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgricultureManagementSystem {
    private Connection connection;

    public AgricultureManagementSystem() throws SQLException {
        this.connection = DatabaseUtils.getConnection();
    }

    public void addFarmer(Farmer farmer) throws SQLException {
        String query = "INSERT INTO farmers (name, contact_number) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, farmer.getName());
            stmt.setString(2, farmer.getContactNumber());
            stmt.executeUpdate();
            System.out.println("Farmer added: " + farmer);
        }
    }

    public void addCrop(Crop crop) throws SQLException {
        String query = "INSERT INTO crops (name, type, price_per_kg) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, crop.getName());
            stmt.setString(2, crop.getType());
            stmt.setDouble(3, crop.getPricePerKg());
            stmt.executeUpdate();
            System.out.println("Crop added: " + crop);
        }
    }

    public List<Farmer> listFarmers() throws SQLException {
        String query = "SELECT * FROM farmers";
        List<Farmer> farmers = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Farmer farmer = new Farmer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_number")
                );
                farmers.add(farmer);
            }
        }
        return farmers;
    }

    public List<Crop> listCrops() throws SQLException {
        String query = "SELECT * FROM crops";
        List<Crop> crops = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Crop crop = new Crop(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("price_per_kg")
                );
                crops.add(crop);
            }
        }
        return crops;
    }

    public static void main(String[] args) {
        try {
            AgricultureManagementSystem system = new AgricultureManagementSystem();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Add Farmer");
                System.out.println("2. Add Crop");
                System.out.println("3. List Farmers");
                System.out.println("4. List Crops");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter farmer name: ");
                        String farmerName = scanner.nextLine();
                        System.out.print("Enter farmer contact number: ");
                        String contactNumber = scanner.nextLine();
                        system.addFarmer(new Farmer(farmerName, contactNumber));
                        break;
                    case 2:
                        System.out.print("Enter crop name: ");
                        String cropName = scanner.nextLine();
                        System.out.print("Enter crop type: ");
                        String cropType = scanner.nextLine();
                        System.out.print("Enter price per kg: ");
                        double pricePerKg = scanner.nextDouble();
                        system.addCrop(new Crop(cropName, cropType, pricePerKg));
                        break;
                    case 3:
                        List<Farmer> farmers = system.listFarmers();
                        System.out.println("List of Farmers:");
                        for (Farmer farmer : farmers) {
                            System.out.println(farmer);
                        }
                        break;
                    case 4:
                        List<Crop> crops = system.listCrops();
                        System.out.println("List of Crops:");
                        for (Crop crop : crops) {
                            System.out.println(crop);
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
