public abstract class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     *Calculates how much food each animal will eat based on its age.
     * @return the required amount of food (in kilograms)
     */
    public abstract double getFoodAmount();

    /**
     * Attempts to feed the animal.
     * @param stock the current food stock
     * @throws InsufficientFoodException thrown if there is not enough food
     */

    public abstract void feed(FoodStock stock, int meals) throws InsufficientFoodException;

    /**
     * Cleans the animal's habitat (each species has its own cleaning routine)
     */

    public abstract void cleanHabitat();
}
