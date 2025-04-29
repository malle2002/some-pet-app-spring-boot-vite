package org.example.api.pets;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("")
    List<PetModel> all(@RequestParam(required = false) Boolean owner) {
        if(owner) return petService.findByOwnerIsNull();
        else return petService.findAll();
    }

    @PostMapping("")
    PetModel newPet(@RequestBody StorePetDto newPet, String type) {
        return petService.save(newPet, type);
    }

    @PutMapping("/{id}")
    PetModel updatePet(@PathVariable UUID id, @RequestBody UpdatePetDto newPet) {
        return petService.updatePet(id, newPet);
    }

    @DeleteMapping("/{id}")
    void deletePet(@PathVariable UUID id) {
        petService.deletePet(id);
    }
}

