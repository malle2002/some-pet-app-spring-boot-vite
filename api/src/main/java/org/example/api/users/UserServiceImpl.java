package org.example.api.users;

import org.example.api.common.domain.Money;
import org.example.api.pets.PetModel;
import org.example.api.pets.PetService;
import org.example.api.pets.UpdatePetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PetService petService;

    public UserServiceImpl(UserRepository userRepository, PetService petService) {
        this.userRepository = userRepository;
        this.petService = petService;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserModel save(UserDto user) {
        UserModel newUser = new UserModel(
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress(),
                user.getBudget()
        );
        return userRepository.save(newUser);
    }

    @Override
    public void delete(UUID id) {
        UserModel foundUser = findById(id);

        if(foundUser == null) throw new UserNotFoundException(id);

        userRepository.delete(foundUser);
    }

    @Override
    public UserModel update(UUID id, UserDto newUser) {
        UserModel foundUser = findById(id);

        if(foundUser == null) throw new UserNotFoundException(id);

        foundUser.setBudget(newUser.getBudget());
        foundUser.setFirstName(newUser.getFirstName());
        foundUser.setLastName(newUser.getLastName());
        foundUser.setEmailAddress(newUser.getEmailAddress());

        userRepository.save(foundUser);

        return foundUser;
    }

    @Override
    public synchronized boolean buyPet(UUID userId, UUID petId) {
        PetModel foundPet = petService.findById(petId);
        UserModel foundUser = findById(userId);

        if(foundUser.getBudget().getAmount().compareTo(foundPet.getPrice().getAmount()) >= 0 && foundPet.getOwner() == null) {
            foundPet.setOwner(foundUser);
            petService.updatePet(
                    foundPet.getId(),
                    new UpdatePetDto(
                            foundPet.getOwner().getId(),
                            foundPet.getName(),
                            foundPet.getDescription(),
                            foundPet.getDateOfBirth(),
                            foundPet.getPrice()));
            foundUser.setBudget(new Money(foundUser.getBudget().getAmount().subtract(foundPet.getPrice().getAmount()), foundUser.getBudget().getCurrency()));
            foundPet.printIfBought();
            return true;
        } else return false;
    }
}

