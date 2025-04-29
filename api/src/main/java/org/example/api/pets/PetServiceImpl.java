package org.example.api.pets;

import lombok.SneakyThrows;
import org.example.api.users.UserModel;
import org.example.api.users.UserNotFoundException;
import org.example.api.users.UserRepository;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetServiceImpl(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PetModel> findAll() {
        return petRepository.findAll();
    }

    @Override
    public PetModel findById(UUID id) {
        return petRepository.findById(id).orElseThrow( () -> new PetNotFoundException(id));
    }

    @Override
    public void deletePet(UUID id) {
        PetModel foundPet = findById(id);

        if(foundPet == null) throw new PetNotFoundException(id);

        petRepository.delete(foundPet);
    }

    @Override
    public PetModel updatePet(UUID id, UpdatePetDto newPet) {
        PetModel foundPet = findById(id);

        if(foundPet == null) throw new PetNotFoundException(id);

        UserModel owner = userRepository
                .findById(newPet.getOwner())
                .orElseThrow(() -> new UserNotFoundException(newPet.getOwner()));

        foundPet.setPrice(newPet.getPrice());
        foundPet.setDescription(newPet.getDescription());
        foundPet.setName(newPet.getName());
        foundPet.setOwner(owner);

        petRepository.save(foundPet);

        return foundPet;
    }

    @SneakyThrows
    @Override
    public PetModel save(StorePetDto pet, String type) {
        PetModel newPet;
        if (type.equalsIgnoreCase("dog")) {
            if(!pet.getRating().isPresent()) throw new RatingNotFoundException();
            newPet = new Dog(
                    pet.getName(),
                    pet.getDescription(),
                    pet.getDateOfBirth(),
                    pet.getRating().get()
            );
        } else if(type.equalsIgnoreCase("cat")){
            newPet = new Cat(
                    pet.getName(),
                    pet.getDescription(),
                    pet.getDateOfBirth()
            );
        } else throw new UnexpectedException("Something didn't go right!");
        newPet.initializePrice();
        return petRepository.save(newPet);
    }

    @Override
    public List<PetModel> findByOwnerIsNull() {
        return petRepository.findByOwnerIsNull();
    }
}

