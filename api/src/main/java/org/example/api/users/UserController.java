package org.example.api.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    List<UserModel> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/")
    UserModel createUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    UserModel updateUser(@PathVariable UUID id, @RequestBody UserDto user) {
        return userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable UUID id) {
        userService.delete(id);
    }

    @PostMapping("/purchase-pet")
    String purchasePet(@RequestParam UUID userId, @RequestParam UUID petId) {
        if(userService.buyPet(userId,petId)){
            return "success";
        } else {
            return "fail";
        }
    }
}
