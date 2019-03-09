package in.co.madguy.springbootpoc.request.dto;

import com.google.common.base.Preconditions;
import in.co.madguy.springbootpoc.model.User;

public class CreateUserRequest extends UserRequest {
    public static CreateUserRequest from(User user) {
        CreateUserRequest request = new CreateUserRequest();
        request.setName(Preconditions.checkNotNull(user.getName(), "Name is a mandatory attribute"));
        request.setAge(Preconditions.checkNotNull(user.getAge(), "Age is a mandatory attribute"));
        return request;
    }
}
