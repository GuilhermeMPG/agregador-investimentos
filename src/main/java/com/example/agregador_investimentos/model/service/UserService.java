package com.example.agregador_investimentos.model.service;


import com.example.agregador_investimentos.dto.*;
import com.example.agregador_investimentos.model.entity.Account;
import com.example.agregador_investimentos.model.entity.BillingAddress;
import com.example.agregador_investimentos.model.entity.User;
import com.example.agregador_investimentos.model.repository.AccountRepository;
import com.example.agregador_investimentos.model.repository.BillingAddressRepository;
import com.example.agregador_investimentos.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    public UUID creatUser(CreateUserDtoRequest createUserDtoRequest) {
        var entity = new User(createUserDtoRequest.username(),
                createUserDtoRequest.email(),
                createUserDtoRequest.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();

    }

    public Optional<User> getUserById(UUID id) {

        return userRepository.findById(id);

    }

    public List<UserDtoResponse> listUsers() {


        return userRepository.findAll().stream().map(user -> new UserDtoResponse(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreationTimestamp(),
                user.getUpdateTimestamp(),
                user.getAccountList().stream().map(accUser ->
                        new UserAccountDtoResponse(accUser.getAccountId(), accUser.getDescription(),
                                accUser.getUser().getUserId(), accUser.getUser().getUsername())).toList()
        )
        ).toList();


    }

    public Boolean deleteById(UUID id) {
        Boolean deleteId = false;
        deleteId = userRepository.existsById(id);
        if (deleteId) {
            userRepository.deleteById(id);
        }
        return deleteId;
    }


    public User updateUserById(UpdateUserDtoRequest updateUserDtoRequest) {

        Optional<User> user = userRepository.findById(UUID.fromString(updateUserDtoRequest.userId()));
        User userEntity = new User();
        if (user.isPresent()) {
            userEntity = user.get();

            if (updateUserDtoRequest.username() != null) {
                userEntity.setUsername(updateUserDtoRequest.username());
            }
            if (updateUserDtoRequest.email() != null) {
                userEntity.setEmail(updateUserDtoRequest.email());
            }
            if (updateUserDtoRequest.password() != null) {
                userEntity.setPassword(updateUserDtoRequest.password());
            }

            userRepository.save(userEntity);


        }
        return userEntity;

    }

    public void createAccount(String userId, CreateAccountDtoRequest createAccountDtoRequest) {

        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Account account = new Account(
                new ArrayList<>(),
                createAccountDtoRequest.description(),
                null,
                user

        );


        Account accountCreated = accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDtoRequest.street(),
                createAccountDtoRequest.number()


        );


        billingAddressRepository.save(billingAddress);



    }

    public List<AccountDtoResponse> lisAccounts(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getAccountList().stream().map(ac -> new AccountDtoResponse(ac.getAccountId().toString(), ac.getDescription())).toList();

    }
}
