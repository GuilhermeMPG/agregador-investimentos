package com.example.agregador_investimentos.controller;


import com.example.agregador_investimentos.dto.*;

import com.example.agregador_investimentos.model.entity.User;
import com.example.agregador_investimentos.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> creatUser(@RequestBody CreateUserDtoRequest createUserDtoRequest) {
        var userId = userService.creatUser(createUserDtoRequest);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();


    }
    @PutMapping
    public ResponseEntity<User> updateUserById( @RequestBody UpdateUserDtoRequest updateUserDtoRequest){

        User user =  userService.updateUserById(updateUserDtoRequest);

        return ResponseEntity.ok(user);



    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {

        var user = userService.getUserById(UUID.fromString(userId));

        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();


    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> listUsers() {
        return ResponseEntity.ok(this.userService.listUsers());

    }


    @DeleteMapping ("/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") String userId) {

        Boolean deletedId = userService.deleteById(UUID.fromString(userId));

        return deletedId ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();


    }

    @PostMapping ("/{userId}/accounts")
    public ResponseEntity<Void> createAccountById(@PathVariable("userId") String userId,
    @RequestBody CreateAccountDtoRequest createAccountDtoRequest){

      userService.createAccount(userId,createAccountDtoRequest);

        return ResponseEntity.ok().build();



    }




    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountDtoResponse>> createAccountById(@PathVariable("userId") String userId ){

        List<AccountDtoResponse> accounts = userService.lisAccounts(userId);

        return ResponseEntity.ok(accounts);



    }

}
