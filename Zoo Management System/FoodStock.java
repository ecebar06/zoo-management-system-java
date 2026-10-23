import java.util.HashMap;
import java.util.Locale;
/**
 * Manages the food stock in the zoo.
 * Supports adding, checking, consuming, and printing the available food types (Meat, Fish, Plant).
 */
public class FoodStock {

    private HashMap<String, Double> stock;

    public FoodStock() {
        stock = new HashMap<>();
        stock.put("Meat", 0.0);
        stock.put("Fish", 0.0);
        stock.put("Plant", 0.0);
    }

    public void addFood(String type, double amount) {
        stock.put(type, stock.get(type) + amount);
    }

    public boolean hasEnough(String type, double amount) {
        return stock.get(type) >= amount;
    }

    public void consume(String type, double amount) {
        stock.put(type, stock.get(type) - amount);
    }

    public double getAmount(String type) {
        return stock.get(type);
    }

    public void printStock() {
        System.out.printf(Locale.US, "Plant: %.3f kgs\n", stock.get("Plant"));
        System.out.printf(Locale.US, "Fish: %.3f kgs\n", stock.get("Fish"));
        System.out.printf(Locale.US, "Meat: %.3f kgs\n", stock.get("Meat"));
    }
}
