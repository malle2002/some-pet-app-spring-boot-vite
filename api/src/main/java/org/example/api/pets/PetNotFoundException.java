package org.example.api.pets;

import java.util.UUID;

class PetNotFoundException extends RuntimeException {
    PetNotFoundException(UUID id) {
        super("Could not find pet with id: " + id);
    }
}
