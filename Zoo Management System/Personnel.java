/**
 * Represents a Personnel member who works at the zoo.
 * Personnel can both feed animals and clean their habitats.
 */
public class Personnel extends Person {



    public Personnel(String name, String id) {
        super(name, id);
    }

    @Override
    public void visitAnimal(Animal animal) {
        System.out.println(name + " attempts to clean " + animal.getName() + "'s habitat.");
        System.out.print(name + " started cleaning " + animal.getName() + "'s habitat.\n");
        animal.cleanHabitat();
    }

    @Override
    public void feedAnimal(Animal animal, int meals, FoodStock stock) throws InsufficientFoodException {
        System.out.println(name + " attempts to feed " + animal.getName() + ".");
        animal.feed(stock, meals); // güncellenmiş feed fonksiyonu (aşağıda)
    }

}
