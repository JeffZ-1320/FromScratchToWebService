package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetsController {
    @Autowired
    private PetRepository petRepository;


    @GetMapping("/allPets")
    public List<PetsEntity> getAllPets(){
        List<PetsEntity> allPets = petRepository.findAll();
        return allPets;

    }

    @GetMapping("/getPetById/{id}")
    public PetsEntity getPetById(@PathVariable int id){
        // haven't defined an exception
        return petRepository.findById(id).orElseThrow();
    }

    @PostMapping("/newPet")
    public PetsEntity addPet(@Valid @RequestBody PetsEntity pet){
        return petRepository.save(pet);
    }

    @PutMapping("/updatePet/{id}")
    public PetsEntity updatePetRecord(@PathVariable int id, @NotNull @Valid @RequestBody PetsEntity newPet){
        PetsEntity petEntity = petRepository.findById(id).orElse(null);
        petEntity.setUserId(newPet.getUserId());
        petEntity.setPetName(newPet.getPetName());
        petEntity.setBirthdate(newPet.getBirthdate());
        petEntity.setColor(newPet.getColor());
        petEntity.setBreedType(newPet.getColor());
        petEntity.setSex(newPet.getSex());
        return petRepository.save(petEntity);
    }

    @DeleteMapping("/deletePet/{id}")
    public int deletePet(@PathVariable int id){
        petRepository.deleteById(id);
        return 1;
    }
}
