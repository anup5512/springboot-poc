package in.co.madguy.springbootpoc.service.feign;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.response.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        User user = new User("Dummy", "Dummy", 1);
        UserResponse userResponse = new UserResponse("Service down. Fallback initiated.", user);

        String status = (cause instanceof FeignException ? String.valueOf(((FeignException) cause).status()) : "");
        String reason = (cause instanceof FeignException ? new String(((FeignException) cause).content()) : String.valueOf(cause));
        UserClient userClient = new UserClient() {
            @Override
            public UserResponse createUser(CreateUserRequest request, String apiKey) {
                log.warn(MessageFormat.format("Hystrix fallback; status: {0}, reason: {1}", status, reason));
                return userResponse;
            }

            @Override
            public UserResponse createUser2(CreateUserRequest request, String apiKey, String apiId) {
                log.warn(MessageFormat.format("Hystrix fallback; status: {0}, reason: {1}", status, reason));
                return userResponse;
            }
        };

        /*if (cause instanceof FeignException && ((FeignException) cause).status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            return userClient;
        }*/

        return userClient;
    }
}
