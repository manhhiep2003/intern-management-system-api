package com.sailing.ims.service;

import com.sailing.ims.dto.UserCreationRequest;
import com.sailing.ims.dto.UserUpdateRequest;
import com.sailing.ims.exception.AppException;
import com.sailing.ims.exception.ErrorCode;
import com.sailing.ims.model.User;
import com.sailing.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest userCreationRequest) {
        User user = new User();

        if (userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setEmail(userCreationRequest.getEmail());
        user.setPassword(userCreationRequest.getPassword());
        user.setUsername(userCreationRequest.getUsername());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        User user = getUser(userId);
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPassword(userUpdateRequest.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
