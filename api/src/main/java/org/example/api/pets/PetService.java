package org.example.api.pets;

import java.util.List;
import java.util.UUID;

public interface PetService {
    List<PetModel> findAll();
    PetModel findById(UUID id);
    void deletePet(UUID id);
    PetModel updatePet(UUID id, UpdatePetDto newPet);
    PetModel save(StorePetDto pet, String type);
    List<PetModel> findByOwnerIsNull();
}

