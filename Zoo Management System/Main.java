import java.io.*;
import java.util.*;
/**
 * Main class of the Zoo Management System.
 * Initializes animals, visitors, food stock, and processes commands from input files.
 */
public class Main {

    /**
     * Reads animal data from a file and initializes animal objects.
     * @param filename the file containing animal information
     * @param animals the map to store initialized Animal objects (key: name)
     */
    public static void readAnimals(String filename, Map<String, Animal> animals) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);

                Animal animal = null;

                switch (type) {
                    case "Lion":
                        animal = new Lion(name, age);
                        break;
                    case "Elephant":
                        animal = new Elephant(name, age);
                        break;
                    case "Penguin":
                        animal = new Penguin(name, age);
                        break;
                    case "Chimpanzee":
                        animal = new Chimpanzee(name, age);
                        break;
                }

                if (animal != null) {
                    animals.put(name, animal);
                    System.out.println("Added new " + type + " with name " + name + " aged " + age + ".");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Reads person data from a file and initializes Visitor and Personnel objects.
     * @param filename the file containing person information
     * @param persons the map to store initialized Person objects (key: id)
     */

    public static void readPersons(String filename, Map<String, Person> persons) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                String id = parts[2];

                Person person = null;

                if (type.equals("Visitor")) {
                    person = new Visitor(name, id);
                } else if (type.equals("Personnel")) {
                    person = new Personnel(name, id);
                }

                if (person != null) {
                    persons.put(id, person);
                    System.out.println("Added new " + type + " with id " + id + " and name " + name + ".");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Reads food stock data from a file and updates the FoodStock object.
     * @param filename the file containing food stock information
     * @param foodStock the FoodStock object to be updated
     */

    public static void readFoods(String filename, FoodStock foodStock) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);

                foodStock.addFood(type, amount);
            }

            System.out.printf(Locale.US, "There are %.3f kg of Meat in stock\n", foodStock.getAmount("Meat"));
            System.out.printf(Locale.US, "There are %.3f kg of Fish in stock\n", foodStock.getAmount("Fish"));
            System.out.printf(Locale.US, "There are %.3f kg of Plant in stock\n", foodStock.getAmount("Plant"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Processes commands from a file, such as listing food stock,
     * handling animal visitations, and feeding animals.
     * @param filename the file containing commands
     * @param persons the map of persons (visitors/personnel)
     * @param animals the map of animals
     * @param foodStock the FoodStock object for managing food
     */

    public static void processCommands(String filename, Map<String, Person> persons, Map<String, Animal> animals, FoodStock foodStock) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String> allLines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allLines.add(line);  // satırları listeye ekle
                }
            }
            for (int i = 0; i < allLines.size(); i++) {
                String currentLine = allLines.get(i);

                System.out.println("***Processing new Command***");



                String[] parts = currentLine.split(",");
                String command = parts[0];


                try {
                    if (command.equals("List Food Stock")) {
                        System.out.println("Listing available Food Stock:");
                        foodStock.printStock();

                    } else if (command.equals("Animal Visitation")) {

                        String id = parts[1];
                        String animalName = parts[2];

                        Person person = persons.get(id);
                        if (!persons.containsKey(id)) {
                            throw new PersonNotFoundException("Error:There are no visitors or personnel with the id "+ id);
                        }
                        if (!animals.containsKey(animalName)) {
                            System.out.println(person.getName() + " attempts to clean " + animalName + "'s habitat.");
                            throw new AnimalNotFoundException("Error: There are no animals with the name "+ animalName+".");
                        }

                        Person p = persons.get(id);
                        Animal a = animals.get(animalName);
                        p.visitAnimal(a);

                    } else if (command.equals("Feed Animal")) {

                        String id = parts[1];
                        String animalName = parts[2];

                        Person person = persons.get(id);
                        int meals;
                        try {
                            meals = Integer.parseInt(parts[3]);
                        } catch (NumberFormatException e) {
                            System.out.println("Error processing command: " + currentLine);
                            System.out.println("Error:" + e.getMessage());
                            System.out.println("***********************************");


                            continue;
                        }


                        if (!persons.containsKey(id)) {
                            throw new PersonNotFoundException("Error: There are no visitors or personnel with the id "+ id);
                        }
                        if (!animals.containsKey(animalName)) {
                            System.out.println(person.getName()+ " attempts to clean "+ animalName+ "'s habitat.");

                            throw new AnimalNotFoundException("Error: Animal " + animalName + " not found.");
                        }

                        Person p = persons.get(id);
                        Animal a = animals.get(animalName);
                        p.feedAnimal(a, meals, foodStock);

                    } else {
                        System.out.println("Unknown command: " + command);
                    }

                }catch (PersonNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (AnimalNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (UnauthorizedOperationException e) {
                    System.out.println(e.getMessage());
                } catch (InsufficientFoodException e) {
                    System.out.println(e.getMessage());
                }
                if (i < allLines.size() - 1) {
                    System.out.println("***********************************");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Entry point of the program.
     * Expects 5 arguments: animals file, persons file, foods file, commands file, and output file.
     * Initializes all data and processes commands, writing output to the specified file.
     * @param args command-line arguments
     */

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java Main animals.txt persons.txt foods.txt commands.txt output.txt");
            return;
        }

        String animalFile = args[0];
        String personFile = args[1];
        String foodFile = args[2];
        String commandFile = args[3];
        String outputFile = args[4];

        Map<String, Animal> animals = new HashMap<>();
        Map<String, Person> persons = new HashMap<>();
        FoodStock foodStock = new FoodStock();

        try (PrintStream out = new PrintStream(new FileOutputStream(outputFile))) {
            System.setOut(out);

            System.out.println("***********************************");
            System.out.println("***Initializing Animal information***");
            readAnimals(animalFile, animals);

            System.out.println("***********************************");
            System.out.println("***Initializing Visitor and Personnel information***");
            readPersons(personFile, persons);

            System.out.println("***********************************");
            System.out.println("***Initializing Food Stock***");
            readFoods(foodFile, foodStock);

            System.out.println("***********************************");
            processCommands(commandFile, persons, animals, foodStock);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
