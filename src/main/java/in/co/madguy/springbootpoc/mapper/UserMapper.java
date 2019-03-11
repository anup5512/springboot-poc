package in.co.madguy.springbootpoc.mapper;

import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.request.dto.UpdateUserRequest;

public class UserMapper {
    public static User from(CreateUserRequest request) {
        User u = new User();
        u.setName(request.getName());
        u.setAge(request.getAge());
        return u;
    }

    public static User from(UpdateUserRequest request, String userId) {
        User u = new User();
        u.setUserId(userId);
        u.setName(request.getName());
        u.setAge(request.getAge());
        return u;
    }
}
