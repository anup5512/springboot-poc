package in.co.madguy.springbootpoc.service.feign;

import feign.RequestLine;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.response.dto.UserResponse;

public interface UserClient {
    @RequestLine("POST /aKBFJ5tZPZAO7j2OBYoa")
    UserResponse create(CreateUserRequest request);
}
