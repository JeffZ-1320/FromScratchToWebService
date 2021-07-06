package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/secure/breedType")
public class BreedTypeController {
    private BreedTypeRepository breedTypeRepository;

    public BreedTypeController() {
    }

    @Autowired
    public BreedTypeController(BreedTypeRepository breedTypeRepository) {
        this.breedTypeRepository = breedTypeRepository;
    }

    @GetMapping("/getAll")
    public List<BreedTypeEntity> getAllBreedType(){
        return breedTypeRepository.findAll();
    }
}
