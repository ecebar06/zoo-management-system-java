import java.util.Locale;
/**
 * Represents a Chimpanzee, which is a type of Animal.
 * The chimpanzee eats both meat and plants in equal parts and has a specific cleaning routine.
 */
public class Chimpanzee extends Animal {


    public Chimpanzee(String name, int age) {
        super(name, age);
    }

    @Override
    public double getFoodAmount() {
        double baseAmount = 6.0; // 3 meat + 3 plant
        return baseAmount + (age - 10) * 0.025;
    }


    @Override
    public void feed(FoodStock stock, int meals) throws InsufficientFoodException {
        double totalFood = getFoodAmount() * meals;
        double halfMeat = totalFood / 2;
        double halfPlant = totalFood / 2;

        if (!stock.hasEnough("Meat", halfMeat)) {
            throw new InsufficientFoodException("Error: Not enough Meat");
        }
        if (!stock.hasEnough("Plant", halfPlant)) {
            throw new InsufficientFoodException("Error: Not enough Plant");
        }

        stock.consume("Meat", halfMeat);
        stock.consume("Plant", halfPlant);

        System.out.printf(Locale.US, "%s has been given %.3f kgs of meat and %.3f kgs of leaves\n", name, halfMeat, halfPlant);
    }


    @Override
    public void cleanHabitat() {
        System.out.println("Cleaning " + name + "'s habitat: Sweeping the enclosure and replacing branches.");
    }
}

