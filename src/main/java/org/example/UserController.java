package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/secure/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers(){
        List<UserEntity> allUsers = userRepository.findAll();
        return allUsers;
    }

    @GetMapping("/getUserById/{id}")
    public UserEntity getUserById(@PathVariable int id){
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/newUser")
    public UserEntity addUser(@Valid @RequestBody UserEntity user){
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public UserEntity updateUserRecord(@PathVariable int id, @NotNull @Valid @RequestBody UserEntity newUser){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setUserId(newUser.getUserId());
        userEntity.setFirstName(newUser.getFirstName());
        userEntity.setLastName(newUser.getLastName());
        userEntity.setNumberOfPets(newUser.getNumberOfPets());
        return userRepository.save(newUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public int deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return 1;
    }
}
