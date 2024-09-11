package com.example.agregador_investimentos.model.service;


import com.example.agregador_investimentos.dto.CreateUserDtoRequest;
import com.example.agregador_investimentos.dto.UpdateUserDtoRequest;
import com.example.agregador_investimentos.model.entity.User;
import com.example.agregador_investimentos.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public List<User> listUsers() {
        return userRepository.findAll();
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

}
