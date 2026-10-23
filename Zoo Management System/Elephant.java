import java.util.Locale;
/**
 * Represents an Elephant, a herbivore animal that consumes only plants.
 * The elephant's diet and habitat cleaning routine are implemented here.
 */

public class Elephant extends Animal {

    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    public double getFoodAmount() {
        return 9.7 + (age ) * 0.015;
    }

    @Override
    public void feed(FoodStock stock, int meals) throws InsufficientFoodException {
        double totalPlant = getFoodAmount() * meals;

        if (!stock.hasEnough("Plant", totalPlant)) {
            throw new InsufficientFoodException("Error: Not enough Plant");
        }

        stock.consume("Plant", totalPlant);
        System.out.printf(Locale.US, "%s has been given %.3f kgs assorted fruits and hay\n", name, totalPlant);
    }

    @Override
    public void cleanHabitat() {
        System.out.println("Cleaning " + name + "'s habitat: Washing the water area.");
    }
}

