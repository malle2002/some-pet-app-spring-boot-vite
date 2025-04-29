package org.example.api.users;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();
    UserModel findById(UUID id);
    UserModel save(UserDto user);
    void delete(UUID id);
    UserModel update(UUID id, UserDto user);
    boolean buyPet(UUID userId, UUID petId);
}

