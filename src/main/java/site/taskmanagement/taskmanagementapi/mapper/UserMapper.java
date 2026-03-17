package site.taskmanagement.taskmanagementapi.mapper;

import org.mapstruct.Mapper;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

}
