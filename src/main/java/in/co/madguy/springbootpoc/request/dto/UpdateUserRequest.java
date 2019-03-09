package in.co.madguy.springbootpoc.request.dto;

import com.google.common.base.Preconditions;
import in.co.madguy.springbootpoc.model.User;

public class UpdateUserRequest extends UserRequest {
    public static UpdateUserRequest from(User user) {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setName(Preconditions.checkNotNull(user.getName(), "Name is a mandatory attribute"));
        request.setAge(Preconditions.checkNotNull(user.getAge(), "Age is a mandatory attribute"));
        return request;
    }
}
