package com.operion.auth.mapper;

import com.operion.auth.dto.UserResponse;
import com.operion.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
}
