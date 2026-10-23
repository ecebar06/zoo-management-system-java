import java.util.Locale;
/**
 * Represents a Lion, a carnivorous animal.
 * This class defines the lion's diet requirements and habitat cleaning routine.
 */
public class Lion extends Animal {


    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public double getFoodAmount() {
        return 4.75 + (age ) * 0.05;

    }

    @Override
    public void feed(FoodStock stock, int meals) throws InsufficientFoodException {
        double totalMeat = getFoodAmount() * meals;

        if (!stock.hasEnough("Meat", totalMeat)) {
            throw new InsufficientFoodException("Error: Not enough Meat");
        }

        stock.consume("Meat", totalMeat);
        System.out.printf(Locale.US, "%s has been given %.3f kgs of meat\n", name, totalMeat);
    }

    @Override
    public void cleanHabitat() {
        System.out.println("Cleaning " + name + "'s habitat: Removing bones and refreshing sand.");
    }
}


