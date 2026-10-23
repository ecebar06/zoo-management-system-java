// Exception: Person not found
class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message) {
        super(message);
    }
}

// Exception: Animal not found
class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

// Exception: Unauthorized access
class UnauthorizedOperationException extends Exception {
    public UnauthorizedOperationException(String visitorName, String animalName) {
        super(visitorName + " tried to feed " + animalName + "\nError: Visitors do not have the authority to feed animals.");
    }
}



// Exception: Not enough food
class InsufficientFoodException extends Exception {
    public InsufficientFoodException(String message) {
        super(message);
    }
}
class InvalidCommandFormatException extends Exception {
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
