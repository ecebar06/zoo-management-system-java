/**
 * Represents a Visitor at the zoo.
 * Visitors can register to visit animals but are not authorized to feed them.
 */
public class Visitor extends Person {


    public Visitor(String name, String id) {
        super(name, id);
    }

    @Override
    public void visitAnimal(Animal animal) {
        System.out.println(name + " tried to register for a visit to " + animal.getName() + ".");
        System.out.println(name + " successfully visited " + animal.getName() + ".");
    }

    @Override
    public void feedAnimal(Animal animal, int meals, FoodStock stock) throws UnauthorizedOperationException {
        throw new UnauthorizedOperationException(name, animal.getName());
    }
}
