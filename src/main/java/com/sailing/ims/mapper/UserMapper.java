package com.sailing.ims.mapper;

import com.sailing.ims.dto.UserCreationRequest;
import com.sailing.ims.dto.UserResponse;
import com.sailing.ims.dto.UserUpdateRequest;
import com.sailing.ims.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    UserResponse toUserResponse(User user);
}
