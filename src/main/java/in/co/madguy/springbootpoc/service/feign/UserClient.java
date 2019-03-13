package in.co.madguy.springbootpoc.service.feign;

import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.response.dto.UserResponse;

public interface UserClient {
    @RequestLine("POST /aKBFJ5tZPZAO7j2OBYoa")
    @Headers("X-API-KEY: {x-api-key}")
    UserResponse createUser(CreateUserRequest request, @Param("x-api-key") String apiKey);

    @RequestLine("POST /aKBFJ5tZPZAO7j2OBYoa")
    @Headers({
        "X-API-KEY: {x-api-key}",
        "X-API-ID: {x-api-id}"
    })
    UserResponse createUser2(CreateUserRequest request, @Param("x-api-key") String apiKey, @Param("x-api-id") String apiId);

    static UserClient init(String url) {
        return HystrixFeign.builder()
            .options(new Request.Options())
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .retryer(new Retryer() {
                @Override
                public void continueOrPropagate(RetryableException e) {

                }

                @Override
                public Retryer clone() {
                    return null;
                }
            })
            .target(UserClient.class, url, new UserClientFallbackFactory());
    }
}
