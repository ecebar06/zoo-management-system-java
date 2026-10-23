/**
 * Represents an abstract Person in the zoo system.
 * A person has a name and an ID, and can perform actions such as visiting or feeding animals.
 * Subclasses define specific behaviors for different types of people (e.g., Visitor, Personnel).
 */
public abstract class Person {

    protected String name;
    protected String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract void visitAnimal(Animal animal);
    public abstract void feedAnimal(Animal animal, int meals, FoodStock stock) throws UnauthorizedOperationException, InsufficientFoodException;
}
