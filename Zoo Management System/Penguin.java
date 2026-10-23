import java.util.Locale;
/**
 * Represents a Penguin, a fish-eating animal.
 * This class defines the penguin's diet requirements and habitat cleaning behavior.
 */
public class Penguin extends Animal {



    public Penguin(String name, int age) {
        super(name, age);
    }

    @Override
    public double getFoodAmount() {
        return 2.84 + (age ) * 0.04;
    }

    @Override
    public void feed(FoodStock stock, int meals) throws InsufficientFoodException {
        double totalFish = getFoodAmount() * meals;

        if (!stock.hasEnough("Fish", totalFish)) {
            throw new InsufficientFoodException("Error: Not enough Fish");
        }

        stock.consume("Fish", totalFish);
        System.out.printf(Locale.US, "%s has been given %.3f kgs of various kinds of fish\n", name, totalFish);
    }

    @Override
    public void cleanHabitat() {
        System.out.println("Cleaning " + name + "'s habitat: Replenishing ice and scrubbing walls.");


    }
}