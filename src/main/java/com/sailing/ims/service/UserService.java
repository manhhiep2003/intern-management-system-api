package com.sailing.ims.service;

import com.sailing.ims.dto.UserCreationRequest;
import com.sailing.ims.dto.UserResponse;
import com.sailing.ims.dto.UserUpdateRequest;
import com.sailing.ims.exception.AppException;
import com.sailing.ims.exception.ErrorCode;
import com.sailing.ims.mapper.UserMapper;
import com.sailing.ims.model.User;
import com.sailing.ims.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public User createUser(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(userCreationRequest);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        userMapper.updateUser(user, userUpdateRequest);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
