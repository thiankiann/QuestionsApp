package com.stormit.demo.springDataManipulation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    private final Validator validator;

    public void validate(User user) {
        Assert.notNull(user, "User can't be null");
        Assert.notNull(user.getUsername(), "User.username can't be null");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException("Error occurred: \n" + sb, violations);
        }

        if (!userRepository.isUsernameUnique(user)) {
            throw new IllegalArgumentException(String.format("Username[%s] is already taken", user.getUsername()));
        }
    }
}

