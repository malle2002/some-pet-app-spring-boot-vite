package org.example.api.users;

public class NotEnoughResourcesException extends RuntimeException {
    public NotEnoughResourcesException() {
        super("You don't have enough resources to do this");
    }
}
