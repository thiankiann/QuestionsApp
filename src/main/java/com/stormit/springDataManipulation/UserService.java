package com.stormit.springDataManipulation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Transactional
    public User createUser(User user){
        userValidator.validate(user);

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer id, User userRequest){
        userRequest.setId(id);

        userValidator.validate(userRequest);

        User user = userRepository.findById(id).orElseThrow();

        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setAge(userRequest.getAge());

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

